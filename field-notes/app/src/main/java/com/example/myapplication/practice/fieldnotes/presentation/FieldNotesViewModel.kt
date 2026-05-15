package com.example.myapplication.practice.fieldnotes.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.practice.fieldnotes.FieldNote
import com.example.myapplication.practice.fieldnotes.FieldNotesStarterData
import com.example.myapplication.practice.fieldnotes.NotePriority
import com.example.myapplication.practice.fieldnotes.NoteStatus
import com.example.myapplication.practice.fieldnotes.data.FieldNotesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class FieldNotesViewModel @Inject constructor(
    private val repository: FieldNotesRepository,
) : ViewModel() {
    private val searchQuery = MutableStateFlow("")
    private val statusFilter = MutableStateFlow(NoteStatusFilter.All)
    private val sortOrder = MutableStateFlow(NoteSortOrder.LastUpdatedFirst)
    private val screenMode = MutableStateFlow(FieldNotesScreenMode.List)
    private val draft = MutableStateFlow(FieldNoteDraft())
    private val pendingDeleteNoteId = MutableStateFlow<String?>(null)
    private val controls =
        combine(
            statusFilter,
            sortOrder,
            screenMode,
            draft,
            pendingDeleteNoteId,
        ) { statusFilter, sortOrder, screenMode, draft, pendingDeleteNoteId ->
            FieldNotesControlsState(
                statusFilter = statusFilter,
                sortOrder = sortOrder,
                screenMode = screenMode,
                draft = draft,
                pendingDeleteNoteId = pendingDeleteNoteId,
            )
        }

    val uiState: StateFlow<FieldNotesUiState> =
        combine(
            repository.notes,
            controls,
            searchQuery,
        ) { notes, controls, searchQuery ->
                FieldNotesUiState(
                    notes = notes.sortedAndFilteredBy(
                        searchQuery = searchQuery,
                        statusFilter = controls.statusFilter,
                        sortOrder = controls.sortOrder,
                    ),
                    searchQuery = searchQuery,
                    statusFilter = controls.statusFilter,
                    sortOrder = controls.sortOrder,
                    screenMode = controls.screenMode,
                    draft = controls.draft,
                    pendingDeleteNoteId = controls.pendingDeleteNoteId,
                )
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = FieldNotesUiState(),
            )

    init {
        viewModelScope.launch {
            repository.seedIfEmpty(FieldNotesStarterData.notes)
        }
    }

    fun onEvent(event: FieldNotesEvent) {
        when (event) {
            is FieldNotesEvent.SearchQueryChanged -> updateSearchQuery(event.query)
            is FieldNotesEvent.StatusFilterSelected -> selectStatusFilter(event.filter)
            is FieldNotesEvent.SortOrderSelected -> selectSortOrder(event.sortOrder)
            FieldNotesEvent.AddNoteStarted -> startAddNote()
            is FieldNotesEvent.EditNoteStarted -> startEditNote(event.noteId)
            is FieldNotesEvent.DeleteNoteRequested -> requestDeleteNote(event.noteId)
            FieldNotesEvent.DeleteNoteCancelled -> cancelDeleteNote()
            FieldNotesEvent.DeleteNoteConfirmed -> confirmDeleteNote()
            FieldNotesEvent.DraftCancelled -> cancelDraft()
            is FieldNotesEvent.DraftTitleChanged -> updateDraftTitle(event.title)
            is FieldNotesEvent.DraftDescriptionChanged -> updateDraftDescription(event.description)
            is FieldNotesEvent.DraftPriorityChanged -> updateDraftPriority(event.priority)
            is FieldNotesEvent.DraftRoomChanged -> updateDraftRoom(event.room)
            FieldNotesEvent.DraftSaved -> saveDraft()
        }
    }

    private fun updateSearchQuery(query: String) {
        searchQuery.update { query }
    }

    private fun selectStatusFilter(filter: NoteStatusFilter) {
        statusFilter.update { filter }
    }

    private fun selectSortOrder(order: NoteSortOrder) {
        sortOrder.update { order }
    }

    private fun startAddNote() {
        draft.value = FieldNoteDraft()
        screenMode.value = FieldNotesScreenMode.Add
    }

    private fun startEditNote(noteId: String) {
        val note = uiState.value.notes.firstOrNull { it.id == noteId } ?: return
        draft.value = FieldNoteDraft(
            noteId = note.id,
            title = note.title,
            description = note.description,
            priority = note.priority,
            room = note.room.orEmpty(),
            status = note.status,
        )
        screenMode.value = FieldNotesScreenMode.Edit
    }

    private fun requestDeleteNote(noteId: String) {
        pendingDeleteNoteId.value = noteId
    }

    private fun cancelDeleteNote() {
        pendingDeleteNoteId.value = null
    }

    private fun confirmDeleteNote() {
        val noteId = pendingDeleteNoteId.value ?: return
        viewModelScope.launch {
            repository.delete(noteId)
            pendingDeleteNoteId.value = null
        }
    }

    private fun cancelDraft() {
        draft.value = FieldNoteDraft()
        screenMode.value = FieldNotesScreenMode.List
    }

    private fun updateDraftTitle(title: String) {
        draft.update { it.copy(title = title) }
    }

    private fun updateDraftDescription(description: String) {
        draft.update { it.copy(description = description) }
    }

    private fun updateDraftPriority(priority: NotePriority) {
        draft.update { it.copy(priority = priority) }
    }

    private fun updateDraftRoom(room: String) {
        draft.update { it.copy(room = room) }
    }

    private fun saveDraft() {
        val currentDraft = draft.value
        if (!currentDraft.canSave) return

        viewModelScope.launch {
            repository.save(
                FieldNote(
                    id = currentDraft.noteId ?: UUID.randomUUID().toString(),
                    title = currentDraft.title.trim(),
                    description = currentDraft.description.trim(),
                    room = currentDraft.room.trim().ifBlank { null },
                    priority = currentDraft.priority,
                    status = currentDraft.status,
                    updatedMinutesAgo = 0,
                )
            )
            draft.value = FieldNoteDraft()
            screenMode.value = FieldNotesScreenMode.List
        }
    }

    private data class FieldNotesControlsState(
        val statusFilter: NoteStatusFilter,
        val sortOrder: NoteSortOrder,
        val screenMode: FieldNotesScreenMode,
        val draft: FieldNoteDraft,
        val pendingDeleteNoteId: String?,
    )
}

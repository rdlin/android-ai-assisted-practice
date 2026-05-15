package com.example.myapplication.practice.fieldnotes.presentation

import com.example.myapplication.practice.fieldnotes.NotePriority

sealed interface FieldNotesEvent {
    data class SearchQueryChanged(val query: String) : FieldNotesEvent
    data class StatusFilterSelected(val filter: NoteStatusFilter) : FieldNotesEvent
    data class SortOrderSelected(val sortOrder: NoteSortOrder) : FieldNotesEvent
    data object AddNoteStarted : FieldNotesEvent
    data class EditNoteStarted(val noteId: String) : FieldNotesEvent
    data class DeleteNoteRequested(val noteId: String) : FieldNotesEvent
    data object DeleteNoteCancelled : FieldNotesEvent
    data object DeleteNoteConfirmed : FieldNotesEvent
    data object DraftCancelled : FieldNotesEvent
    data class DraftTitleChanged(val title: String) : FieldNotesEvent
    data class DraftDescriptionChanged(val description: String) : FieldNotesEvent
    data class DraftPriorityChanged(val priority: NotePriority) : FieldNotesEvent
    data class DraftRoomChanged(val room: String) : FieldNotesEvent
    data object DraftSaved : FieldNotesEvent
}

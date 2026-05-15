package com.example.myapplication.practice.fieldnotes.presentation

import com.example.myapplication.practice.fieldnotes.FieldNote
import com.example.myapplication.practice.fieldnotes.NotePriority
import com.example.myapplication.practice.fieldnotes.NoteStatus

data class FieldNotesUiState(
    val notes: List<FieldNote> = emptyList(),
    val searchQuery: String = "",
    val statusFilter: NoteStatusFilter = NoteStatusFilter.All,
    val sortOrder: NoteSortOrder = NoteSortOrder.LastUpdatedFirst,
    val screenMode: FieldNotesScreenMode = FieldNotesScreenMode.List,
    val draft: FieldNoteDraft = FieldNoteDraft(),
    val pendingDeleteNoteId: String? = null,
)

enum class FieldNotesScreenMode {
    List,
    Add,
    Edit,
}

data class FieldNoteDraft(
    val noteId: String? = null,
    val title: String = "",
    val description: String = "",
    val priority: NotePriority = NotePriority.Medium,
    val room: String = "",
    val status: NoteStatus = NoteStatus.Open,
) {
    val canSave: Boolean
        get() = title.isNotBlank() && description.isNotBlank()
}

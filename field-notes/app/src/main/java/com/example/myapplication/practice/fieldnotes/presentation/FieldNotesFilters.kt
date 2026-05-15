package com.example.myapplication.practice.fieldnotes.presentation

import com.example.myapplication.practice.fieldnotes.FieldNote
import com.example.myapplication.practice.fieldnotes.NoteStatus

enum class NoteStatusFilter {
    All,
    Open,
    Complete,
}

enum class NoteSortOrder {
    LastUpdatedFirst,
    LastUpdatedLast,
}

fun List<FieldNote>.sortedAndFilteredBy(
    searchQuery: String = "",
    statusFilter: NoteStatusFilter = NoteStatusFilter.All,
    sortOrder: NoteSortOrder = NoteSortOrder.LastUpdatedFirst,
): List<FieldNote> =
    asSequence()
        .filter { note -> note.matchesSearch(searchQuery) }
        .filter { note ->
            when (statusFilter) {
                NoteStatusFilter.All -> true
                NoteStatusFilter.Open -> note.status == NoteStatus.Open
                NoteStatusFilter.Complete -> note.status == NoteStatus.Complete
            }
        }
        .let { notes ->
            when (sortOrder) {
                NoteSortOrder.LastUpdatedFirst -> notes.sortedBy { it.updatedMinutesAgo }
                NoteSortOrder.LastUpdatedLast -> notes.sortedByDescending { it.updatedMinutesAgo }
            }
        }
        .toList()

private fun FieldNote.matchesSearch(query: String): Boolean {
    val trimmedQuery = query.trim()
    if (trimmedQuery.isEmpty()) return true

    return title.contains(trimmedQuery, ignoreCase = true) ||
        description.contains(trimmedQuery, ignoreCase = true) ||
        room.orEmpty().contains(trimmedQuery, ignoreCase = true) ||
        priority.name.contains(trimmedQuery, ignoreCase = true) ||
        status.name.contains(trimmedQuery, ignoreCase = true)
}

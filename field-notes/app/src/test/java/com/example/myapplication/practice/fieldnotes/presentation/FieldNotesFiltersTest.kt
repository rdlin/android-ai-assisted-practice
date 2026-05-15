package com.example.myapplication.practice.fieldnotes.presentation

import com.example.myapplication.practice.fieldnotes.FieldNote
import com.example.myapplication.practice.fieldnotes.NotePriority
import com.example.myapplication.practice.fieldnotes.NoteStatus
import org.junit.Assert.assertEquals
import org.junit.Test

class FieldNotesFiltersTest {
    @Test
    fun sortedAndFilteredBy_defaultsToNewestFirst() {
        val notes = filterTestNotes.shuffled()

        val result = notes.sortedAndFilteredBy()

        assertEquals(listOf("note-1", "note-2", "note-3"), result.map { it.id })
    }

    @Test
    fun sortedAndFilteredBy_canSortOldestFirst() {
        val notes = filterTestNotes

        val result = notes.sortedAndFilteredBy(sortOrder = NoteSortOrder.LastUpdatedLast)

        assertEquals(listOf("note-3", "note-2", "note-1"), result.map { it.id })
    }

    @Test
    fun sortedAndFilteredBy_canFilterOpenNotes() {
        val notes = filterTestNotes

        val result = notes.sortedAndFilteredBy(statusFilter = NoteStatusFilter.Open)

        assertEquals(listOf("note-1", "note-3"), result.map { it.id })
    }

    @Test
    fun sortedAndFilteredBy_canFilterCompleteNotes() {
        val notes = filterTestNotes

        val result = notes.sortedAndFilteredBy(statusFilter = NoteStatusFilter.Complete)

        assertEquals(listOf("note-2"), result.map { it.id })
    }

    @Test
    fun sortedAndFilteredBy_searchesTitleDescriptionAndRoom() {
        val notes = filterTestNotes

        val titleResult = notes.sortedAndFilteredBy(searchQuery = "new")
        val descriptionResult = notes.sortedAndFilteredBy(searchQuery = "COMPLETE")
        val roomResult = notes.sortedAndFilteredBy(searchQuery = "stor")

        assertEquals(listOf("note-1"), titleResult.map { it.id })
        assertEquals(listOf("note-2"), descriptionResult.map { it.id })
        assertEquals(listOf("note-3"), roomResult.map { it.id })
    }

    @Test
    fun sortedAndFilteredBy_searchesPartialPriorityAndStatus() {
        val notes = listOf(
            FieldNote(
                id = "note-1",
                title = "Pump vibration",
                description = "Reading above baseline",
                room = "Mechanical",
                priority = NotePriority.High,
                status = NoteStatus.Open,
                updatedMinutesAgo = 1,
            ),
            FieldNote(
                id = "note-2",
                title = "Exit sign",
                description = "Battery replaced",
                room = "Stairwell",
                priority = NotePriority.Low,
                status = NoteStatus.Complete,
                updatedMinutesAgo = 2,
            ),
        )

        val priorityResult = notes.sortedAndFilteredBy(searchQuery = "hig")
        val statusResult = notes.sortedAndFilteredBy(searchQuery = "compl")

        assertEquals(listOf("note-1"), priorityResult.map { it.id })
        assertEquals(listOf("note-2"), statusResult.map { it.id })
    }

    @Test
    fun sortedAndFilteredBy_combinesSearchWithStatusFilterAndSort() {
        val notes = filterTestNotes

        val result = notes.sortedAndFilteredBy(
            searchQuery = "open",
            statusFilter = NoteStatusFilter.Open,
            sortOrder = NoteSortOrder.LastUpdatedLast,
        )

        assertEquals(listOf("note-3", "note-1"), result.map { it.id })
    }

    private val filterTestNotes = listOf(
        FieldNote(
            id = "note-1",
            title = "Newest open",
            description = "Newest open note",
            room = "A",
            priority = NotePriority.High,
            status = NoteStatus.Open,
            updatedMinutesAgo = 1,
        ),
        FieldNote(
            id = "note-2",
            title = "Middle complete",
            description = "Middle complete note",
            room = "B",
            priority = NotePriority.Medium,
            status = NoteStatus.Complete,
            updatedMinutesAgo = 2,
        ),
        FieldNote(
            id = "note-3",
            title = "Oldest open",
            description = "Oldest open note",
            room = "Storage Wing",
            priority = NotePriority.Low,
            status = NoteStatus.Open,
            updatedMinutesAgo = 3,
        ),
    )
}

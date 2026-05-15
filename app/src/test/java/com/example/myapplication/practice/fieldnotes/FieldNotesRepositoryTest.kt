package com.example.myapplication.practice.fieldnotes

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class FieldNotesRepositoryTest {
    @Test
    fun inMemoryRepository_returnsSeedNotes() {
        val notes = InMemoryFieldNotesRepository().loadNotes()

        assertEquals(3, notes.size)
        assertTrue(notes.any { it.status == NoteStatus.Open })
        assertTrue(notes.any { it.status == NoteStatus.Complete })
    }
}

package com.example.myapplication.practice.fieldnotes

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class FieldNotesRepositoryTest {
    @Test
    fun starterData_containsSeedNotes() {
        val notes = FieldNotesStarterData.notes

        assertEquals(10, notes.size)
        assertTrue(notes.any { it.status == NoteStatus.Open })
        assertTrue(notes.any { it.status == NoteStatus.Complete })
    }
}

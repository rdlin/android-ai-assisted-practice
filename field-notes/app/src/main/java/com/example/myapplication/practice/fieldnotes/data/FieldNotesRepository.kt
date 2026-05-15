package com.example.myapplication.practice.fieldnotes.data

import com.example.myapplication.practice.fieldnotes.FieldNote
import kotlinx.coroutines.flow.Flow

interface FieldNotesRepository {
    val notes: Flow<List<FieldNote>>

    suspend fun seedIfEmpty(notes: List<FieldNote>)

    suspend fun save(note: FieldNote)

    suspend fun delete(noteId: String)
}

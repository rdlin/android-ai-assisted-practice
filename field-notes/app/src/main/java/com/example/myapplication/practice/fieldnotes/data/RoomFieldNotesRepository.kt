package com.example.myapplication.practice.fieldnotes.data

import com.example.myapplication.practice.fieldnotes.FieldNote
import com.example.myapplication.practice.fieldnotes.database.FieldNotesDao
import com.example.myapplication.practice.fieldnotes.database.toEntity
import com.example.myapplication.practice.fieldnotes.database.toFieldNote
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RoomFieldNotesRepository @Inject constructor(
    private val dao: FieldNotesDao,
) : FieldNotesRepository {
    override val notes: Flow<List<FieldNote>> =
        dao.observeAll().map { entities ->
            entities.map { it.toFieldNote() }
        }

    override suspend fun seedIfEmpty(notes: List<FieldNote>) {
        if (dao.count() < notes.size) {
            dao.insertAll(notes.map { it.toEntity() })
        }
    }

    override suspend fun save(note: FieldNote) {
        dao.upsert(note.toEntity())
    }

    override suspend fun delete(noteId: String) {
        dao.deleteById(noteId)
    }
}

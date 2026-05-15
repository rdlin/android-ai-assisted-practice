package com.example.myapplication.practice.fieldnotes.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FieldNotesDao {
    @Query("SELECT COUNT(*) FROM field_notes")
    suspend fun count(): Int

    @Query("SELECT * FROM field_notes ORDER BY updatedMinutesAgo ASC")
    fun observeAll(): Flow<List<FieldNoteEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(notes: List<FieldNoteEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(note: FieldNoteEntity)

    @Query("DELETE FROM field_notes WHERE id = :noteId")
    suspend fun deleteById(noteId: String)
}

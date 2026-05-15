package com.example.myapplication.practice.fieldnotes.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [FieldNoteEntity::class],
    version = 1,
    exportSchema = false,
)
abstract class FieldNotesDatabase : RoomDatabase() {
    abstract fun fieldNotesDao(): FieldNotesDao
}

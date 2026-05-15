package com.example.myapplication.practice.fieldnotes.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.myapplication.practice.fieldnotes.FieldNote

@Entity(tableName = "field_notes")
data class FieldNoteEntity(
    @PrimaryKey val id: String,
    val title: String,
    val description: String,
    val room: String?,
    val priority: String,
    val status: String,
    val updatedMinutesAgo: Int,
)

fun FieldNoteEntity.toFieldNote(): FieldNote =
    FieldNote(
        id = id,
        title = title,
        description = description,
        room = room,
        priority = enumValueOf(priority),
        status = enumValueOf(status),
        updatedMinutesAgo = updatedMinutesAgo,
    )

fun FieldNote.toEntity(): FieldNoteEntity =
    FieldNoteEntity(
        id = id,
        title = title,
        description = description,
        room = room,
        priority = priority.name,
        status = status.name,
        updatedMinutesAgo = updatedMinutesAgo,
    )

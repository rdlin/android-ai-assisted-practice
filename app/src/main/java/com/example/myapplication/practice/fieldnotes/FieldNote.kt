package com.example.myapplication.practice.fieldnotes

enum class NotePriority {
    Low,
    Medium,
    High,
}

enum class NoteStatus {
    Open,
    Complete,
}

data class FieldNote(
    val id: String,
    val title: String,
    val description: String,
    val room: String?,
    val priority: NotePriority,
    val status: NoteStatus,
    val updatedMinutesAgo: Int,
)

interface FieldNotesRepository {
    fun loadNotes(): List<FieldNote>
}

class InMemoryFieldNotesRepository : FieldNotesRepository {
    override fun loadNotes(): List<FieldNote> = FieldNotesStarterData.notes
}

object FieldNotesStarterData {
    val notes = listOf(
        FieldNote(
            id = "note-1",
            title = "Boiler pressure warning",
            description = "Gauge is fluctuating during startup checks.",
            room = "Mechanical B12",
            priority = NotePriority.High,
            status = NoteStatus.Open,
            updatedMinutesAgo = 8,
        ),
        FieldNote(
            id = "note-2",
            title = "Exit sign battery replaced",
            description = "Completed replacement and verified emergency mode.",
            room = "North stairwell",
            priority = NotePriority.Medium,
            status = NoteStatus.Complete,
            updatedMinutesAgo = 36,
        ),
        FieldNote(
            id = "note-3",
            title = "Water stain near vent",
            description = "Needs follow-up after next rainfall.",
            room = "Conference 4A",
            priority = NotePriority.Low,
            status = NoteStatus.Open,
            updatedMinutesAgo = 91,
        ),
    )
}

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
        FieldNote(
            id = "note-4",
            title = "Air handler belt squeal",
            description = "Intermittent squeal during high-speed fan cycle.",
            room = "Roof AHU-2",
            priority = NotePriority.Medium,
            status = NoteStatus.Open,
            updatedMinutesAgo = 124,
        ),
        FieldNote(
            id = "note-5",
            title = "Panel door latch loose",
            description = "Electrical panel closes but latch does not hold firmly.",
            room = "Electrical 2C",
            priority = NotePriority.Low,
            status = NoteStatus.Complete,
            updatedMinutesAgo = 168,
        ),
        FieldNote(
            id = "note-6",
            title = "Pump vibration above baseline",
            description = "Vibration reading is elevated compared with last inspection.",
            room = "Pump Room 1",
            priority = NotePriority.High,
            status = NoteStatus.Open,
            updatedMinutesAgo = 214,
        ),
        FieldNote(
            id = "note-7",
            title = "Thermostat calibration checked",
            description = "Room sensor matched handheld meter within tolerance.",
            room = "Office 3B",
            priority = NotePriority.Low,
            status = NoteStatus.Complete,
            updatedMinutesAgo = 260,
        ),
        FieldNote(
            id = "note-8",
            title = "Condensate tray residue",
            description = "Residue present along tray edge; cleaned and flagged for review.",
            room = "Mechanical 4D",
            priority = NotePriority.Medium,
            status = NoteStatus.Open,
            updatedMinutesAgo = 315,
        ),
        FieldNote(
            id = "note-9",
            title = "Generator fuel level verified",
            description = "Fuel level and transfer switch indicators checked during walkthrough.",
            room = "Generator Yard",
            priority = NotePriority.Medium,
            status = NoteStatus.Complete,
            updatedMinutesAgo = 402,
        ),
        FieldNote(
            id = "note-10",
            title = "Door sensor intermittent",
            description = "Security panel reported intermittent contact during open-close test.",
            room = "Loading Dock",
            priority = NotePriority.High,
            status = NoteStatus.Open,
            updatedMinutesAgo = 487,
        ),
    )
}

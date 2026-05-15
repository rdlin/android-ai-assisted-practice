package com.example.myapplication.practice

object PracticeScenarioRepository {
    val scenarios = listOf(
        PracticeScenario(
            id = "field-notes",
            title = "Offline Field Notes",
            duration = "60 min",
            summary = "Build a small incident note app for field technicians who need reliable local data entry and fast filtering.",
            route = PracticeRoute.FieldNotes.path,
            mustHave = listOf(
                "Create, edit, complete, and delete notes",
                "Persist notes locally across app restarts",
                "Filter by status and search note text",
                "Show empty, loading, and error states",
            ),
            skills = listOf("Compose", "Room", "ViewModel"),
        ),
        PracticeScenario(
            id = "calendar-planner",
            title = "Calendar Day Planner",
            duration = "60 min",
            summary = "Build a calendar day view that handles event creation, overlap detection, and schedule availability.",
            route = PracticeRoute.CalendarPlanner.path,
            mustHave = listOf(
                "Render a day timeline from seeded calendar events",
                "Create and edit events with start/end times",
                "Detect overlapping events and available time slots",
                "Include unit tests for time-window logic",
            ),
            skills = listOf("Time", "Validation", "State"),
        ),
        PracticeScenario(
            id = "receipt-review",
            title = "Receipt Review",
            duration = "60 min",
            summary = "Build an expense receipt review flow with fake OCR, editable fields, validation, and upload retry states.",
            route = PracticeRoute.ReceiptReview.path,
            mustHave = listOf(
                "Parse a seeded receipt into an editable expense draft",
                "Validate merchant, total, date, and category",
                "Model draft, ready, uploading, failed, and submitted states",
                "Persist drafts and make upload retries idempotent",
            ),
            skills = listOf("Forms", "State Machine", "Retries"),
        ),
    )
}

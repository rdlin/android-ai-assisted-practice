package com.example.myapplication.practice

sealed class PracticeRoute(val path: String, val title: String) {
    data object Launcher : PracticeRoute("launcher", "Android AI Practice")
    data object FieldNotes : PracticeRoute("field-notes", "Offline Field Notes")
    data object CalendarPlanner : PracticeRoute("calendar-planner", "Calendar Day Planner")
    data object ReceiptReview : PracticeRoute("receipt-review", "Receipt Review")

    companion object {
        fun fromPath(path: String): PracticeRoute = when (path) {
            FieldNotes.path -> FieldNotes
            CalendarPlanner.path -> CalendarPlanner
            ReceiptReview.path -> ReceiptReview
            else -> Launcher
        }
    }
}

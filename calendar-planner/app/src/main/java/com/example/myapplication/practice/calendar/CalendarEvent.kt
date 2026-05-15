package com.example.myapplication.practice.calendar

data class TimeOfDay(
    val hour: Int,
    val minute: Int,
) : Comparable<TimeOfDay> {
    init {
        require(hour in 0..23) { "hour must be between 0 and 23" }
        require(minute in 0..59) { "minute must be between 0 and 59" }
    }

    val minutesSinceMidnight: Int = hour * 60 + minute

    override fun compareTo(other: TimeOfDay): Int {
        return minutesSinceMidnight.compareTo(other.minutesSinceMidnight)
    }

    override fun toString(): String {
        val paddedHour = hour.toString().padStart(2, '0')
        val paddedMinute = minute.toString().padStart(2, '0')
        return "$paddedHour:$paddedMinute"
    }
}

data class TimeRange(
    val start: TimeOfDay,
    val end: TimeOfDay,
) {
    init {
        require(start < end) { "start must be before end" }
    }

    fun overlaps(other: TimeRange): Boolean {
        return start < other.end && other.start < end
    }
}

data class CalendarEvent(
    val id: String,
    val title: String,
    val location: String?,
    val range: TimeRange,
    val attendees: List<String>,
)

interface CalendarRepository {
    fun loadEvents(): List<CalendarEvent>
}

class InMemoryCalendarRepository : CalendarRepository {
    override fun loadEvents(): List<CalendarEvent> = CalendarStarterData.events
}

object CalendarRules {
    fun findConflicts(events: List<CalendarEvent>, candidate: TimeRange): List<CalendarEvent> {
        return events.filter { it.range.overlaps(candidate) }
    }

    fun sortedEvents(events: List<CalendarEvent>): List<CalendarEvent> {
        return events.sortedBy { it.range.start }
    }
}

object CalendarStarterData {
    val events = listOf(
        CalendarEvent(
            id = "event-1",
            title = "Design review",
            location = "Room 3A",
            range = TimeRange(TimeOfDay(9, 30), TimeOfDay(10, 15)),
            attendees = listOf("Avery", "Sam"),
        ),
        CalendarEvent(
            id = "event-2",
            title = "Customer follow-up",
            location = null,
            range = TimeRange(TimeOfDay(11, 0), TimeOfDay(11, 30)),
            attendees = listOf("Mina"),
        ),
        CalendarEvent(
            id = "event-3",
            title = "Focus block",
            location = "Desk",
            range = TimeRange(TimeOfDay(14, 0), TimeOfDay(15, 30)),
            attendees = emptyList(),
        ),
    )
}

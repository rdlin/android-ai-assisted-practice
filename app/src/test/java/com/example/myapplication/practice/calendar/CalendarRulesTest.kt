package com.example.myapplication.practice.calendar

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class CalendarRulesTest {
    @Test
    fun findConflicts_detectsOverlappingCandidate() {
        val candidate = TimeRange(TimeOfDay(10, 0), TimeOfDay(10, 45))

        val conflicts = CalendarRules.findConflicts(CalendarStarterData.events, candidate)

        assertEquals(listOf("Design review"), conflicts.map { it.title })
    }

    @Test
    fun findConflicts_allowsBackToBackEvents() {
        val candidate = TimeRange(TimeOfDay(10, 15), TimeOfDay(11, 0))

        val conflicts = CalendarRules.findConflicts(CalendarStarterData.events, candidate)

        assertTrue(conflicts.isEmpty())
    }
}

package com.example.myapplication.practice

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class PracticeNavManagerTest {
    @Test
    fun startsAtLauncher() {
        val navManager = PracticeNavManager()

        assertEquals(PracticeRoute.Launcher, navManager.currentRoute)
        assertFalse(navManager.canNavigateBack)
    }

    @Test
    fun navigatesToProblemAndBack() {
        val navManager = PracticeNavManager()

        navManager.navigateTo(PracticeRoute.FieldNotes)

        assertEquals(PracticeRoute.FieldNotes, navManager.currentRoute)
        assertTrue(navManager.canNavigateBack)

        assertTrue(navManager.navigateBack())

        assertEquals(PracticeRoute.Launcher, navManager.currentRoute)
        assertFalse(navManager.canNavigateBack)
    }

    @Test
    fun restoresFromSnapshot() {
        val navManager = PracticeNavManager.fromSnapshot(
            listOf(PracticeRoute.Launcher.path, PracticeRoute.ReceiptReview.path),
        )

        assertEquals(PracticeRoute.ReceiptReview, navManager.currentRoute)
        assertTrue(navManager.canNavigateBack)
    }
}

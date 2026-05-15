package com.example.myapplication.practice

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class PracticeScenarioRepositoryTest {
    @Test
    fun scenarios_areSizedForOneHourPractice() {
        assertEquals(3, PracticeScenarioRepository.scenarios.size)
        assertTrue(PracticeScenarioRepository.scenarios.all { it.duration == "60 min" })
    }

    @Test
    fun scenarios_haveAcceptanceCriteriaAndSkillTags() {
        PracticeScenarioRepository.scenarios.forEach { scenario ->
            assertTrue("${scenario.id} needs multiple requirements", scenario.mustHave.size >= 4)
            assertTrue("${scenario.id} needs skill tags", scenario.skills.size >= 3)
        }
    }

    @Test
    fun scenarios_areWiredToProblemRoutes() {
        val routes = PracticeScenarioRepository.scenarios.map { it.route }

        assertEquals(
            listOf(
                PracticeRoute.FieldNotes.path,
                PracticeRoute.CalendarPlanner.path,
                PracticeRoute.ReceiptReview.path,
            ),
            routes,
        )
    }
}

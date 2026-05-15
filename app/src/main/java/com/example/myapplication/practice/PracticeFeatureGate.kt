package com.example.myapplication.practice

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.myapplication.practice.calendar.CalendarPlannerPracticeScreen
import com.example.myapplication.practice.expenses.ReceiptReviewPracticeScreen
import com.example.myapplication.practice.fieldnotes.FieldNotesPracticeScreen

@Composable
fun PracticeFeatureGate(
    route: PracticeRoute,
    modifier: Modifier = Modifier,
) {
    when (route) {
        PracticeRoute.FieldNotes -> FieldNotesPracticeScreen(modifier = modifier)
        PracticeRoute.CalendarPlanner -> CalendarPlannerPracticeScreen(modifier = modifier)
        PracticeRoute.ReceiptReview -> ReceiptReviewPracticeScreen(modifier = modifier)
        PracticeRoute.Launcher -> Unit
    }
}

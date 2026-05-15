package com.example.myapplication.practice.expenses

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class ExpenseValidatorTest {
    @Test
    fun validate_acceptsParsedStarterReceipt() {
        val draft = FakeReceiptParser().parse(ReceiptStarterData.scan)

        assertEquals(ExpenseValidationResult.Valid, ExpenseValidator.validate(draft))
    }

    @Test
    fun validate_returnsMessagesForMissingFields() {
        val draft = ExpenseDraft(
            receiptId = "receipt-test",
            merchant = "",
            totalCents = 0,
            purchasedOn = "",
            category = null,
            notes = "",
            state = SubmissionState.Draft,
        )

        val result = ExpenseValidator.validate(draft)

        assertTrue(result is ExpenseValidationResult.Invalid)
        assertEquals(4, (result as ExpenseValidationResult.Invalid).messages.size)
    }
}

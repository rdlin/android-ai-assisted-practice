package com.example.myapplication.practice.expenses

enum class ExpenseCategory {
    Meals,
    Travel,
    Office,
    Other,
}

enum class SubmissionState {
    Draft,
    Ready,
    Uploading,
    Failed,
    Submitted,
}

data class ReceiptScan(
    val id: String,
    val imageLabel: String,
    val rawText: String,
)

data class ExpenseDraft(
    val receiptId: String,
    val merchant: String,
    val totalCents: Int,
    val purchasedOn: String,
    val category: ExpenseCategory?,
    val notes: String,
    val state: SubmissionState,
)

sealed interface ExpenseValidationResult {
    data object Valid : ExpenseValidationResult
    data class Invalid(val messages: List<String>) : ExpenseValidationResult
}

interface ReceiptParser {
    fun parse(scan: ReceiptScan): ExpenseDraft
}

class FakeReceiptParser : ReceiptParser {
    override fun parse(scan: ReceiptScan): ExpenseDraft {
        return ExpenseDraft(
            receiptId = scan.id,
            merchant = "Aster Cafe",
            totalCents = 2842,
            purchasedOn = "2026-05-14",
            category = ExpenseCategory.Meals,
            notes = "Parsed from ${scan.imageLabel}",
            state = SubmissionState.Ready,
        )
    }
}

object ExpenseValidator {
    fun validate(draft: ExpenseDraft): ExpenseValidationResult {
        val messages = buildList {
            if (draft.merchant.isBlank()) add("Merchant is required")
            if (draft.totalCents <= 0) add("Total must be greater than zero")
            if (draft.purchasedOn.isBlank()) add("Purchase date is required")
            if (draft.category == null) add("Category is required")
        }

        return if (messages.isEmpty()) {
            ExpenseValidationResult.Valid
        } else {
            ExpenseValidationResult.Invalid(messages)
        }
    }
}

object ReceiptStarterData {
    val scan = ReceiptScan(
        id = "receipt-1",
        imageLabel = "lunch-receipt.jpg",
        rawText = "Aster Cafe\nMay 14 2026\nTotal 28.42",
    )
}

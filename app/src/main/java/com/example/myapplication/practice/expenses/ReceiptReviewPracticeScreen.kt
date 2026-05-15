package com.example.myapplication.practice.expenses

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun ReceiptReviewPracticeScreen(
    modifier: Modifier = Modifier,
    parser: ReceiptParser = remember { FakeReceiptParser() },
) {
    val scan = ReceiptStarterData.scan
    val draft = parser.parse(scan)
    val validation = ExpenseValidator.validate(draft)

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        item {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = "Receipt Review",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.SemiBold,
                )
                Text(
                    text = "Starter code parses a seeded receipt into an expense draft and validates required fields.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }

        item {
            StarterTodoCard(
                todos = listOf(
                    "Replace static parsed fields with editable form state.",
                    "Model draft, uploading, failed, and submitted transitions.",
                    "Persist local drafts and restore them after process death.",
                    "Add idempotent fake upload retries and validation tests.",
                ),
            )
        }

        item {
            ReceiptCard(scan = scan, draft = draft, validation = validation)
        }
    }
}

@Composable
private fun ReceiptCard(
    scan: ReceiptScan,
    draft: ExpenseDraft,
    validation: ExpenseValidationResult,
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer,
        ),
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text(
                text = scan.imageLabel,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
            )
            Text(text = "Merchant: ${draft.merchant}")
            Text(text = "Total: $${draft.totalCents / 100}.${(draft.totalCents % 100).toString().padStart(2, '0')}")
            Text(text = "Date: ${draft.purchasedOn}")
            Text(text = "Category: ${draft.category ?: "Missing"}")
            Text(text = "State: ${draft.state}")
            when (validation) {
                ExpenseValidationResult.Valid -> Text(
                    text = "Ready to submit",
                    color = MaterialTheme.colorScheme.primary,
                )
                is ExpenseValidationResult.Invalid -> validation.messages.forEach { message ->
                    Text(
                        text = "- $message",
                        color = MaterialTheme.colorScheme.error,
                    )
                }
            }
        }
    }
}

@Composable
private fun StarterTodoCard(todos: List<String>) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
        ),
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            Text(
                text = "Starter TODOs",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
            )
            todos.forEach { todo ->
                Text(
                    text = "- $todo",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                )
            }
        }
    }
}

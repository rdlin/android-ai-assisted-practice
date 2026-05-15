package com.example.myapplication.practice.fieldnotes.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.myapplication.practice.fieldnotes.FieldNote
import com.example.myapplication.practice.fieldnotes.NotePriority
import com.example.myapplication.practice.fieldnotes.NoteStatus
import com.example.myapplication.practice.fieldnotes.presentation.FieldNotesEvent

@Composable
fun FieldNoteRow(
    note: FieldNote,
    onEvent: (FieldNotesEvent) -> Unit,
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
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = note.title,
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                )
                Text(
                    text = "${note.updatedMinutesAgo}m ago",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }

            Text(
                text = note.description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )

            WrappingChipRow {
                StatusChip(status = note.status)
                PriorityChip(priority = note.priority)
                note.room?.let { room ->
                    LocationChip(room = room)
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
            ) {
                TextButton(
                    onClick = { onEvent(FieldNotesEvent.EditNoteStarted(note.id)) },
                ) {
                    Text("Edit")
                }
                TextButton(
                    onClick = { onEvent(FieldNotesEvent.DeleteNoteRequested(note.id)) },
                ) {
                    Text("Delete")
                }
            }
        }
    }
}

@Composable
private fun WrappingChipRow(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    val horizontalSpacing = 8.dp
    val verticalSpacing = 4.dp

    Layout(
        content = content,
        modifier = modifier.fillMaxWidth(),
    ) { measurables, constraints ->
        val horizontalSpacingPx = horizontalSpacing.roundToPx()
        val verticalSpacingPx = verticalSpacing.roundToPx()
        val placeables = measurables.map { measurable ->
            measurable.measure(constraints.copy(minWidth = 0))
        }
        val rows = mutableListOf<List<Int>>()
        val rowHeights = mutableListOf<Int>()
        var currentRow = mutableListOf<Int>()
        var currentWidth = 0
        var currentHeight = 0

        placeables.forEachIndexed { index, placeable ->
            val nextWidth = if (currentRow.isEmpty()) {
                placeable.width
            } else {
                currentWidth + horizontalSpacingPx + placeable.width
            }

            if (currentRow.isNotEmpty() && nextWidth > constraints.maxWidth) {
                rows += currentRow
                rowHeights += currentHeight
                currentRow = mutableListOf()
                currentWidth = 0
                currentHeight = 0
            }

            currentRow += index
            currentWidth = if (currentWidth == 0) {
                placeable.width
            } else {
                currentWidth + horizontalSpacingPx + placeable.width
            }
            currentHeight = maxOf(currentHeight, placeable.height)
        }

        if (currentRow.isNotEmpty()) {
            rows += currentRow
            rowHeights += currentHeight
        }

        val height = rowHeights.sum() + verticalSpacingPx * (rowHeights.size - 1).coerceAtLeast(0)

        layout(width = constraints.maxWidth, height = height) {
            var y = 0
            rows.forEachIndexed { rowIndex, row ->
                var x = 0
                row.forEach { placeableIndex ->
                    val placeable = placeables[placeableIndex]
                    placeable.placeRelative(x = x, y = y)
                    x += placeable.width + horizontalSpacingPx
                }
                y += rowHeights[rowIndex] + verticalSpacingPx
            }
        }
    }
}

@Composable
private fun StatusChip(status: NoteStatus) {
    val containerColor = when (status) {
        NoteStatus.Open -> MaterialTheme.colorScheme.primaryContainer
        NoteStatus.Complete -> MaterialTheme.colorScheme.tertiaryContainer
    }
    val labelColor = when (status) {
        NoteStatus.Open -> MaterialTheme.colorScheme.onPrimaryContainer
        NoteStatus.Complete -> MaterialTheme.colorScheme.onTertiaryContainer
    }

    SuggestionChip(
        onClick = {},
        label = { Text("Status: ${status.name}") },
        colors = SuggestionChipDefaults.suggestionChipColors(
            containerColor = containerColor,
            labelColor = labelColor,
        ),
    )
}

@Composable
private fun PriorityChip(priority: NotePriority) {
    val containerColor = when (priority) {
        NotePriority.High -> MaterialTheme.colorScheme.errorContainer
        NotePriority.Medium -> MaterialTheme.colorScheme.secondaryContainer
        NotePriority.Low -> MaterialTheme.colorScheme.surfaceVariant
    }
    val labelColor = when (priority) {
        NotePriority.High -> MaterialTheme.colorScheme.onErrorContainer
        NotePriority.Medium -> MaterialTheme.colorScheme.onSecondaryContainer
        NotePriority.Low -> MaterialTheme.colorScheme.onSurfaceVariant
    }

    SuggestionChip(
        onClick = {},
        label = { Text("Priority: ${priority.name}") },
        colors = SuggestionChipDefaults.suggestionChipColors(
            containerColor = containerColor,
            labelColor = labelColor,
        ),
    )
}

@Composable
private fun LocationChip(room: String) {
    AssistChip(
        onClick = {},
        label = { Text("Room: $room") },
    )
}

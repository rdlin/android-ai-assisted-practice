package com.example.myapplication.practice.fieldnotes.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.myapplication.practice.fieldnotes.NotePriority
import com.example.myapplication.practice.fieldnotes.presentation.FieldNoteDraft
import com.example.myapplication.practice.fieldnotes.presentation.FieldNotesEvent

@Composable
fun AddFieldNoteScreen(
    modifier: Modifier,
    title: String,
    draft: FieldNoteDraft,
    onEvent: (FieldNotesEvent) -> Unit,
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        item {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.SemiBold,
            )
        }

        item {
            OutlinedTextField(
                value = draft.title,
                onValueChange = { onEvent(FieldNotesEvent.DraftTitleChanged(it)) },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Title") },
                singleLine = true,
            )
        }

        item {
            OutlinedTextField(
                value = draft.description,
                onValueChange = { onEvent(FieldNotesEvent.DraftDescriptionChanged(it)) },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Description") },
                minLines = 3,
            )
        }

        item {
            OutlinedTextField(
                value = draft.room,
                onValueChange = { onEvent(FieldNotesEvent.DraftRoomChanged(it)) },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Room / location") },
                singleLine = true,
            )
        }

        item {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = "Priority",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    NotePriority.entries.forEach { priority ->
                        FilterChip(
                            selected = draft.priority == priority,
                            onClick = { onEvent(FieldNotesEvent.DraftPriorityChanged(priority)) },
                            label = { Text(priority.name) },
                        )
                    }
                }
            }
        }

        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                OutlinedButton(
                    onClick = { onEvent(FieldNotesEvent.DraftCancelled) },
                    modifier = Modifier.weight(1f),
                ) {
                    Text("Cancel")
                }
                Button(
                    onClick = { onEvent(FieldNotesEvent.DraftSaved) },
                    enabled = draft.canSave,
                    modifier = Modifier.weight(1f),
                ) {
                    Text("Save")
                }
            }
        }
    }
}

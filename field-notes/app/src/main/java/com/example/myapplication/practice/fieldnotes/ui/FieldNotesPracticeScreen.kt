package com.example.myapplication.practice.fieldnotes.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.myapplication.practice.fieldnotes.presentation.FieldNotesEvent
import com.example.myapplication.practice.fieldnotes.presentation.FieldNotesScreenMode
import com.example.myapplication.practice.fieldnotes.presentation.FieldNotesViewModel

@Composable
fun FieldNotesPracticeScreen(
    modifier: Modifier = Modifier,
    viewModel: FieldNotesViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val pendingDeleteNote = uiState.notes.firstOrNull { it.id == uiState.pendingDeleteNoteId }

    if (pendingDeleteNote != null) {
        AlertDialog(
            onDismissRequest = { viewModel.onEvent(FieldNotesEvent.DeleteNoteCancelled) },
            title = { Text("Delete note?") },
            text = { Text("Delete \"${pendingDeleteNote.title}\"? This cannot be undone.") },
            confirmButton = {
                TextButton(
                    onClick = { viewModel.onEvent(FieldNotesEvent.DeleteNoteConfirmed) },
                ) {
                    Text("Delete")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { viewModel.onEvent(FieldNotesEvent.DeleteNoteCancelled) },
                ) {
                    Text("Cancel")
                }
            },
        )
    }

    if (uiState.screenMode == FieldNotesScreenMode.Add || uiState.screenMode == FieldNotesScreenMode.Edit) {
        AddFieldNoteScreen(
            modifier = modifier,
            title = if (uiState.screenMode == FieldNotesScreenMode.Edit) {
                "Edit Field Note"
            } else {
                "Add Field Note"
            },
            draft = uiState.draft,
            onEvent = viewModel::onEvent,
        )
        return
    }

    Box(modifier = modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                start = 16.dp,
                top = 16.dp,
                end = 16.dp,
                bottom = 88.dp,
            ),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            item {
                FieldNotesControls(
                    uiState = uiState,
                    onEvent = viewModel::onEvent,
                )
            }

            items(uiState.notes, key = { it.id }) { note ->
                FieldNoteRow(
                    note = note,
                    onEvent = viewModel::onEvent,
                )
            }
        }

        FloatingActionButton(
            onClick = { viewModel.onEvent(FieldNotesEvent.AddNoteStarted) },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
        ) {
            Text("+")
        }
    }
}

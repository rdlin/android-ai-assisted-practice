package com.example.myapplication.practice.fieldnotes.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapplication.practice.fieldnotes.presentation.FieldNotesEvent
import com.example.myapplication.practice.fieldnotes.presentation.FieldNotesUiState
import com.example.myapplication.practice.fieldnotes.presentation.NoteSortOrder
import com.example.myapplication.practice.fieldnotes.presentation.NoteStatusFilter

@Composable
fun FieldNotesControls(
    uiState: FieldNotesUiState,
    onEvent: (FieldNotesEvent) -> Unit,
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            OutlinedTextField(
                value = uiState.searchQuery,
                onValueChange = { query ->
                    onEvent(FieldNotesEvent.SearchQueryChanged(query))
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                label = { Text("Search") },
            )

            StatusAndSortRow(
                uiState = uiState,
                onEvent = onEvent,
            )
        }
    }
}

@Composable
private fun StatusAndSortRow(
    uiState: FieldNotesUiState,
    onEvent: (FieldNotesEvent) -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        NoteStatusFilter.entries.forEach { filter ->
            FilterChip(
                selected = uiState.statusFilter == filter,
                onClick = { onEvent(FieldNotesEvent.StatusFilterSelected(filter)) },
                label = { Text(filter.name) },
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        SortDropdown(
            sortOrder = uiState.sortOrder,
            onSortSelected = { sortOrder ->
                onEvent(FieldNotesEvent.SortOrderSelected(sortOrder))
            },
        )
    }
}

@Composable
private fun SortDropdown(
    sortOrder: NoteSortOrder,
    onSortSelected: (NoteSortOrder) -> Unit,
) {
    val expanded = remember { mutableStateOf(false) }

    TextButton(onClick = { expanded.value = true }) {
        Text("Sort: ${sortOrder.label}")
    }
    DropdownMenu(
        expanded = expanded.value,
        onDismissRequest = { expanded.value = false },
    ) {
        NoteSortOrder.entries.forEach { order ->
            DropdownMenuItem(
                text = { Text(order.label) },
                onClick = {
                    onSortSelected(order)
                    expanded.value = false
                },
            )
        }
    }
}

private val NoteSortOrder.label: String
    get() = when (this) {
        NoteSortOrder.LastUpdatedFirst -> "Newest"
        NoteSortOrder.LastUpdatedLast -> "Oldest"
    }

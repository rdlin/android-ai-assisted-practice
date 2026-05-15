package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.practice.PracticeFeatureGate
import com.example.myapplication.practice.PracticeNavManager
import com.example.myapplication.practice.PracticeRoute
import com.example.myapplication.practice.PracticeScenario
import com.example.myapplication.practice.PracticeScenarioRepository
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                PracticeApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PracticeApp(
    scenarios: List<PracticeScenario> = PracticeScenarioRepository.scenarios,
) {
    var navSnapshot by rememberSaveable {
        mutableStateOf(listOf(PracticeRoute.Launcher.path))
    }
    val navManager = remember(navSnapshot) {
        PracticeNavManager.fromSnapshot(navSnapshot)
    }
    val currentRoute = navManager.currentRoute

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = currentRoute.title,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                },
                navigationIcon = {
                    if (navManager.canNavigateBack) {
                        TextButton(
                            onClick = {
                                navManager.navigateBack()
                                navSnapshot = navManager.snapshot()
                            },
                        ) {
                            Text("Back")
                        }
                    }
                },
            )
        },
    ) { innerPadding ->
        if (currentRoute == PracticeRoute.Launcher) {
            ScenarioList(
                scenarios = scenarios,
                onScenarioSelected = { scenario ->
                    navManager.navigateTo(PracticeRoute.fromPath(scenario.route))
                    navSnapshot = navManager.snapshot()
                },
                modifier = Modifier.padding(innerPadding),
            )
        } else {
            PracticeFeatureGate(
                route = currentRoute,
                modifier = Modifier.padding(innerPadding),
            )
        }
    }
}

@Composable
private fun ScenarioList(
    scenarios: List<PracticeScenario>,
    onScenarioSelected: (PracticeScenario) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        item {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = "One-hour prompts",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.SemiBold,
                )
                Text(
                    text = "Use these to practice turning an ambiguous Android feature request into a working Compose app with state, persistence, and tests.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }

        items(scenarios, key = { it.id }) { scenario ->
            ScenarioCard(
                scenario = scenario,
                onClick = { onScenarioSelected(scenario) },
            )
        }
    }
}

@Composable
private fun ScenarioCard(
    scenario: PracticeScenario,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer,
        ),
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = scenario.title,
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                )
                Text(
                    text = scenario.duration,
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary,
                )
            }

            Text(
                text = scenario.summary,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )

            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                scenario.mustHave.take(3).forEach { requirement ->
                    Text(
                        text = "- $requirement",
                        style = MaterialTheme.typography.bodySmall,
                    )
                }
            }

            Spacer(modifier = Modifier.height(2.dp))

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                scenario.skills.forEach { skill ->
                    AssistChip(
                        onClick = {},
                        label = {
                            Text(
                                text = skill,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                            )
                        },
                    )
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
            ) {
                Button(onClick = onClick) {
                    Text("Open")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PracticeAppPreview() {
    MyApplicationTheme {
        Surface {
            PracticeApp()
        }
    }
}

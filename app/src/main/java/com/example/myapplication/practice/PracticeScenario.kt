package com.example.myapplication.practice

data class PracticeScenario(
    val id: String,
    val title: String,
    val duration: String,
    val summary: String,
    val route: String,
    val mustHave: List<String>,
    val skills: List<String>,
)

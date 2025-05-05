package com.example.pattern.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pattern.ui.components.ExperienceLevelCard
import com.example.pattern.ui.components.ExtraCard
import com.example.pattern.ui.components.ProfileStatCard

@Preview
@Composable
fun ProfileScreenPreview() {
    ProfileScreen()
}

@Composable
fun ProfileScreen() {
    val scroll = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scroll)
            .background(MaterialTheme.colorScheme.background)
            .padding(vertical = 12.dp)
    ) {
        ExtraCard()
        ExperienceLevelCard(
            title = "Your Progress Score",
            percentage = 0.75f,
            number = 100,
        )
        ProfileStatCard(
            title = "Total Completed Habits",
            percentage = 1f,
            number = 200,
            label = "Total Habits"
        )

        ProfileStatCard(
            title = "Success Rate",
            percentage = 0.9f,
            number = 100,
            label = "Success Score"
        )
    }
}



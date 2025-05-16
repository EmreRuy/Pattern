package com.example.pattern.ui.screens.addHabitScreen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HabitDetailsCard(habitName: String, onNameChange: (String) -> Unit) {
    Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp)) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text("Habit Details", style = MaterialTheme.typography.titleMedium)
            OutlinedTextField(
                value = habitName,
                onValueChange = {
                    onNameChange(it.split(" ").joinToString(" ") { word ->
                        word.replaceFirstChar { char -> char.titlecase() }
                    })
                },
                label = { Text("Habit Name") },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
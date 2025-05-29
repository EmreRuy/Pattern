package com.example.pattern.ui.screens.addHabitScreen.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import java.time.DayOfWeek
import java.time.LocalTime

@Composable
fun HabitTypeSelectorModern(
    selectedType: String,
    onTypeChange: (String) -> Unit,
    selectedTime: LocalTime,
    onTimeChange: (LocalTime) -> Unit,
    selectedDays: List<DayOfWeek>,
    onDaysChange: (List<DayOfWeek>) -> Unit
) {
    val habitTypes = listOf(
        "Build" to "🚀",
        "Quit" to "🛑",
        "Task" to "🔁"
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Select Habit Type",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            habitTypes.forEach { (type, emoji) ->
                val isSelected = selectedType == type

                Surface(
                    onClick = { onTypeChange(type) },
                    shape = RoundedCornerShape(50),
                    color = if (isSelected)
                        MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)
                    else
                        MaterialTheme.colorScheme.surface,
                    tonalElevation = if (isSelected) 4.dp else 0.dp,
                    border = if (!isSelected) BorderStroke(
                        1.dp,
                        MaterialTheme.colorScheme.outline
                    ) else null
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    ) {
                        Text(
                            text = emoji,
                            modifier = Modifier.padding(end = 8.dp)
                        )
                        Text(
                            text = type,
                            color = if (isSelected)
                                MaterialTheme.colorScheme.primary
                            else
                                MaterialTheme.colorScheme.onSurfaceVariant,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }

        when (selectedType) {
            "Build" -> {
                BuildTypeOfHabit(
                    selectedTime = selectedTime,
                    onTimeChange = onTimeChange,
                    selectedDays = selectedDays,
                    onDaysChange = onDaysChange
                )
            }

            "Quit" -> {
                Text(
                    text = "You're trying to quit something 🚫",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            "Task" -> {
                Text(
                    text = "You're managing a recurring task 🔁",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}



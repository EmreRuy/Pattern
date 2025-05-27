package com.example.pattern.ui.screens.addHabitScreen.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun HabitTypeSelectorModern(
    selectedType: String,
    onTypeChange: (String) -> Unit
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
                val backgroundColor = if (isSelected)
                    MaterialTheme.colorScheme.primaryContainer
                else
                    MaterialTheme.colorScheme.surface

                val contentColor = if (isSelected)
                    MaterialTheme.colorScheme.onPrimaryContainer
                else
                    MaterialTheme.colorScheme.onSurfaceVariant

                Surface(
                    onClick = { onTypeChange(type) },
                    shape = RoundedCornerShape(50),
                    color = backgroundColor,
                    tonalElevation = if (isSelected) 4.dp else 0.dp,
                    border = if (!isSelected) BorderStroke(1.dp, MaterialTheme.colorScheme.outline) else null
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
                            color = contentColor,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }
        when (selectedType) {
            "Build" -> {
                Text(
                    text = "You're trying to build a habit 💪",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                // You can add more input fields here (e.g., goal description, slider)
            }
            "Quit" -> {
                Text(
                    text = "You're trying to quit something 🚫",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                // Maybe: TextField for trigger, reason, or journal
            }
            "Task" -> {
                Text(
                    text = "You're managing a recurring task 🔁",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                // Example: estimated time field or checklist
            }
        }
    }
}


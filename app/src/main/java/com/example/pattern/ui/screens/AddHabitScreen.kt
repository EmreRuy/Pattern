package com.example.pattern.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddHabitScreen(onSave: () -> Unit) {
    var habitName by remember { mutableStateOf("") }
    var habitType by remember { mutableStateOf("Build") }
    var frequency by remember { mutableStateOf("Daily") }
    var reminderEnabled by remember { mutableStateOf(false) }
    var reminderTime by remember { mutableStateOf(LocalTime.now()) }
    var emoji by remember { mutableStateOf("🔥") }
    var motivationNote by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Add New Habit") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onSave) {
                Icon(Icons.Default.Check, contentDescription = "Save")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = habitName,
                onValueChange = {
                    habitName = it.split(" ").joinToString(" ") { word ->
                        word.replaceFirstChar { char ->
                            if (char.isLowerCase()) char.titlecase() else char.toString()
                        }
                    }
                },
                label = { Text("Habit Name") },
                shape = RoundedCornerShape(24.dp),
                modifier = Modifier.fillMaxWidth()
            )

            Text("Habit Type", style = MaterialTheme.typography.labelLarge)
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                listOf("Build", "Quit", "Reinforce").forEach { type ->
                    FilterChip(
                        selected = habitType == type,
                        onClick = { habitType = type },
                        label = { Text(type) },
                    )
                }
            }

            Text("Frequency", style = MaterialTheme.typography.labelLarge)
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                listOf("Daily", "Weekly", "Weekdays", "Custom").forEach { freq ->
                    AssistChip(
                        onClick = { frequency = freq },
                        label = { Text(freq) },
                        leadingIcon = if (frequency == freq) {
                            { Icon(Icons.Default.Check, contentDescription = null) }
                        } else null
                    )
                }
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Reminder", modifier = Modifier.weight(1f))
                Switch(checked = reminderEnabled, onCheckedChange = { reminderEnabled = it })
            }

            if (reminderEnabled) {
                OutlinedTextField(
                    value = reminderTime.toString(),
                    onValueChange = {},
                    label = { Text("Reminder Time") },
                    enabled = false,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Text("Emoji/Icon", style = MaterialTheme.typography.labelLarge)
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                listOf("🔥", "🏃", "📚", "💧", "🌿").forEach { icon ->
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .background(if (emoji == icon) Color.LightGray else Color.Transparent)
                            .clickable { emoji = icon },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(icon, fontSize = 24.sp)
                    }
                }
            }
            OutlinedTextField(
                value = motivationNote,
                onValueChange = { motivationNote = it },
                label = { Text("Why are you doing this?") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = false,
                maxLines = 3
            )
        }
    }
}
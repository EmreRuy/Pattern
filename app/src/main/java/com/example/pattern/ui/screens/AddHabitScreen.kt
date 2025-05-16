package com.example.pattern.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = onSave,
                icon = { Icon(Icons.Default.Check, contentDescription = "Save") },
                text = { Text("Save Habit") }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            HabitDetailsCard(habitName) { habitName = it }
            HabitTypeSelector(habitType) { habitType = it }
            FrequencySelector(frequency) { frequency = it }
            ReminderCard(
                reminderEnabled, reminderTime,
                onToggle = { reminderEnabled = it }
            )
            EmojiSelector(emoji) { emoji = it }
            MotivationInput(motivationNote) { motivationNote = it }
        }
    }
}

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

@Composable
fun HabitTypeSelector(selectedType: String, onTypeChange: (String) -> Unit) {
    Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp)) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text("Type", style = MaterialTheme.typography.titleMedium)
            FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                listOf("Build", "Quit", "Reinforce").forEach { type ->
                    FilterChip(
                        selected = selectedType == type,
                        onClick = { onTypeChange(type) },
                        label = { Text(type) }
                    )
                }
            }
        }
    }
}

@Composable
fun FrequencySelector(selectedFrequency: String, onFrequencyChange: (String) -> Unit) {
    Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp)) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text("Frequency", style = MaterialTheme.typography.titleMedium)
            FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                listOf("Daily", "Weekly", "Weekdays", "Custom").forEach { freq ->
                    AssistChip(
                        onClick = { onFrequencyChange(freq) },
                        label = { Text(freq) },
                        leadingIcon = if (selectedFrequency == freq) {
                            { Icon(Icons.Default.Check, contentDescription = null) }
                        } else null
                    )
                }
            }
        }
    }
}

@Composable
fun ReminderCard(
    reminderEnabled: Boolean,
    reminderTime: LocalTime,
    onToggle: (Boolean) -> Unit
) {
    Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp)) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Reminder", modifier = Modifier.weight(1f))
                Switch(checked = reminderEnabled, onCheckedChange = onToggle)
            }
            if (reminderEnabled) {
                OutlinedTextField(
                    value = reminderTime.toString(),
                    onValueChange = {},
                    enabled = false,
                    label = { Text("Reminder Time") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
fun EmojiSelector(selectedEmoji: String, onEmojiChange: (String) -> Unit) {
    Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp)) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text("Choose an Icon", style = MaterialTheme.typography.titleMedium)
            FlowRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                listOf("🔥", "🏃", "📚", "💧", "🌿", "😴", "🧘").forEach { icon ->
                    Surface(
                        shape = CircleShape,
                        color = if (selectedEmoji == icon)
                            MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)
                        else MaterialTheme.colorScheme.surface,
                        tonalElevation = if (selectedEmoji == icon) 4.dp else 0.dp,
                        modifier = Modifier
                            .size(48.dp)
                            .clickable { onEmojiChange(icon) }
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Text(icon, fontSize = 24.sp)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MotivationInput(note: String, onNoteChange: (String) -> Unit) {
    Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Motivation", style = MaterialTheme.typography.titleMedium)
            OutlinedTextField(
                value = note,
                onValueChange = onNoteChange,
                label = { Text("Why are you doing this?") },
                modifier = Modifier.fillMaxWidth(),
                maxLines = 3,
                singleLine = false
            )
        }
    }
}

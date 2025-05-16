package com.example.pattern.ui.screens.addHabitScreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pattern.ui.screens.addHabitScreen.components.EmojiSelector
import com.example.pattern.ui.screens.addHabitScreen.components.FrequencySelector
import com.example.pattern.ui.screens.addHabitScreen.components.HabitDetailsCard
import com.example.pattern.ui.screens.addHabitScreen.components.HabitTypeSelector
import com.example.pattern.ui.screens.addHabitScreen.components.MotivationInput
import com.example.pattern.ui.screens.addHabitScreen.components.ReminderCard
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



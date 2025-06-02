package com.example.pattern.ui.screens.profileScreen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun WheelDurationPicker(
    durationHours: Int,
    durationMinutes: Int,
    onDurationChange: (Int, Int) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            WheelPicker(
                items = (0..23).map { it.toString().padStart(2, '0') },
                selectedIndex = durationHours,
                onSelectedIndexChange = { onDurationChange(it, durationMinutes) }
            )
            Text("Hours", style = MaterialTheme.typography.labelSmall)
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            WheelPicker(
                items = (0..59 step 5).map { it.toString().padStart(2, '0') },
                selectedIndex = durationMinutes / 5,
                onSelectedIndexChange = { onDurationChange(durationHours, it * 5) }
            )
            Text("Minutes", style = MaterialTheme.typography.labelSmall)
        }
    }
}

package com.example.pattern.ui.components

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.vector.ImageVector
import java.time.DayOfWeek

data class Habit(
    val name: String,
    val icon: ImageVector,
    var isChecked: MutableState<Boolean> = mutableStateOf(false),
    var isTimeChecked: MutableState<Boolean> = mutableStateOf(false)
)
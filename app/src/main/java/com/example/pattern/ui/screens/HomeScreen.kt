package com.example.pattern.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pattern.R
import com.example.pattern.ui.components.ConfettiView
import com.example.pattern.ui.components.Habit
import kotlinx.coroutines.delay

@Preview(showBackground = true)
@Composable
fun PreviewOfHomeScreen() {
    HomeScreen()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    val selectedDay = remember { mutableIntStateOf(0) }
    var explodeConfetti by remember { mutableStateOf(false) }
    Box(modifier = Modifier.fillMaxSize()) {
        ConfettiView(
            explodeConfetti = explodeConfetti,
            explodeConfettiCallback = { explodeConfetti = false }
        ) {
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                topBar = {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.background)
                            .padding(vertical = 8.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 8.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.menu),
                                contentDescription = "Menu Icon",
                                modifier = Modifier.size(32.dp),
                                tint = MaterialTheme.colorScheme.onBackground
                            )
                            Text(
                                text = stringResource(id = R.string.app_name),
                                fontSize = 22.sp,
                                fontFamily = MaterialTheme.typography.bodyMedium.fontFamily,
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialTheme.colorScheme.onBackground
                            )
                            Icon(
                                painter = painterResource(id = R.drawable.settings),
                                contentDescription = "Settings Icon",
                                modifier = Modifier.size(32.dp),
                                tint = MaterialTheme.colorScheme.onBackground
                            )
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        LazyRow(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 8.dp),
                            contentPadding = PaddingValues(end = 8.dp)
                        ) {
                            items(365) { index ->
                                Column(modifier = Modifier.padding(end = 8.dp)) {
                                    Box(
                                        modifier = Modifier
                                            .width(120.dp)
                                            .clickable { selectedDay.intValue = index }
                                            .background(
                                                if (selectedDay.intValue == index)
                                                    MaterialTheme.colorScheme.primary.copy(alpha = 0.3f) // Highlights if selected
                                                else
                                                    MaterialTheme.colorScheme.primary.copy(alpha = 0.1f), // if not then normal
                                                shape = RoundedCornerShape(12.dp)
                                            )
                                            .padding(horizontal = 12.dp, vertical = 6.dp),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = "April $index", // will be Replaced with real day data like "Mon 1", "Tue 2", etc.
                                            modifier = Modifier
                                                .padding(horizontal = 12.dp, vertical = 8.dp),
                                            color = MaterialTheme.colorScheme.onBackground
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            ) { paddingValues ->
                val allHabits = remember {
                    listOf(
                        Habit("Read 10 pages", Icons.Default.CheckCircle),
                        Habit("Meditate", Icons.Default.Build),
                        Habit("Workout", Icons.Default.AccountBox),
                        Habit("Drink water", Icons.Default.AddCircle),
                        Habit("Listen Music", Icons.Default.Call),
                        Habit("Write a journal", Icons.Default.Create),
                        Habit("Write a journal", Icons.Default.Create),
                        Habit("Write a journal", Icons.Default.Create),
                        Habit("Write a journal", Icons.Default.Create),
                        Habit("Write a journal", Icons.Default.Create),
                        Habit("Write a journal", Icons.Default.Create),
                        Habit("Write a journal", Icons.Default.Create),
                        Habit("Write a journal", Icons.Default.Create),
                        Habit("Write a journal", Icons.Default.Create),
                        Habit("Write a journal", Icons.Default.Create),
                        Habit("Write a journal", Icons.Default.Create),
                        Habit("Write a journal", Icons.Default.Create),
                        Habit("Dance Practice", Icons.Default.DateRange),
                        Habit("Drawing course", Icons.Default.Email)
                    )
                }
                val habits = allHabits.filterIndexed { index, _ ->
                    (index + selectedDay.intValue) % 2 == 0
                }
                val scroll = rememberScrollState()
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            MaterialTheme.colorScheme.onBackground.copy(alpha = 0.05f)
                        )
                        .padding(paddingValues)
                        .padding(horizontal = 16.dp, vertical = 20.dp),
                    shape = RoundedCornerShape(24.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    ),
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .verticalScroll(scroll)
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Top
                    ) {
                        Text(
                            modifier = Modifier.padding(10.dp),
                            text = "My Habits",
                            fontSize = 22.sp,
                            fontFamily = MaterialTheme.typography.bodyMedium.fontFamily,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                        habits.forEach { habit ->
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .heightIn(min = 85.dp)
                                    .padding(vertical = 10.dp),
                                shape = RoundedCornerShape(24.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                                ),
                                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                            ) {
                                Row(
                                    modifier = Modifier
                                        .padding(16.dp)
                                        .fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Icon(
                                            imageVector = habit.icon,
                                            contentDescription = null,
                                            tint = MaterialTheme.colorScheme.primary,
                                            modifier = Modifier.size(32.dp)
                                        )
                                        Spacer(modifier = Modifier.width(12.dp))
                                        Text(
                                            text = habit.name,
                                            style = MaterialTheme.typography.bodyLarge,
                                            fontWeight = FontWeight.SemiBold,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    }
                                    Icon(
                                        imageVector = if (habit.isChecked.value) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                                        contentDescription = null,
                                        tint = if (habit.isChecked.value) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline,
                                        modifier = Modifier
                                            .size(28.dp)
                                            .clickable {
                                                habit.isChecked.value = !habit.isChecked.value
                                            }
                                    )
                                    LaunchedEffect(habit.isChecked.value) {
                                        if (habit.isChecked.value) {
                                            delay(300)
                                            explodeConfetti = true
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}


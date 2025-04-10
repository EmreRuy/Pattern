package com.example.pattern.ui.navigation

import androidx.compose.runtime.Composable
import com.example.pattern.ui.screens.HomeScreen

@Composable
fun NavHost(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreen() // ← This gets displayed first
        }

        // Add more screens like:
        // composable("details") { DetailsScreen() }
    }
}
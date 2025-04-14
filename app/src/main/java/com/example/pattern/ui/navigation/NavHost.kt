package com.example.pattern.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.pattern.ui.screens.HomeScreen

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavHost(
    navController: NavHostController
) {
    // Setup the NavHost with the correct controller
    NavHost(
        navController = navController, // Pass the navController here
        startDestination = Screens.Home.route
    ) {
        composable(Screens.Home.route) {
            HomeScreen() // Implement the Home Screen
        }
        composable(Screens.Search.route) {
           // SearchScreen() // Implement the Search Screen UI
        }
        composable(Screens.Profile.route) {
            // ProfileScreen() // Implement the Profile Screen UI
        }
    }
}


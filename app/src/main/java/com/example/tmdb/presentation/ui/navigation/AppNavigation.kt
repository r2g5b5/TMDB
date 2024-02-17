package com.example.tmdb.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.tmdb.presentation.feature.movie.compose.MoviesScreen


@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Destinations.MoviesScreen.route
    ) {
        composable(Destinations.MoviesScreen.route) { MoviesScreen(navController = navController) }

    }
}
package com.example.tmdb.presentation.ui.navigation

sealed class Destinations(val route: String) {
    data object MoviesScreen : Destinations(DestinationRoutes.MOVIES_LIST.name)
}
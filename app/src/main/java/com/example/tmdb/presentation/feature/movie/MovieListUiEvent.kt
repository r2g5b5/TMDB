package com.example.tmdb.presentation.feature.movie

sealed interface MovieListUiEvent {
     data object Paginate : MovieListUiEvent
     data object Retry : MovieListUiEvent
}
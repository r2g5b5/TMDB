package com.example.tmdb.presentation.feature.movie

sealed interface MovieListUiEvent {
     class Paginate() : MovieListUiEvent
}
package com.example.tmdb.presentation.feature.movie

import com.example.tmdb.domain.model.movie.Movie

data class MovieListState(
    val isLoading: Boolean = false,
    val upcomingMovieListPage: Int = 1,
    val upcomingMovieList: List<Movie> = emptyList()
)
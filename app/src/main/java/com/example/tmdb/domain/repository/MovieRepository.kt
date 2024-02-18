package com.example.tmdb.domain.repository

import com.example.tmdb.domain.model.movie.Movie
import com.example.tmdb.utils.Resource
import kotlinx.coroutines.flow.Flow


interface MovieRepository {
    suspend fun getMovieList(
        forceFetchFromRemote: Boolean,
        page: Int
    ): Flow<Resource<List<Movie>>>
}
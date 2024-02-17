package com.example.tmdb.data.repository.movie

import com.example.tmdb.data.remote.api.movie.MovieApi
import com.example.tmdb.data.remote.dto.movie.MovieDto
import com.example.tmdb.data.remote.retrofit.api_request.PageRequest
import com.example.tmdb.data.remote.retrofit.api_response.PageResult
import com.example.tmdb.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val api: MovieApi,
) : MovieRepository {
    override suspend fun getMovies(filter: PageRequest): PageResult<MovieDto> {
        return api.getMovies(filter)
    }
}
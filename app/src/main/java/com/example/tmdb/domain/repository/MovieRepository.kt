package com.example.tmdb.domain.repository

import com.example.tmdb.data.remote.dto.movie.MovieDto
import com.example.tmdb.data.remote.retrofit.api_request.PageRequest
import com.example.tmdb.data.remote.retrofit.api_response.PageResult


interface MovieRepository {
    suspend fun getMovies(filter: PageRequest): PageResult<MovieDto>
}
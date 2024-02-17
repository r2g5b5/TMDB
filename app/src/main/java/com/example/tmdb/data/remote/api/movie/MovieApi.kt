package com.example.tmdb.data.remote.api.movie

import com.example.tmdb.data.remote.dto.movie.MovieDto
import com.example.tmdb.data.remote.retrofit.api_request.PageRequest
import com.example.tmdb.data.remote.retrofit.api_response.PageResult
import retrofit2.http.Body
import retrofit2.http.POST

interface MovieApi {
    @POST("movie/upcoming")
    suspend fun getMovies(@Body filter: PageRequest): PageResult<MovieDto>

}
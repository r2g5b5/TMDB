package com.example.tmdb.data.remote.api.movie

import com.example.tmdb.data.remote.dto.movie.MovieListDto
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
    @GET("movie/upcoming")
    suspend fun getMovies(
        @Query("page") page: Int
    ): MovieListDto

}
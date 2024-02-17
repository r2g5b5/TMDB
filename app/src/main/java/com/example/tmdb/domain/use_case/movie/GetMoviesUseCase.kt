package com.example.tmdb.domain.use_case.movie

import com.example.tmdb.data.remote.dto.movie.toMovie
import com.example.tmdb.data.remote.retrofit.api_request.PageRequest
import com.example.tmdb.data.remote.retrofit.api_response.ResponseError
import com.example.tmdb.data.remote.retrofit.response_handler.ResponseHandler
import com.example.tmdb.domain.model.movie.Movie
import com.example.tmdb.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(
    private val repository: MovieRepository,
) {

    operator fun invoke(filter: PageRequest): Flow<ResponseHandler<List<Movie>>> = flow {
        try {
            emit(ResponseHandler.Loading())
            val result = repository.getMovies(filter)
            emit(ResponseHandler.Success(data = result.results.map { it.toMovie() }))

        } catch (e: HttpException) {
            emit(
                ResponseHandler.Error(
                    errors = listOf(
                        ResponseError(
                            description = e.localizedMessage ?: "Unexpected error occurred."
                        )
                    )
                )
            )
        } catch (e: IOException) {
            emit(
                ResponseHandler.Error(
                    errors = listOf(
                        ResponseError(
                            description = e.localizedMessage
                                ?: "Couldn't reach server. please check your internet connection."
                        )
                    )
                )
            )
        }
    }

}
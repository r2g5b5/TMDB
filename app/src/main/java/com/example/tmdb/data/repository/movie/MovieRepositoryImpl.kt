package com.example.tmdb.data.repository.movie

import com.example.tmdb.data.local.movie.MovieDatabase
import com.example.tmdb.data.mappers.toMovie
import com.example.tmdb.data.mappers.toMovieEntity
import com.example.tmdb.data.remote.api.movie.MovieApi
import com.example.tmdb.domain.model.movie.Movie
import com.example.tmdb.domain.repository.MovieRepository
import com.example.tmdb.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException

import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val api: MovieApi,
    private val movieDatabase: MovieDatabase,
) : MovieRepository {
    override suspend fun getMovieList(
        forceFetchFromRemote: Boolean,
        page: Int,
    ): Flow<Resource<List<Movie>>> {
        return flow {

            emit(Resource.Loading(true))

            val localMovieList = movieDatabase.movieDao.getMovieList()

            val shouldLoadLocalMovie = localMovieList.isNotEmpty() && !forceFetchFromRemote

            if (shouldLoadLocalMovie) {
                emit(Resource.Success(
                    data = localMovieList.map { movieEntity ->
                        movieEntity.toMovie()
                    }
                ))

                emit(Resource.Loading(false))
                return@flow
            }


            val movieListFromApi = try {
                api.getMovies(page)
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error(message = "Error loading movies"))
                return@flow
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error(message = "Error loading movies"))
                return@flow
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.Error(message = "Error loading movies"))
                return@flow
            }

            val movieEntities = movieListFromApi.results.let {
                it.map { movieDto ->
                    movieDto.toMovieEntity()
                }
            }

            movieDatabase.movieDao.upsertMovieList(movieEntities)

            emit(Resource.Success(movieEntities.map { it.toMovie() }))
            emit(Resource.Loading(false))

        }
    }

}
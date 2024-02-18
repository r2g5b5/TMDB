package com.example.tmdb.di
import com.example.tmdb.data.repository.movie.MovieRepositoryImpl
import com.example.tmdb.domain.repository.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun provideMoveRepository(
        movieListRepositoryImpl: MovieRepositoryImpl
    ): MovieRepository

}













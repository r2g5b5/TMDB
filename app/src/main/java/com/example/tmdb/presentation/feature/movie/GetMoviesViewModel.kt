package com.example.tmdb.presentation.feature.movie

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdb.data.remote.retrofit.api_request.PageRequest
import com.example.tmdb.data.remote.retrofit.response_handler.ResponseHandler
import com.example.tmdb.domain.model.movie.Movie
import com.example.tmdb.domain.use_case.movie.GetMoviesUseCase
import com.example.tmdb.presentation.ui.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class GetMoviesViewModel @Inject constructor(
    private val useCase: GetMoviesUseCase,
) : ViewModel() {

    private val job: Job? = null
    private val _uiState = mutableStateOf(UiState<List<Movie>>())
    val uiState: State<UiState<List<Movie>>> = _uiState

    fun getMovies(filter: PageRequest) {
        useCase(filter).onEach { result ->
            when (result) {
                is ResponseHandler.Success -> _uiState.value = UiState(success = result.data)
                is ResponseHandler.Error -> _uiState.value = UiState(errors = result.errors)
                is ResponseHandler.Loading -> _uiState.value = UiState(isLoading = true)
            }
        }.launchIn(viewModelScope)
    }

    override fun onCleared() {
        job?.cancel()
        super.onCleared()
    }

}
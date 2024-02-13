package com.example.tmdb.presentation.ui.state

import com.example.tmdb.data.remote.retrofit.api_response.ResponseError

data class UiState<T>(
    val isLoading: Boolean = false,
    val success: T? = null,
    val errors: List<ResponseError>? = null
)
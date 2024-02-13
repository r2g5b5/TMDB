package com.example.tmdb.data.remote.retrofit.response_handler

import com.example.tmdb.data.remote.retrofit.api_response.ResponseError

sealed class ResponseHandler<T>(
    val data: T? = null,
    val errors: List<ResponseError>? = null
) {
    class Success<T>(data: T) : ResponseHandler<T>(data)

    class Error<T>(
        data: T? = null,
        errors: List<ResponseError>
    ) : ResponseHandler<T>(data, errors)

    class Loading<T> : ResponseHandler<T>()
}

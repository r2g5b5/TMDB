package com.example.tmdb.data.remote.retrofit.api_response

data class ResponseError(
    val description: String,
    val errorCode: Int? = -1,
    val fieldName: String? = null
)
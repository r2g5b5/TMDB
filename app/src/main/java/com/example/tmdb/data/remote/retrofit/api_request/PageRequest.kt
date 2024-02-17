package com.example.tmdb.data.remote.retrofit.api_request

import com.example.crypto.common.Constants

data class PageRequest(
    val page: Int = 1,
    val language: String = Constants.API_LANGUAGE
)
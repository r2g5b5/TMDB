package com.example.tmdb.data.remote.retrofit.api_response

data class PageResult<T>(
    val results: List<T>,
    val page: Int,
    val pageSize: Int,
    val total_results: Int,
    val total_pages: Int,
)
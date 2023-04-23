package com.miu.movieapp.data.remote

import com.miu.movieapp.data.MovieEntity
import com.squareup.moshi.Json

data class MovieResponse(
    @field:Json(name = "results")
    val results: List<MovieEntity>,

    /* out of scope, no handle */
    val page: Int,
    val total_pages: Int,
    val total_results: Int,
)
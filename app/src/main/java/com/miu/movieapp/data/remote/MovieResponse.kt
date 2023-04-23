package com.miu.movieapp.data.remote

import com.miu.movieapp.data.Movie
import com.squareup.moshi.Json

data class MovieResponse(
    @field:Json(name = "results")
    val results: List<Movie>,

    /* out of scope, no handle */
    val page: Int,
    val total_pages: Int,
    val total_results: Int,
)
package com.miu.movieapp.data.remote

import retrofit2.http.GET

interface MovieApi {
    @GET("movie/now_playing?api_key=${Constants.API_KEY}")
    suspend fun getNowPlayingMovies(): MovieResponse

    @GET("movie/popular?api_key=${Constants.API_KEY}")
    suspend fun getPopularMovies(): MovieResponse

    @GET("movie/top_rated?api_key=${Constants.API_KEY}")
    suspend fun getTopRatedMovies(): MovieResponse
}

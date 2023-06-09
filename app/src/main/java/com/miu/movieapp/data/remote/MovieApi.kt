package com.miu.movieapp.data.remote

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET("movie/now_playing?api_key=${Constants.API_KEY}")
    suspend fun getNowPlayingMovies(): MovieResponse

    @GET("movie/popular?api_key=${Constants.API_KEY}")
    suspend fun getPopularMovies(): MovieResponse

    @GET("movie/top_rated?api_key=${Constants.API_KEY}")
    suspend fun getTopRatedMovies(): MovieResponse

    //to get trailer video for selected movie
    @GET("movie/{id}/videos?api_key=${Constants.API_KEY}")
    suspend fun getVideoTrailers(@Path("id") id: Int): VideoResponse

    @GET("search/movie?api_key=${Constants.API_KEY}")
    suspend fun searchMovies(@Query("query") query: String): MovieResponse
}

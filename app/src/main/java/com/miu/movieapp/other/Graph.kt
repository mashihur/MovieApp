package com.miu.movieapp.other

import com.miu.movieapp.data.MovieRepository
import com.miu.movieapp.data.remote.RetrofitBuilder
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

object Graph {
    val movieRepository: MovieRepository
        get() = MovieRepository(RetrofitBuilder.movieApi, ioDispatcher)

    private val mainDispatcher: CoroutineDispatcher
        get() = Dispatchers.Main

    private val ioDispatcher: CoroutineDispatcher
        get() = Dispatchers.IO
}
package com.miu.movieapp.data

import com.miu.movieapp.data.remote.MovieApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MovieRepository(
    private val movieApi: MovieApi,
    private val ioDispatcher: CoroutineDispatcher
) {
    fun getNowPlayingMovies(): Flow<List<Movie>> = flow {
        emit(movieApi.getNowPlayingMovies().results)
    }
        .flowOn(ioDispatcher)

    fun getPopularMovies(): Flow<List<Movie>> = flow {
        emit(movieApi.getPopularMovies().results)
    }
        .flowOn(ioDispatcher)

    fun getTopRatedMovies(): Flow<List<Movie>> = flow {
        emit(movieApi.getTopRatedMovies().results)
    }
        .flowOn(ioDispatcher)
}
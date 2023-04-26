package com.miu.movieapp.data

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.miu.movieapp.data.local.MovieDao
import com.miu.movieapp.data.local.MovieDatabase
import com.miu.movieapp.data.local.MovieItem
import com.miu.movieapp.data.remote.MovieApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class LocalMovieRepository(
    private val movieDao: MovieDao,
) {

    suspend fun addMovieItem(item: MovieItem) {
        movieDao.addMovieItem(item)
    }

    suspend fun deleteMovieItem(item: MovieItem) {
        movieDao.deleteMovieItem(item)
    }

    suspend fun getAllMovieItems() : List<MovieItem> {
        return movieDao.getAllMovieItems()
    }

}

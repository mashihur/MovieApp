package com.miu.movieapp.ui.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miu.movieapp.data.LocalMovieRepository
import com.miu.movieapp.data.local.MovieDatabase
import com.miu.movieapp.data.local.MovieItem
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class FavoriteViewModel(private val repository: LocalMovieRepository) : ViewModel() {
//    private var _movieItems = MutableLiveData<List<MovieItem>>()
//    val movieItems: LiveData<List<MovieItem>> = _movieItems
//    val movieItems = repository.getAllFavoriteMovies()

//    init {
//        getAllFavoriteMoves()
//    }
//
//    public fun getAllFavoriteMoves() {
//        viewModelScope.launch {
//            _movieItems = repository.getAllFavoriteMovies()
//        }
//        repository.getFavoriteMovieItems()
//            .applyValue()
//    }

//    private fun Flow<List<MovieItem>>.applyValue() {
//        this.onEach {
//            _movieItems.value = _movieItems.value.orEmpty().plus(it)
//        }
//        .catch {
//
//        }
//        .launchIn(viewModelScope)
//    }

    suspend fun getAllFavoriteMovies() : List<MovieItem> {
//        var movieItems = listOf<MovieItem>()
//        viewModelScope.launch {
//            movieItems =  repository.getAllMovieItems()
//        }
        return repository.getAllMovieItems()
    }

    fun addMovieItem(item : MovieItem) {
        viewModelScope.launch {
            repository.addMovieItem(item)
        }
    }

    fun deleteMovieItem(item : MovieItem) {
        viewModelScope.launch {
            repository.deleteMovieItem(item)
        }
    }




}
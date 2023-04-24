package com.miu.movieapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miu.movieapp.data.MovieRepository
import com.miu.movieapp.ui.uimodel.CategoryMovie
import com.miu.movieapp.ui.uimodel.MovieViewType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

class MovieViewModel(
    private val repository: MovieRepository
) : ViewModel() {

    private val _categoryMovies = MutableLiveData<List<CategoryMovie>>()
    val categoryMovies: LiveData<List<CategoryMovie>> = _categoryMovies

    init {
        getMovies()
    }

    private fun getMovies() {
        getNowPlayingMovies()
        getPopularMovies()
        getTopRatedMovies()
    }

    private fun getNowPlayingMovies() {
        repository.getNowPlayingMovies()
            .map {
                CategoryMovie(MovieViewType.NOW_PLAYING, it)
            }
            .applyValue()
    }

    private fun getPopularMovies() {
        repository.getPopularMovies()
            .map {
                CategoryMovie(MovieViewType.POPULAR, it)
            }
            .applyValue()
    }

    private fun getTopRatedMovies() {
        repository.getTopRatedMovies()
            .map {
                CategoryMovie(MovieViewType.TOP_RATED, it)
            }
            .applyValue()
    }

    private fun Flow<CategoryMovie>.applyValue() {
        this.onEach {
            _categoryMovies.value = _categoryMovies.value.orEmpty().plus(it)
        }
            .launchIn(viewModelScope)
    }
}
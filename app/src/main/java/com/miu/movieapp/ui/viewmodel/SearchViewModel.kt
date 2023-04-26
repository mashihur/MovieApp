package com.miu.movieapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miu.movieapp.data.MovieEntity
import com.miu.movieapp.data.MovieRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class SearchViewModel(
    private val repository: MovieRepository
) : ViewModel() {

    val movieVideos = MutableLiveData<List<MovieEntity>>()

    fun getMovies(query: String) {
        repository.searchMovies(query)
            .onEach {
                movieVideos.value = it
            }.catch {
                println(it)
            }.launchIn(viewModelScope)
    }
}
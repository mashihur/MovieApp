package com.miu.movieapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miu.movieapp.data.MovieEntity
import com.miu.movieapp.data.MovieRepository
import com.miu.movieapp.data.VideoEntity
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MovieDetailViewModel(
    private val repository: MovieRepository
) : ViewModel()  {

    val movieVideos = MutableLiveData<List<VideoEntity>>()
    val movieEntity = MutableLiveData<MovieEntity>()

    fun updateMovie(movie: MovieEntity) {
        movieEntity.value = movie
    }

    fun getTrailers(movieid: Int) {
        repository.getTrailerVideos(movieid)
            .onEach {
                movieVideos.value = it
            }.catch {
                println(it)
            }.launchIn(viewModelScope)
    }
}
package com.miu.movieapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miu.movieapp.data.MovieEntity
import com.miu.movieapp.data.MovieRepository
import com.miu.movieapp.data.VideoEntity
import com.miu.movieapp.ui.uimodel.CategoryMovie
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

class MovieDetailViewModel(
    private val repository: MovieRepository
) : ViewModel()  {

    private val _movieVideos = MutableLiveData<List<VideoEntity>>()
    val movieVideos: LiveData<List<VideoEntity>> = _movieVideos
    lateinit var movieItem: MovieEntity

    init {

    }

    fun updateMovie(movie: MovieEntity) {
        movieItem = movie
    }

    fun getTitle() = movieItem.title

    fun getDesc() = movieItem.overview


    fun getTrailerVideos(movieid: Int) {
        repository.getTrailerVideos(movieid)
        .onEach {
            _movieVideos.value = it
            println(it)
        }
            .catch {
                // TODO: handle error
                println(it)
            }
            .launchIn(viewModelScope)
    }
}
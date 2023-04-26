package com.miu.movieapp.ui.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miu.movieapp.data.LocalMovieRepository
import com.miu.movieapp.data.MovieEntity
import com.miu.movieapp.data.MovieRepository
import com.miu.movieapp.data.VideoEntity
import com.miu.movieapp.data.local.MovieDatabase
import com.miu.movieapp.data.local.MovieItem
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class MovieDetailViewModel(
    private val repository: MovieRepository
) : ViewModel()  {

    val movieVideos = MutableLiveData<List<VideoEntity>>()
    val movieEntity = MutableLiveData<MovieEntity>()
    val movieFromDb = MutableLiveData<Boolean>()

    fun getMovieFromDB(context: Context) {
        viewModelScope.launch {
            movieEntity.value?.let {
                val item = MovieDatabase.invoke(context).getMovieDao().getMovieItemByVideoId(movieEntity.value?.id ?: 0)
                movieFromDb.value = (item == null)
            }
        }
    }

    fun addMovieToDB(context: Context) {
        viewModelScope.launch {
            movieEntity.value?.let {
                val item = MovieItem(it.id, it.originalTitle, it.posterPath, it.overview, it.voteAverage)
                MovieDatabase.invoke(context).getMovieDao().addMovieItem(item)
            }
        }
    }

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
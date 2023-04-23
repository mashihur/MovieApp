package com.miu.movieapp.uimodel

import android.os.Parcelable
import com.miu.movieapp.data.Movie
import kotlinx.parcelize.Parcelize

@Parcelize
data class CategoryMovie(
    @MovieViewType
    val viewType: Int,
    val movies: List<Movie>
) : Parcelable
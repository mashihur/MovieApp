package com.miu.movieapp.viewholder

import com.miu.movieapp.R
import com.miu.movieapp.data.MovieEntity
import com.miu.movieapp.databinding.ItemMoviesBinding

class TopRatedMoviesViewHolder(
    binding: ItemMoviesBinding,
    onMovieClick: (MovieEntity) -> Unit
) : MovieViewHolder(binding, onMovieClick) {
    override val category: Int = R.string.top_rated
}

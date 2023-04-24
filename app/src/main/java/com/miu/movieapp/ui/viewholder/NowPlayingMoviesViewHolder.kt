package com.miu.movieapp.ui.viewholder

import com.miu.movieapp.R
import com.miu.movieapp.data.MovieEntity
import com.miu.movieapp.databinding.ItemMoviesBinding

class NowPlayingMoviesViewHolder(
    binding: ItemMoviesBinding,
    onMovieClick: (MovieEntity) -> Unit
) : MovieViewHolder(binding, onMovieClick) {
    override val category: Int = R.string.now_playing
}

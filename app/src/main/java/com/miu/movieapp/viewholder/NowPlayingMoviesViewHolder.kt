package com.miu.movieapp.viewholder

import com.miu.movieapp.R
import com.miu.movieapp.databinding.ItemMoviesBinding

class NowPlayingMoviesViewHolder(binding: ItemMoviesBinding) : MovieViewHolder(binding) {
    override val category: Int = R.string.now_playing
}

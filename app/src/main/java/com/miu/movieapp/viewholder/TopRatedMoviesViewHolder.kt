package com.miu.movieapp.viewholder

import com.miu.movieapp.R
import com.miu.movieapp.databinding.ItemMoviesBinding

class TopRatedMoviesViewHolder(binding: ItemMoviesBinding) : MovieViewHolder(binding) {
    override val category: Int = R.string.top_rated
}

package com.miu.movieapp.viewholder

import com.miu.movieapp.R
import com.miu.movieapp.databinding.ItemMoviesBinding

class PopularMoviesViewHolder(binding: ItemMoviesBinding) : MovieViewHolder(binding) {
    override val category: Int = R.string.popular

}

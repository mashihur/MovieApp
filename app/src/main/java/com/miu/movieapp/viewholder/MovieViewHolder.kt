package com.miu.movieapp.viewholder

import androidx.annotation.StringRes
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.miu.movieapp.adapter.MovieAdapter
import com.miu.movieapp.data.MovieEntity
import com.miu.movieapp.databinding.ItemMoviesBinding

abstract class MovieViewHolder(
    private val binding: ItemMoviesBinding
) : RecyclerView.ViewHolder(binding.root) {
    @get:StringRes
    protected abstract val category: Int

    fun bindView(movies: List<MovieEntity>) {
        with(binding) {
            tvTitle.text = itemView.context.getString(category)
            rvMovie.layoutManager =
                LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
            rvMovie.adapter = MovieAdapter().apply {
                submitList(movies)
            }
        }
    }
}
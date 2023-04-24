package com.miu.movieapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.miu.movieapp.data.MovieEntity
import com.miu.movieapp.databinding.ItemMoviesBinding
import com.miu.movieapp.ui.uimodel.CategoryMovie
import com.miu.movieapp.ui.uimodel.MovieViewType
import com.miu.movieapp.ui.viewholder.MovieViewHolder
import com.miu.movieapp.ui.viewholder.NowPlayingMoviesViewHolder
import com.miu.movieapp.ui.viewholder.PopularMoviesViewHolder
import com.miu.movieapp.ui.viewholder.TopRatedMoviesViewHolder

class CategoryMovieAdapter(
    private val onMovieClick: (MovieEntity) -> Unit
) :
    ListAdapter<CategoryMovie, MovieViewHolder>(CategoryMovieDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return when (viewType) {
            MovieViewType.NOW_PLAYING -> NowPlayingMoviesViewHolder(binding, onMovieClick)
            MovieViewType.POPULAR -> PopularMoviesViewHolder(binding, onMovieClick)
            MovieViewType.TOP_RATED -> TopRatedMoviesViewHolder(binding, onMovieClick)
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindView(getItem(position).movies)
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).viewType
    }

    private class CategoryMovieDiffCallBack : DiffUtil.ItemCallback<CategoryMovie>() {
        override fun areItemsTheSame(oldItem: CategoryMovie, newItem: CategoryMovie): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: CategoryMovie, newItem: CategoryMovie): Boolean {
            return oldItem == newItem
        }
    }
}
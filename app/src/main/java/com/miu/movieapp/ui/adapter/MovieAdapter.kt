package com.miu.movieapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.miu.movieapp.data.MovieEntity
import com.miu.movieapp.databinding.ItemMovieBinding

class MovieAdapter(
    private val onMovieClick: (MovieEntity) -> Unit
) : ListAdapter<MovieEntity, MovieAdapter.ViewHolder>(MovieDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding).apply {
            binding.ivMovie.setOnClickListener {
                movie?.let(onMovieClick::invoke)
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bindView(movie)
    }

    class ViewHolder(
        private val binding: ItemMovieBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        var movie: MovieEntity? = null
        fun bindView(movie: MovieEntity) {
            this.movie = movie
            with(binding) {
                tvTitle.text = movie.originalTitle
                Glide.with(itemView)
                    .load("https://image.tmdb.org/t/p/w500${movie.posterPath}")
                    .into(ivMovie)
            }
        }
    }

    private class MovieDiffCallBack : DiffUtil.ItemCallback<MovieEntity>() {
        override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
            return oldItem == newItem
        }
    }

}
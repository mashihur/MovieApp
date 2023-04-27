package com.miu.movieapp.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.miu.movieapp.R
import com.miu.movieapp.data.MovieEntity
import com.miu.movieapp.data.local.MovieItem
import com.miu.movieapp.other.Helpers
import com.miu.movieapp.ui.activity.MovieDetailActivity
import com.miu.movieapp.ui.viewholder.FavoriteViewHolder

class FavoriteAdapter(private val onMovieClick: (MovieItem) -> Unit, var context: Context, var movies : MutableList<MovieItem>) : RecyclerView.Adapter<FavoriteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.favorite_item, parent, false)
        return FavoriteViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        var movie = movies[position]
        Glide.with(context)
            .load("${Helpers.FAVORITE_IMG_BASE_PARTH}${movie.imagePath}")
            .into(holder.iv)
        holder.tv1.text = movie.title
        holder.tv2.text = movie.description
        holder.rl.setOnClickListener {
            val intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtra("movie", MovieEntity(movie.videoId, movie.title, movie.description, movie.imagePath, movie.voting))
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }
        holder.ib.setOnClickListener {
            movies.remove(movie)
            movie.let(onMovieClick::invoke)
            notifyDataSetChanged()
        }
    }


}
package com.miu.movieapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.miu.movieapp.R
import com.miu.movieapp.data.MovieEntity

class SearchAdapter(val context: Context, var videos: List<MovieEntity>, val onItemClick: (MovieEntity) -> Unit):
    RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    private fun getItem(p0: Int) = videos[p0]

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        var convert = LayoutInflater.from(context).inflate(R.layout.item_search, parent, false)
        var txt =  convert.findViewById<TextView>(R.id.tv_title)
        var desc =  convert.findViewById<TextView>(R.id.tv_description)
        var img =  convert.findViewById<ImageView>(R.id.iv_movie)
        return SearchViewHolder(convert, txt, desc, img)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val movie = getItem(position)
        holder.textView.text = movie.originalTitle
        holder.desc.text = movie.overview
        Glide.with(holder.view)
            .load("https://image.tmdb.org/t/p/w500${movie.posterPath}")
            .into(holder.img)
        holder.img.setOnClickListener{ onItemClick.invoke(getItem(position)) }
    }

    override fun getItemCount() = videos.size

    class SearchViewHolder(var view: View, var textView: TextView, var desc: TextView, var img: ImageView) : RecyclerView.ViewHolder(view)
}
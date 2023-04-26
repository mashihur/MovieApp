package com.miu.movieapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.miu.movieapp.R
import com.miu.movieapp.data.VideoEntity

class VideoAdapter(val context: Context, var videos: List<VideoEntity>, val onItemClick: (VideoEntity) -> Unit):
    RecyclerView.Adapter<VideoAdapter.VideoViewHolder>() {

    private fun getItem(p0: Int) = videos[p0]

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        var convert = LayoutInflater.from(context).inflate(R.layout.item_video, parent, false)
        var txt =  convert.findViewById<TextView>(R.id.textView)
        return VideoViewHolder(convert, txt)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        holder.textView.text = getItem(position).name
        holder.textView.setOnClickListener{ onItemClick.invoke(getItem(position)) }
    }

    override fun getItemCount() = videos.size

    class VideoViewHolder(var view: View, var textView: TextView) : RecyclerView.ViewHolder(view)
}
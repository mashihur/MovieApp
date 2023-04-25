package com.miu.movieapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.miu.movieapp.R
import com.miu.movieapp.data.VideoEntity

class VideoAdapter(val context: Context, var videos: List<VideoEntity>, val onItemClick: (VideoEntity) -> Unit): BaseAdapter() {
    override fun getCount(): Int = videos.size

    override fun getItem(p0: Int) = videos[p0]

    override fun getItemId(p0: Int) = p0.toLong()

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var convert = LayoutInflater.from(context).inflate(R.layout.item_video, p2, false)
        var textView = convert.findViewById<TextView>(R.id.textView)
        textView.text = getItem(p0).name
        textView.setOnClickListener{ onItemClick.invoke(getItem(p0)) }
        return convert
    }
}
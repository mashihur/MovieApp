package com.miu.movieapp.ui.viewholder

import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.miu.movieapp.R

class FavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var rl : RelativeLayout
    var iv : ImageView
    var tv1 : TextView
    var tv2 : TextView
    var ib : ImageButton

    init {
        rl = itemView.findViewById(R.id.rl_fav)
        iv = itemView.findViewById(R.id.imageView)
        tv1 = itemView.findViewById(R.id.tv1)
        tv2 = itemView.findViewById(R.id.tv2)
        ib = itemView.findViewById(R.id.ib_delete)
    }
}
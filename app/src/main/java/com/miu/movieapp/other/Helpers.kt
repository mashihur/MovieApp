package com.miu.movieapp.other

import android.content.Context
import android.widget.Toast

class Helpers {
    companion object {
        const val MOVIE_TABLE  = "movie_item"
        const val INTENT_VALUE1  = "value1"
        const val FAVORITE_IMG_BASE_PARTH  = "https://image.tmdb.org/t/p/w200/"
    }
}
fun Context?.toastShort(msg:String) = Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
fun Context?.toastLong(msg:String) = Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
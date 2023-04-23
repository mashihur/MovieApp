package com.miu.movieapp.uimodel

import androidx.annotation.IntDef
import com.miu.movieapp.uimodel.MovieViewType.Companion.NOW_PLAYING
import com.miu.movieapp.uimodel.MovieViewType.Companion.POPULAR
import com.miu.movieapp.uimodel.MovieViewType.Companion.TOP_RATED

/**
 * Created by phatvt2 on 8/6/20
 */

@IntDef(NOW_PLAYING, POPULAR, TOP_RATED)
@Retention(AnnotationRetention.SOURCE)
annotation class MovieViewType {
    companion object {
        const val NOW_PLAYING = 1
        const val POPULAR = 2
        const val TOP_RATED = 3
    }
}

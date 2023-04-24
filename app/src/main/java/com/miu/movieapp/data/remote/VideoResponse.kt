package com.miu.movieapp.data.remote

import android.os.Parcelable
import com.miu.movieapp.data.VideoEntity
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize


data class VideoResponse(
    @field:Json(name = "results")
    val results: List<VideoEntity>
)


package com.miu.movieapp.data.remote

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize


data class VideoResponse(
    @field:Json(name = "results")
    val results: List<VideoEntity>
)

@Parcelize
data class VideoEntity(
    @field:Json(name = "name")
    val name: String,

    @field:Json(name = "key")
    val key: String,

    @field:Json(name = "site")
    val site: String,

    @field:Json(name = "type")
    val type: String
) : Parcelable
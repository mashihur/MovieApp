package com.miu.movieapp.data

import android.os.Parcelable
import androidx.room.Entity
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize


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
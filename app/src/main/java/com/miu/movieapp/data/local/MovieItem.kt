package com.miu.movieapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class MovieItem(//  @ColumnInfo(name = "notetitle")
    val title :String,
    val note :String
): Serializable // need to pass Note entity through Fragments. So that entity should be Serializable
{
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0
}
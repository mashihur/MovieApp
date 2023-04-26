package com.miu.movieapp.data.local

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.miu.movieapp.other.Helpers
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Entity(tableName = Helpers.MOVIE_TABLE)
@Parcelize
data class MovieItem(
    @ColumnInfo(name = "video_id")
    val videoId : Int,
    val title : String,
    val imagePath : String,
    val description : String,
    val voting : Double
) : Parcelable
{
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0
}
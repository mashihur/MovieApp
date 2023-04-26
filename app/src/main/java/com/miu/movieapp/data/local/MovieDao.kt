package com.miu.movieapp.data.local

import androidx.room.*

@Dao
interface MovieDao {
    @Insert
    suspend fun addMovieItem(item: MovieItem)

    @Query("SELECT * FROM movie_item ORDER BY id DESC")
    suspend fun getAllMovieItems(): List<MovieItem>

    @Query("SELECT * FROM movie_item WHERE video_id = :id")
    suspend fun getMovieItemByVideoId(id : Int): MovieItem

    @Delete
    suspend fun deleteMovieItem(note: MovieItem)
}
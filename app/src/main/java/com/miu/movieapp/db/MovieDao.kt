package com.miu.movieapp.db

import androidx.room.*

// Create DAO for each entity, we have one entity. create one NoteDao
// Provide the functionality expects from the DB
// suspend keyword is used in all the functions to run DB queries under Kotlin Coroutine scope
@Dao
interface MovieDao {
    @Insert
    suspend fun addNote(note:MovieItem)
    @Query("SELECT * FROM MOVIEITEM ORDER BY id DESC")
    suspend fun getAllNotes():List<MovieItem>
    @Insert
    suspend fun addMultipleNotes(vararg note: MovieItem)
    @Update
    suspend fun updateNote(enote:MovieItem)
    @Delete
    suspend fun deleteNote(note: MovieItem)
}
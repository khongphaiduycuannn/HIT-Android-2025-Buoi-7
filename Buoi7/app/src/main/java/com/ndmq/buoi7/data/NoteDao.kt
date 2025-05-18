package com.ndmq.buoi7.data

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert

@Dao
interface NoteDao {

    @Query("select * from note")
    suspend fun getAllNotes(): List<Note>

    @Upsert
    fun addNote(note: Note)

    @Update
    fun updateNote(note: Note)

    @Query("update note set favorite = 1 where id = :id")
    fun favoriteNote(id: Int)

    @Query("update note set favorite = 0 where id = :id")
    fun unFavoriteNote(id: Int)
}
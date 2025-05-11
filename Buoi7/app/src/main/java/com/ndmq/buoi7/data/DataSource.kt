package com.ndmq.buoi7.data

import kotlinx.coroutines.delay

object DataSource {

    private val notes = mutableListOf(
        Note(
            title = "Note 1",
            content = "Content 1",
            favorite = false
        ),
        Note(
            title = "Note 2",
            content = "Content 2",
            favorite = false
        )
    )

    suspend fun getAllNotes(): List<Note> {
        delay(2000)
        return notes
    }

    fun addNote(note: Note) {
        notes.add(note)
    }

    fun updateNote(note: Note) {
        val index = notes.indexOfFirst { it.id == note.id }
        if (index != -1) {
            notes[index] = note
        }
    }

    fun favoriteNote(id: Int) {
        notes.find { it.id == id }?.let {
            it.favorite = true
        }
    }

    fun unFavoriteNote(id: Int) {
        notes.find { it.id == id }?.let {
            it.favorite = false
        }
    }
}
package com.example.noteapp.db

import androidx.room.*
import com.example.noteapp.model.NotesModel

@Dao
interface NoteDao {

    @Query("SELECT * FROM notes ORDER BY id DESC")
    fun getAllNotes(): List<NotesModel>?

    @Query("SELECT * FROM notes WHERE isFavourite = 1")
    fun getFavouriteNotes() : List<NotesModel>?

    @Insert
    fun insertNote(note: NotesModel?)

    @Delete
    fun deleteNote(note: NotesModel?)

    @Update
    fun updateNote(user: NotesModel?)
}

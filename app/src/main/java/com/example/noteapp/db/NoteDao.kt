package com.example.noteapp.db

import androidx.room.*
import com.example.noteapp.model.NotesModel

@Dao
interface NoteDao {

    @Query("SELECT * FROM notes ORDER BY id DESC")
    fun getAllNotes(): List<NotesModel>?


    @Insert
    fun insertUser(note: NotesModel?)

    @Delete
    fun deleteUser(note: NotesModel?)

    @Update
    fun updateUser(user: NotesModel?)
}

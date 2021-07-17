package com.example.noteapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class NotesModel(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id : Int = 0,
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "description") var description: String,
    @ColumnInfo(name = "date") var date: String?,
    @ColumnInfo(name="isFavourite") var favourite : Boolean = false
)
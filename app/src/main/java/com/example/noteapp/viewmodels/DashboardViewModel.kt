package com.example.noteapp.viewmodels

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.noteapp.db.RoomAppDb
import com.example.noteapp.model.NotesModel

class DashboardViewModel(app: Application) : AndroidViewModel(app) {
    lateinit var allNotes : MutableLiveData<List<NotesModel>>
    lateinit var mContext :Context
    init{
        allNotes = MutableLiveData()
        getAllNotes()
    }

    fun getAllNotessObservers(): MutableLiveData<List<NotesModel>> {
        return allNotes
    }

    fun getAllNotes() {
        val noteDao = RoomAppDb.getAppDatabase(getApplication())?.noteDao()
        val list = noteDao?.getAllNotes()
        allNotes.postValue(list)
    }


     fun getAllFavNotes() {
        val noteDao = RoomAppDb.getAppDatabase(getApplication())?.noteDao()
        val list = noteDao?.getFavouriteNotes()
        allNotes.postValue(list)
    }

    fun insertNote(entity: NotesModel){
        val noteDao = RoomAppDb.getAppDatabase(getApplication())?.noteDao()
        noteDao?.insertNote(entity)
        getAllNotes()
    }

    fun updateNote(entity: NotesModel){
        val noteDao = RoomAppDb.getAppDatabase(getApplication())?.noteDao()
        noteDao?.updateNote(entity)
        getAllNotes()
    }

    fun deleteNote(entity: NotesModel){
        val noteDao = RoomAppDb.getAppDatabase(getApplication())?.noteDao()
        noteDao?.deleteNote(entity)
        getAllNotes()
    }

    fun deleteNotes(notesModel: NotesModel) {
        val noteDao = RoomAppDb.getAppDatabase(getApplication())?.noteDao()
        noteDao?.deleteNote(notesModel)
        getAllFavNotes()
    }


}
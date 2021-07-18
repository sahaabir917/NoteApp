package com.example.noteapp.views

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.noteapp.R
import com.example.noteapp.databinding.ActivityNoteDescriptionBinding
import com.example.noteapp.model.NotesModel
import com.example.noteapp.viewmodels.DashboardViewModel
import com.google.gson.Gson

class NoteDescription : AppCompatActivity() {
    private lateinit var noteModel: NotesModel
    private lateinit var binding: ActivityNoteDescriptionBinding
    lateinit var viewModel: DashboardViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_note_description)
        viewRelatedWork()
        initClickListener()
    }

    private fun initClickListener() {
        binding.editBtn.setOnClickListener {
            binding.etDescription.isEnabled = true
            binding.etTitle.isEnabled = true
            binding.favBtn.visibility = View.GONE
            binding.editBtn.visibility = View.GONE
            binding.saveBtn.visibility = View.VISIBLE
        }

        binding.saveBtn.setOnClickListener {
            binding.etDescription.isEnabled = false
            binding.etTitle.isEnabled = false
            binding.favBtn.visibility = View.VISIBLE
            binding.editBtn.visibility = View.VISIBLE
            binding.saveBtn.visibility = View.GONE
            viewModel.updateNote(
                NotesModel(
                    noteModel.id,
                    binding.etTitle.text.toString(),
                    binding.etDescription.text.toString(),
                    noteModel.date,
                    noteModel.favourite
                )
            )
        }


        binding.favBtn.setOnClickListener {
            var isFaviourte = noteModel.favourite
            if (isFaviourte == true) {
                isFaviourte = false
                binding.favBtn.setImageResource(R.drawable.ic_iconly_light_heart)
            } else if (isFaviourte == false) {
                isFaviourte = true
                binding.favBtn.setImageResource(R.drawable.ic_favourite_icon)
            }

            viewModel.updateNote(
                NotesModel(
                    noteModel.id,
                    noteModel.title,
                    noteModel.description,
                    noteModel.date,
                    isFaviourte
                )
            )
        }


        binding.delBtn.setOnClickListener {
            viewModel.deleteNote(noteModel)
            onBackPressed()
        }

    }

    private fun viewRelatedWork() {
        viewModel = ViewModelProviders.of(this).get(DashboardViewModel::class.java)
        try {
            if (intent.hasExtra("singlenote")) {
                val noteInfo = intent.extras?.getString("singlenote")
                noteModel = Gson().fromJson(noteInfo, NotesModel::class.java)
                bindValues()
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun bindValues() {
        binding.etTitle.setText(noteModel.title)
        binding.etDescription.setText(noteModel.description)

        if (noteModel.favourite) {
            binding.favBtn.setImageResource(R.drawable.ic_favourite_icon)
        } else if (!noteModel.favourite) {
            binding.favBtn.setImageResource(R.drawable.ic_iconly_light_heart)
        }

    }
}
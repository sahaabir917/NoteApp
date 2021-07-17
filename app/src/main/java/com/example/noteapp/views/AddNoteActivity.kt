package com.example.noteapp.views

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.noteapp.R
import com.example.noteapp.databinding.ActivityAddNoteBinding
import com.example.noteapp.model.NotesModel
import com.example.noteapp.viewmodels.DashboardViewModel
import java.text.SimpleDateFormat
import java.util.*

class AddNoteActivity : AppCompatActivity() {
   private lateinit var binding : ActivityAddNoteBinding
    var title: String = ""
    var description: String = ""
    var date: String = ""
    lateinit var viewModel: DashboardViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_note)
        viewRelatedWork()
    }

    private fun viewRelatedWork() {
        initClickListener()
        viewModel = ViewModelProviders.of(this).get(DashboardViewModel::class.java)
    }

    private fun initClickListener() {
        binding.saveBtn.setOnClickListener {
            title = binding.etTitle.text.toString()
            description = binding.etDescription.text.toString()

            val c = Calendar.getInstance()
            val y = c.get(Calendar.YEAR)
            val m = c.get(Calendar.MONTH)
            val d = c.get(Calendar.DAY_OF_MONTH)
            val dpd = DatePickerDialog(
                this, AlertDialog.THEME_HOLO_LIGHT,
                DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                    val cal = Calendar.getInstance()
                    cal.set(year, monthOfYear, dayOfMonth)
                    val sdf = SimpleDateFormat("dd MMMM yyyy").format(Date(cal.timeInMillis))
//                    binding.etDate.text = sdf
                    val notesModel = NotesModel(0, title, description, sdf, favourite = false)
                    viewModel.insertNote(notesModel)
                    title = ""
                    description = ""
                    date = ""
                    Toast.makeText(this,"Note Saved successfully",Toast.LENGTH_SHORT).show()
//                    binding.favBtn.visibility = View.VISIBLE
                    onBackPressed()

                },
                y,
                m,
                d
            )
            dpd.show()
        }

    }
}
package com.example.noteapp.views

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.noteapp.R
import com.example.noteapp.adapters.RecyclerViewAdapter
import com.example.noteapp.databinding.ActivityDashboardBinding
import com.example.noteapp.model.NotesModel
import com.example.noteapp.viewmodels.DashboardViewModel
import java.text.SimpleDateFormat
import java.util.*

class DashboardActivity : AppCompatActivity(), RecyclerViewAdapter.RowClickListener {
    private lateinit var binding: ActivityDashboardBinding
    lateinit var viewModel: DashboardViewModel
    var title: String = ""
    var description: String = ""
    lateinit var recyclerViewAdapter: RecyclerViewAdapter
    var date: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard)
        viewRelatedWork()

    }

    private fun viewRelatedWork() {
        initRecyclerView()
        initClickListeners()
        viewModel = ViewModelProviders.of(this).get(DashboardViewModel::class.java)
        viewModel.getAllNotessObservers().observe(this, androidx.lifecycle.Observer {
            recyclerViewAdapter.setListData(ArrayList(it))
            recyclerViewAdapter.notifyDataSetChanged()
        })
    }

    private fun initClickListeners() {
//        binding.saveBtn.setOnClickListener {
//            title = binding.etTitle.text.toString()
//            description = binding.etDescription.text.toString()
//            date = binding.etDate.text.toString()
//            val notesModel = NotesModel(0, title, description, date, favourite = false)
//            viewModel.insertNote(notesModel)
//        }

//        binding.linearLayout.setOnClickListener {
//            val c = Calendar.getInstance()
//            val y = c.get(Calendar.YEAR)
//            val m = c.get(Calendar.MONTH)
//            val d = c.get(Calendar.DAY_OF_MONTH)
//            val dpd = DatePickerDialog(
//                this, AlertDialog.THEME_HOLO_LIGHT,
//                DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
//                    val cal = Calendar.getInstance()
//                    cal.set(year, monthOfYear, dayOfMonth)
//                    val sdf = SimpleDateFormat("dd MMMM yyyy").format(Date(cal.timeInMillis))
//                    binding.etDate.text = sdf
//                },
//                y,
//                m,
//                d
//            )
//            dpd.show()
//        }


    }

    private fun initRecyclerView() {
        binding.allNotes.apply {
            layoutManager = LinearLayoutManager(this@DashboardActivity)
            recyclerViewAdapter = RecyclerViewAdapter(this@DashboardActivity)
            adapter = recyclerViewAdapter
            val divider =
                DividerItemDecoration(applicationContext, StaggeredGridLayoutManager.VERTICAL)
            addItemDecoration(divider)
        }
    }

    override fun onDeleteUserClickListener(user: NotesModel) {

    }

    override fun onItemClickListener(user: NotesModel) {

    }
}
package com.example.noteapp.views

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Intent
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
import java.text.FieldPosition
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class DashboardActivity : AppCompatActivity(), RecyclerViewAdapter.RowClickListener {
    private var allNotes: ArrayList<NotesModel>? = null
    private lateinit var binding: ActivityDashboardBinding
    lateinit var viewModel: DashboardViewModel

    lateinit var recyclerViewAdapter: RecyclerViewAdapter
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
            allNotes = ArrayList(it)
            recyclerViewAdapter.setListData(ArrayList(it))
            recyclerViewAdapter.notifyDataSetChanged()
        })
    }

    private fun initClickListeners() {

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

    override fun onItemDeleteListener(position: Int) {
        viewModel.deleteNote(allNotes!![position])
    }


    override fun onItemClickListener(user: NotesModel) {

    }
}
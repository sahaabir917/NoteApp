package com.example.noteapp.views

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.noteapp.R
import com.example.noteapp.adapters.RecyclerViewAdapter
import com.example.noteapp.databinding.FragmentFavouriteNoteBinding
import com.example.noteapp.model.NotesModel
import com.example.noteapp.viewmodels.DashboardViewModel
import com.google.gson.Gson


class FavouriteNoteFragment : Fragment(), RecyclerViewAdapter.RowClickListener {
    private val filerList: ArrayList<NotesModel> = ArrayList<NotesModel>()
    private lateinit var binding: FragmentFavouriteNoteBinding
    lateinit var viewModel: DashboardViewModel
    var title: String = ""
    var description: String = ""
    private var allNotes: ArrayList<NotesModel> = ArrayList<NotesModel>()
    lateinit var recyclerViewAdapter: RecyclerViewAdapter
    var date: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_favourite_note, container, false)
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_favourite_note, container, false)
        viewRelatedWork()
        return binding.root

    }

    private fun viewRelatedWork() {
        initRecyclerView()
        initClickListeners()
        initSearch()
        viewModel = ViewModelProviders.of(this).get(DashboardViewModel::class.java)
        viewModel.getAllNotessObservers().observe(requireActivity(), androidx.lifecycle.Observer {
            allNotes.clear()
            allNotes = ArrayList(it)
            recyclerViewAdapter.setListData(ArrayList(it))
            recyclerViewAdapter.notifyDataSetChanged()
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllFavNotes()
    }

    private fun initSearch() {
        binding.appCompatEditTextSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//                viewModel.getAllFavNotes()
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                if (s.toString().length == 0) {
                    viewModel.getAllFavNotes()
                } else if (s.toString().length >= 1) {
                    filerList.clear()
                    filterValues(s.toString())
                }
            }

        })
    }

    private fun filterValues(searchKey: String?) {

        for (item in allNotes) {
            if (searchKey != null) {
                if (item.title.toLowerCase().contains(searchKey.toLowerCase())) {
                    this.filerList.add(item)
                }
            }
        }
        allNotes.clear()

        allNotes.addAll(filerList)

        recyclerViewAdapter.setListData(allNotes)
        recyclerViewAdapter.notifyDataSetChanged()
    }


    private fun initClickListeners() {
        binding.btnAdd.setOnClickListener {
            startActivity(Intent(context, AddNoteActivity::class.java))
        }

    }

    private fun initRecyclerView() {
        binding.allNotes.apply {
            layoutManager = LinearLayoutManager(context)
            recyclerViewAdapter = RecyclerViewAdapter(this@FavouriteNoteFragment)
            adapter = recyclerViewAdapter
            val divider =
                DividerItemDecoration(context, StaggeredGridLayoutManager.VERTICAL)
            addItemDecoration(divider)
        }
    }


    companion object {

        @JvmStatic
        fun newInstance() =
            FavouriteNoteFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    override fun onItemDeleteListener(position: Int) {
        viewModel.deleteNotes(allNotes!![position])
    }

    override fun onItemClickListener(note: NotesModel) {
        startActivity(
            Intent(context, NoteDescription::class.java).putExtra(
                "singlenote",
                Gson().toJson(note)
            )
        )
    }
}
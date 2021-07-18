package com.example.noteapp.views

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.noteapp.R
import com.example.noteapp.databinding.FragmentDashBoardBinding
import com.example.noteapp.model.NotesModel
import com.example.noteapp.preference.PreferenceHelper
import com.example.noteapp.viewmodels.DashboardViewModel
import java.util.*


class DashBoardFragment : Fragment() {


    private lateinit var mContext: Context
    private lateinit var binding: FragmentDashBoardBinding
    private lateinit var dashBoardListeners: DashBoardListeners
    lateinit var viewModel: DashboardViewModel
    private lateinit var preferences: PreferenceHelper
    private var allNotes: ArrayList<NotesModel>? = null
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
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_dash_board, container, false)
        viewRelatedWork()
        initClickListener()
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllNotes()
    }

    private fun viewRelatedWork() {
        viewModel = ViewModelProviders.of(this).get(DashboardViewModel::class.java)
        viewModel.getAllNotessObservers().observe(requireActivity(), androidx.lifecycle.Observer {
            allNotes = ArrayList(it)
            BindInfo()
        })

        initCrediential()

    }

    private fun initCrediential() {

        preferences = PreferenceHelper(mContext)
        var userModel = preferences.getLoginData()
        if (userModel != null) {


            binding.textView22.text = userModel.UserFullName.toString()
            Glide.with(mContext)
                .load("http://3.129.13.191:8082/" + userModel.Picture)
                .placeholder(R.drawable.ic_placeholder_user)
                .error(R.drawable.ic_placeholder_user)
                .into(binding.profilePic)

        }

    }

    private fun BindInfo() {
        binding.textView2.text = ((allNotes?.size ?: 0)).toString()
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = activity?.applicationContext!!
        dashBoardListeners = context as DashBoardListeners
    }

    private fun initClickListener() {
        binding.constraintLayout2.setOnClickListener {
//            startActivity(Intent(context,DashboardActivity::class.java))
            dashBoardListeners.onNoteLayoutClicked()
        }

        binding.linearLayout4.setOnClickListener {
            dashBoardListeners.onSheduleLayoutClicked()
        }

        binding.appCompatEditTextSearch.setOnClickListener {
            dashBoardListeners.onSheduleLayoutClicked()
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DashBoardFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    interface DashBoardListeners {
        fun onNoteLayoutClicked()
        fun onRemenderLayoutClicked()
        fun onSheduleLayoutClicked()
    }

}
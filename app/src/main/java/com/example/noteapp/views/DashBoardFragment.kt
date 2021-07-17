package com.example.noteapp.views

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.noteapp.R
import com.example.noteapp.databinding.FragmentDashBoardBinding


class DashBoardFragment : Fragment() {


    private lateinit var binding: FragmentDashBoardBinding

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
        initClickListener()

        return binding.root
    }

    private fun initClickListener() {
        binding.constraintLayout2.setOnClickListener {
            startActivity(Intent(context,DashboardActivity::class.java))
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
}
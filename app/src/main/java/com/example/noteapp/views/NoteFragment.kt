package com.example.noteapp.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.noteapp.R
import com.example.noteapp.databinding.FragmentNoteBinding


class NoteFragment : Fragment() {

    private lateinit var binding: FragmentNoteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_note, container, false)

        return binding.root

        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_note, container, false)
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            NoteFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}
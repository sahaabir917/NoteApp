package com.example.noteapp.views

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.noteapp.R
import com.example.noteapp.databinding.FragmentSettingsBinding
import com.example.noteapp.preference.PreferenceHelper

class SettingsFragment : Fragment() {


    private lateinit var binding: FragmentSettingsBinding
    private lateinit var preferences: PreferenceHelper
    private lateinit var mContext: Context
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
            DataBindingUtil.inflate(inflater, R.layout.fragment_settings, container, false)
        viewRelatedWork()
        return binding.root
    }

    private fun viewRelatedWork() {
        mContext = activity?.applicationContext!!
        initCrediential()
        initClickListener()
    }

    private fun initClickListener() {
        binding.updateBtn.setOnClickListener {
            Toast.makeText(mContext,"Coming soon!",Toast.LENGTH_SHORT).show()
        }
    }

    private fun initCrediential() {

        preferences = PreferenceHelper(mContext)
        var userModel = preferences.getLoginData()
        if (userModel != null) {
            binding.textView10.text = userModel.UserFullName.toString()
            binding.passwordEt.setText(userModel.Password)
            Glide.with(mContext)
                .load("http://3.129.13.191:8082/" + userModel.Picture)
                .placeholder(R.drawable.ic_placeholder_user)
                .error(R.drawable.ic_placeholder_user)
                .into(binding.imageView14)

        }

    }


    companion object {

        @JvmStatic
        fun newInstance() =
            SettingsFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}
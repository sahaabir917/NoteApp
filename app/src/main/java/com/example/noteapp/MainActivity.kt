package com.example.noteapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.noteapp.databinding.ActivityMainBinding
import com.example.noteapp.preference.PreferenceHelper
import com.example.noteapp.views.HomePageActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var preferences: PreferenceHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        preferences = PreferenceHelper(this)
        initOnclickListens()
    }

    private fun initOnclickListens() {

        binding.btnGetStarted.setOnClickListener {
//            startActivity(Intent(this, LoginActivity::class.java))
            initCheckLoginStatus()
        }

    }

    private fun initCheckLoginStatus() {
        if (!preferences.getIsLoginMood()) {
            startActivity(Intent(this, LoginActivity::class.java))
        } else {
            startActivity(Intent(this, HomePageActivity::class.java))
        }
    }
}
package com.example.noteapp

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Html
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
        ViewRelatedWork()
        initOnclickListens()
    }

    private fun ViewRelatedWork() {
        val text =
            "<font color=#cc0c52>E</font><font color=#1e1e1e>asy </font><font color=#cc0c52>N</font><font color=#1e1e1e>ote </font>"
        binding.textView.text =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Html.fromHtml(text, Html.FROM_HTML_MODE_COMPACT)
            } else {
                Html.fromHtml(text)
            }
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
package com.example.noteapp.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.noteapp.R
import com.example.noteapp.databinding.ActivityHomePageBinding

class HomePageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomePageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home_page)
        viewRelatedWork()
    }

    private fun viewRelatedWork() {
        var friendRequestFragment = HomePageFragment.newInstance()
        val transaction =
            supportFragmentManager.beginTransaction()
        transaction.replace(R.id.dashboard_fragment, friendRequestFragment)
        transaction.commit()
    }

}
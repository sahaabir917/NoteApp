package com.example.noteapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.noteapp.databinding.ActivityLoginBinding
import com.example.noteapp.model.LoginCredential
import com.example.noteapp.model.UserModel
import com.example.noteapp.preference.PreferenceHelper
import com.example.noteapp.viewmodels.LoginViewModel
import com.example.noteapp.views.HomePageActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var userModel: UserModel
    private lateinit var binding :ActivityLoginBinding
    private var email :String  = ""
    private var password :String = ""
    lateinit var loginViewModel: LoginViewModel
    private lateinit  var preferenceHelper : PreferenceHelper

    var loginCredential : LoginCredential?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_login)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        initClickListener()
        preferenceHelper = PreferenceHelper(this)
    }

    private fun initClickListener() {
        binding.loginBtn.setOnClickListener {
            email = binding.emailEt.text.toString()
            password = binding.passwordEt.text.toString()
            loginCredential = LoginCredential(email,password)
            loginViewModel.getUserObserver().observe(this, Observer<UserModel>{
                if(it != null) {
                    userModel = it
                    preferenceHelper.setLoginData(userModel)
                    preferenceHelper.setIsLoginMood(true)
                    //start a activity here
                    startActivity(Intent(this,HomePageActivity::class.java))
                }
                else {
                    Toast.makeText(this, "Error in fetching data", Toast.LENGTH_SHORT).show()
                }
            })
            loginViewModel.makeApiCall(loginCredential!!)
        }
    }
}
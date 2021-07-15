package com.example.noteapp.model

import com.google.gson.annotations.SerializedName

data class LoginCredential(
    @SerializedName("UserName") val UserName: String,
    @SerializedName("Password") val Password: String,
)
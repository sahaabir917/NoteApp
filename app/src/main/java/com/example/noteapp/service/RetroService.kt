package com.example.noteapp.service

import com.example.noteapp.model.LoginCredential
import com.example.noteapp.model.UserModel
import io.reactivex.Observable
import retrofit2.http.*

interface RetroService {

    @Headers( "Content-Type:application/json" )
    @POST("demo/login")
    fun getLoginData(@Body body: LoginCredential): Observable<UserModel>
}
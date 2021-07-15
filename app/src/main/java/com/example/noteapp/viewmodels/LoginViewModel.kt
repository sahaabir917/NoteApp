package com.example.noteapp.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.noteapp.model.LoginCredential
import com.example.noteapp.model.UserModel
import com.example.noteapp.service.RetroInstance
import com.example.noteapp.service.RetroService
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class LoginViewModel : ViewModel() {

    lateinit var user: MutableLiveData<UserModel>
    init {
        user = MutableLiveData()
    }

    fun getUserObserver(): MutableLiveData<UserModel> {
        return user
    }

    fun makeApiCall(loginCredential: LoginCredential) {

        val retroInstance  = RetroInstance.getRetroInstance().create(RetroService::class.java)
        retroInstance.getLoginData(loginCredential)      //give body
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(getBookListObserverRx())
    }

    private fun getBookListObserverRx(): Observer<UserModel> {
        return object : Observer<UserModel> {
            override fun onComplete() {
                //hide progress indicator .
            }

            override fun onError(e: Throwable) {
                Log.d("error",e.message.toString())
                user.postValue(null)
            }

            override fun onNext(t: UserModel) {
                user.postValue(t)
            }

            override fun onSubscribe(d: Disposable) {
                //start showing progress indicator.
            }
        }
    }


}
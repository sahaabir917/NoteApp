package com.example.noteapp.preference

import android.content.Context
import android.content.SharedPreferences
import com.example.noteapp.model.UserModel
import com.google.gson.Gson

class PreferenceHelper(context: Context) {
    private val PREF_KEY_IS_LOGIN: String = "is_login"
    private val PREF_LOGIN_DATA :String = "login_data"

    private val mPrefs: SharedPreferences =
        context.getSharedPreferences("My_preference", Context.MODE_PRIVATE)

    fun setLoginData(userModel: UserModel) {
        mPrefs.edit().putString(PREF_LOGIN_DATA, Gson().toJson(userModel))
            .apply()
    }

    fun getLoginData(): UserModel? {
        val user = mPrefs.getString(PREF_LOGIN_DATA, null)
        return Gson().fromJson(
            user, UserModel::class.java
        )
    }

    fun setIsLoginMood(isLogin: Boolean) {
        mPrefs.edit().putBoolean(PREF_KEY_IS_LOGIN, isLogin ?: false).apply()
    }

    fun getIsLoginMood(): Boolean {
        return mPrefs.getBoolean(PREF_KEY_IS_LOGIN, false)
    }

}
package com.soten.sotenshopclient.data.preference

import android.content.SharedPreferences
import androidx.core.content.edit
import com.soten.sotenshopclient.data.preference.SharedPreferenceKey.KEY_REFRESH_TOKEN
import com.soten.sotenshopclient.data.preference.SharedPreferenceKey.KEY_TOKEN
import com.soten.sotenshopclient.data.preference.SharedPreferenceKey.KEY_USER_EMAIL
import com.soten.sotenshopclient.data.preference.SharedPreferenceKey.KEY_USER_ID
import com.soten.sotenshopclient.data.preference.SharedPreferenceKey.KEY_USER_NAME
import com.soten.sotenshopclient.data.preference.SharedPreferenceKey.KEY_USER_PASSWORD
import com.soten.sotenshopclient.data.request.shopping.auth.SignInRequest
import com.soten.sotenshopclient.data.response.shopping.auth.SignInResponse
import javax.inject.Inject

class SharedPreferenceManager @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {

    fun getString(key: String): String =
        sharedPreferences.getString(key, "null")!!

    fun putString(key: String, value: String?) =
        sharedPreferences.edit { putString(key, value) }

    fun getInt(key: String): Int =
        sharedPreferences.getInt(key, Int.MIN_VALUE)

    private fun putInt(key: String, value: Int) =
        sharedPreferences.edit { putInt(key, value) }

    fun saveUserInfo(response: SignInResponse, signInRequest: SignInRequest) {
        putString(KEY_TOKEN, response.token)
        putString(KEY_REFRESH_TOKEN, response.refreshToken)
        putString(KEY_USER_NAME, response.userName)
        putInt(KEY_USER_ID, response.userId)
        putString(KEY_USER_EMAIL, signInRequest.email)
        putString(KEY_USER_PASSWORD, signInRequest.password)
    }

    fun resetUserInfo() {
        sharedPreferences.edit { clear() }
    }

}
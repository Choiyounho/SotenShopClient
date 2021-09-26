package com.soten.sotenshopclient.data.preference

import android.content.SharedPreferences
import androidx.core.content.edit
import javax.inject.Inject

class SharedPreferenceManager @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {

    fun getString(key: String): String? =
        sharedPreferences.getString(key, null)

    fun putString(key: String, value: String) =
        sharedPreferences.edit { putString(key, value) }

}
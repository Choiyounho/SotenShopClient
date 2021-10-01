package com.soten.sotenshopclient.data.request.auth

import android.util.Patterns

data class SignInRequest(
    val email: String,
    val password: String,
) {

    fun isNotValidEmail() =
        email.isNullOrBlank() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()

    fun isNotValidPassword() =
        password.isNullOrBlank() || password.length !in 8..20

}

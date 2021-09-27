package com.soten.sotenshopclient.data.request.auth

import android.util.Patterns

data class SignUpRequest(
    val email: String?,
    val password: String?,
    val name: String?,
) {

    fun isNotValidEmail() =
        email.isNullOrBlank() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()

    fun isNotValidPassword() =
        password.isNullOrBlank() || password.length !in 4..20

    fun isNotValidName() =
        name.isNullOrBlank() || name.length !in 2..10

}

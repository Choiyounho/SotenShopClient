package com.soten.sotenshopclient.data.response.auth

data class SignInResponse(
    val token: String,
    val refreshToken: String,
    val userName: String,
    val userId: Long,
)

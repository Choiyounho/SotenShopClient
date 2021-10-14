package com.soten.sotenshopclient.data.response.shopping.auth

data class SignInResponse(
    val token: String,
    val refreshToken: String,
    val userName: String,
    val userId: Int,
    val cardName: String
)

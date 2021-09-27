package com.soten.sotenshopclient.data.repository

import com.soten.sotenshopclient.data.request.auth.SignInRequest
import com.soten.sotenshopclient.data.request.auth.SignUpRequest
import com.soten.sotenshopclient.data.response.auth.SignInResponse
import com.soten.sotenshopclient.data.response.common.ApiResponse

interface ShoppingRepository {

    suspend fun signUp(signUpRequest: SignUpRequest): ApiResponse<Void>

    suspend fun signIn(signInRequest: SignInRequest): ApiResponse<SignInResponse>

}
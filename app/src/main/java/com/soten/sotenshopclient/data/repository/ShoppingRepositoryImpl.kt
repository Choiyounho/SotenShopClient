package com.soten.sotenshopclient.data.repository

import com.soten.sotenshopclient.data.api.ShoppingApi
import com.soten.sotenshopclient.data.request.auth.SignInRequest
import com.soten.sotenshopclient.data.request.auth.SignUpRequest
import com.soten.sotenshopclient.data.response.auth.SignInResponse
import com.soten.sotenshopclient.data.response.common.ApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ShoppingRepositoryImpl(
    private val shoppingApi: ShoppingApi,
) : ShoppingRepository {

    override suspend fun signUp(signUpRequest: SignUpRequest): ApiResponse<Void> =
        withContext(Dispatchers.IO) {
            shoppingApi.signUp(signUpRequest)
        }

    override suspend fun signIn(signInRequest: SignInRequest): ApiResponse<SignInResponse> =
        withContext(Dispatchers.IO) {
            shoppingApi.signIn(signInRequest)
        }

}
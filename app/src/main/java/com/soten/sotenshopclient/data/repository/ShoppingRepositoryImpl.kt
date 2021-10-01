package com.soten.sotenshopclient.data.repository

import com.soten.sotenshopclient.data.api.ShoppingApi
import com.soten.sotenshopclient.data.request.auth.SignInRequest
import com.soten.sotenshopclient.data.request.auth.SignUpRequest
import com.soten.sotenshopclient.data.request.product.ProductRegistrationRequest
import com.soten.sotenshopclient.data.response.auth.SignInResponse
import com.soten.sotenshopclient.data.response.common.ApiResponse
import com.soten.sotenshopclient.data.response.product.ProductResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MultipartBody
import retrofit2.Response

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

    override suspend fun registerProduct(
        request: ProductRegistrationRequest,
    ): ApiResponse<Void> =
        withContext(Dispatchers.IO) {
            shoppingApi.registerProduct(request)
        }

}
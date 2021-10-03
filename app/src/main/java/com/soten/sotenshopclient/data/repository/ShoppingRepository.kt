package com.soten.sotenshopclient.data.repository

import androidx.lifecycle.LiveData
import com.soten.sotenshopclient.data.request.auth.SignInRequest
import com.soten.sotenshopclient.data.request.auth.SignUpRequest
import com.soten.sotenshopclient.data.request.product.ProductRegistrationRequest
import com.soten.sotenshopclient.data.response.auth.SignInResponse
import com.soten.sotenshopclient.data.response.common.ApiResponse
import com.soten.sotenshopclient.data.response.product.ProductResponse
import okhttp3.MultipartBody
import retrofit2.Response

interface ShoppingRepository {

    suspend fun signUp(signUpRequest: SignUpRequest): ApiResponse<Void>

    suspend fun signIn(signInRequest: SignInRequest): ApiResponse<SignInResponse>

    suspend fun registerProduct(
        request: ProductRegistrationRequest,
    ): ApiResponse<Void>

    suspend fun getProductForId(
        id: Int
    ): ApiResponse<ProductResponse>

}
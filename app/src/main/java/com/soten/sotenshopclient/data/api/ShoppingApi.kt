package com.soten.sotenshopclient.data.api

import com.soten.sotenshopclient.data.request.auth.SignInRequest
import com.soten.sotenshopclient.data.request.auth.SignUpRequest
import com.soten.sotenshopclient.data.request.product.ProductRegistrationRequest
import com.soten.sotenshopclient.data.response.auth.SignInResponse
import com.soten.sotenshopclient.data.response.common.ApiResponse
import com.soten.sotenshopclient.data.response.product.ProductResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ShoppingApi {

    @GET("products/recommend")
    suspend fun getAllProduct(): Response<ApiResponse<List<ProductResponse>>>

    @POST("sign-up")
    suspend fun signUp(@Body signUpRequest: SignUpRequest): ApiResponse<Void>

    @POST("sign-in")
    suspend fun signIn(@Body signInRequest: SignInRequest): ApiResponse<SignInResponse>

    @POST("register/products/")
    suspend fun registerProduct(
        @Body request: ProductRegistrationRequest
    ): ApiResponse<Void>

}
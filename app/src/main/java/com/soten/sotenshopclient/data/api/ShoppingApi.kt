package com.soten.sotenshopclient.data.api

import com.soten.sotenshopclient.data.response.paging.json.ProductPagingJson
import com.soten.sotenshopclient.data.request.auth.SignInRequest
import com.soten.sotenshopclient.data.request.auth.SignUpRequest
import com.soten.sotenshopclient.data.request.product.ProductRegistrationRequest
import com.soten.sotenshopclient.data.response.auth.SignInResponse
import com.soten.sotenshopclient.data.response.common.ApiResponse
import com.soten.sotenshopclient.data.response.product.ProductResponse
import retrofit2.http.*

interface ShoppingApi {

    @GET("products/recommend")
    suspend fun getAllProduct(): ApiResponse<List<ProductResponse>>

    @POST("sign-up")
    suspend fun signUp(@Body signUpRequest: SignUpRequest): ApiResponse<Void>

    @POST("sign-in")
    suspend fun signIn(@Body signInRequest: SignInRequest): ApiResponse<SignInResponse>

    @POST("register/products/")
    suspend fun registerProduct(
        @Body request: ProductRegistrationRequest
    ): ApiResponse<ProductResponse>

    @GET("products/{id}")
    suspend fun getProductById(
        @Path("id") id: Int
    ): ApiResponse<ProductResponse>

    @GET("product/category/{categoryId}")
    suspend fun getAllProductByCategoryId(
        @Path("categoryId") categoryId: Int,
        @Query("pageNumber") page: Int
    ): ProductPagingJson

}
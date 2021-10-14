package com.soten.sotenshopclient.data.api

import com.soten.sotenshopclient.data.request.shopping.auth.CardNameUpdateRequest
import com.soten.sotenshopclient.data.response.shopping.paging.json.ProductPagingJson
import com.soten.sotenshopclient.data.request.shopping.auth.SignInRequest
import com.soten.sotenshopclient.data.request.shopping.auth.SignUpRequest
import com.soten.sotenshopclient.data.request.shopping.product.ProductRegistrationRequest
import com.soten.sotenshopclient.data.response.shopping.auth.SignInResponse
import com.soten.sotenshopclient.data.response.shopping.ShoppingApiResponse
import com.soten.sotenshopclient.data.response.shopping.product.ProductResponse
import retrofit2.http.*

interface ShoppingApi {

    @GET("products/recommend")
    suspend fun getAllProduct(): ShoppingApiResponse<List<ProductResponse>>

    @POST("sign-up")
    suspend fun signUp(@Body signUpRequest: SignUpRequest): ShoppingApiResponse<Void>

    @POST("sign-in")
    suspend fun signIn(@Body signInRequest: SignInRequest): ShoppingApiResponse<SignInResponse>

    @POST("register/products/")
    suspend fun registerProduct(
        @Body request: ProductRegistrationRequest,
    ): ShoppingApiResponse<ProductResponse>

    @GET("products/{id}")
    suspend fun getProductById(
        @Path("id") id: Int,
    ): ShoppingApiResponse<ProductResponse>

    @GET("product/category/{categoryId}")
    suspend fun getAllProductByCategoryId(
        @Path("categoryId") categoryId: Int,
        @Query("pageNumber") page: Int,
    ): ProductPagingJson

    @PATCH("register/card")
    suspend fun registerCard(
        @Body request: CardNameUpdateRequest,
    ): ShoppingApiResponse<Void>

    @GET("products/search")
    suspend fun search(
        @Query("keyword") keyword: String,
        @Query("pageNumber") page: Int,
    ): ProductPagingJson

}
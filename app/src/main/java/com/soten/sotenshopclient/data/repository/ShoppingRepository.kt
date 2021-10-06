package com.soten.sotenshopclient.data.repository

import com.soten.sotenshopclient.data.request.auth.SignInRequest
import com.soten.sotenshopclient.data.request.auth.SignUpRequest
import com.soten.sotenshopclient.data.request.product.ProductRegistrationRequest
import com.soten.sotenshopclient.data.response.auth.SignInResponse
import com.soten.sotenshopclient.data.response.common.ApiResponse
import com.soten.sotenshopclient.data.response.paging.json.ProductPagingJson
import com.soten.sotenshopclient.data.response.product.ProductResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ShoppingRepository {

    suspend fun signUp(signUpRequest: SignUpRequest): ApiResponse<Void>

    suspend fun signIn(signInRequest: SignInRequest): ApiResponse<SignInResponse>

    suspend fun getAllProduct(): ApiResponse<List<ProductResponse>>

    suspend fun registerProduct(request: ProductRegistrationRequest): ApiResponse<ProductResponse>

    suspend fun getProductById(id: Int): ApiResponse<ProductResponse>

    suspend fun getAllProductByCategoryId(categoryId: Int, page: Int): ProductPagingJson

}
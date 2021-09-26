package com.soten.sotenshopclient.data.api

import com.soten.sotenshopclient.domain.common.ApiResponse
import com.soten.sotenshopclient.domain.response.ProductResponse
import retrofit2.Response
import retrofit2.http.GET

interface ShoppingApi {

    @GET("products/all")
    suspend fun getAllProduct(): Response<ApiResponse<List<ProductResponse>>>



}
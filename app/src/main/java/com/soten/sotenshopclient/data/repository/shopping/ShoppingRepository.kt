package com.soten.sotenshopclient.data.repository.shopping

import com.soten.sotenshopclient.data.request.shopping.auth.CardNameUpdateRequest
import com.soten.sotenshopclient.data.request.shopping.auth.SignInRequest
import com.soten.sotenshopclient.data.request.shopping.auth.SignUpRequest
import com.soten.sotenshopclient.data.request.shopping.product.ProductRegistrationRequest
import com.soten.sotenshopclient.data.response.shopping.ShoppingApiResponse
import com.soten.sotenshopclient.data.response.shopping.auth.SignInResponse
import com.soten.sotenshopclient.data.response.shopping.paging.json.ProductPagingJson
import com.soten.sotenshopclient.data.response.shopping.product.ProductResponse

interface ShoppingRepository {

    suspend fun signUp(signUpRequest: SignUpRequest): ShoppingApiResponse<Void>

    suspend fun signIn(signInRequest: SignInRequest): ShoppingApiResponse<SignInResponse>

    suspend fun getAllProduct(): ShoppingApiResponse<List<ProductResponse>>

    suspend fun registerProduct(request: ProductRegistrationRequest): ShoppingApiResponse<ProductResponse>

    suspend fun getProductById(id: Int): ShoppingApiResponse<ProductResponse>

    suspend fun getAllProductByCategoryId(categoryId: Int, page: Int): ProductPagingJson

    suspend fun registerCard(request: CardNameUpdateRequest): ShoppingApiResponse<Void>

    suspend fun search(keyword: String, page: Int): ProductPagingJson

}
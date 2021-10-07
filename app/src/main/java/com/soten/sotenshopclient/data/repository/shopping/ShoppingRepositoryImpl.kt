package com.soten.sotenshopclient.data.repository.shopping

import com.soten.sotenshopclient.data.api.ShoppingApi
import com.soten.sotenshopclient.data.request.auth.SignInRequest
import com.soten.sotenshopclient.data.request.auth.SignUpRequest
import com.soten.sotenshopclient.data.request.product.ProductRegistrationRequest
import com.soten.sotenshopclient.data.response.auth.SignInResponse
import com.soten.sotenshopclient.data.response.common.ApiResponse
import com.soten.sotenshopclient.data.response.paging.json.ProductPagingJson
import com.soten.sotenshopclient.data.response.product.ProductResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ShoppingRepositoryImpl(private val shoppingApi: ShoppingApi) : ShoppingRepository {

    override suspend fun getAllProduct(): ApiResponse<List<ProductResponse>> {
        return shoppingApi.getAllProduct()
    }

    override suspend fun signUp(signUpRequest: SignUpRequest): ApiResponse<Void> {
        return shoppingApi.signUp(signUpRequest)
    }

    override suspend fun signIn(signInRequest: SignInRequest): ApiResponse<SignInResponse> {
        return shoppingApi.signIn(signInRequest)
    }

    override suspend fun registerProduct(request: ProductRegistrationRequest): ApiResponse<ProductResponse> {
        return shoppingApi.registerProduct(request)
    }

    override suspend fun getProductById(id: Int): ApiResponse<ProductResponse> {
        return shoppingApi.getProductById(id)
    }

    override suspend fun getAllProductByCategoryId(categoryId: Int, page: Int): ProductPagingJson {
        return shoppingApi.getAllProductByCategoryId(categoryId, page)
    }

}
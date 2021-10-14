package com.soten.sotenshopclient.data.repository.shopping

import com.soten.sotenshopclient.data.api.ShoppingApi
import com.soten.sotenshopclient.data.request.shopping.auth.CardNameUpdateRequest
import com.soten.sotenshopclient.data.request.shopping.auth.SignInRequest
import com.soten.sotenshopclient.data.request.shopping.auth.SignUpRequest
import com.soten.sotenshopclient.data.request.shopping.product.ProductRegistrationRequest
import com.soten.sotenshopclient.data.response.shopping.auth.SignInResponse
import com.soten.sotenshopclient.data.response.shopping.ShoppingApiResponse
import com.soten.sotenshopclient.data.response.shopping.paging.json.ProductPagingJson
import com.soten.sotenshopclient.data.response.shopping.product.ProductResponse

class ShoppingRepositoryImpl(private val shoppingApi: ShoppingApi) : ShoppingRepository {

    override suspend fun getAllProduct(): ShoppingApiResponse<List<ProductResponse>> {
        return shoppingApi.getAllProduct()
    }

    override suspend fun signUp(signUpRequest: SignUpRequest): ShoppingApiResponse<Void> {
        return shoppingApi.signUp(signUpRequest)
    }

    override suspend fun signIn(signInRequest: SignInRequest): ShoppingApiResponse<SignInResponse> {
        return shoppingApi.signIn(signInRequest)
    }

    override suspend fun registerProduct(request: ProductRegistrationRequest): ShoppingApiResponse<ProductResponse> {
        return shoppingApi.registerProduct(request)
    }

    override suspend fun getProductById(id: Int): ShoppingApiResponse<ProductResponse> {
        return shoppingApi.getProductById(id)
    }

    override suspend fun getAllProductByCategoryId(categoryId: Int, page: Int): ProductPagingJson {
        return shoppingApi.getAllProductByCategoryId(categoryId, page)
    }

    override suspend fun registerCard(request: CardNameUpdateRequest): ShoppingApiResponse<Void> {
        return shoppingApi.registerCard(request)
    }

    override suspend fun search(keyword: String, page: Int): ProductPagingJson {
        return shoppingApi.search(keyword, page)
    }
}
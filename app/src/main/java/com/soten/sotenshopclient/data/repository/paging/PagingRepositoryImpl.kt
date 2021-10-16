package com.soten.sotenshopclient.data.repository.paging

import androidx.lifecycle.LiveData
import androidx.paging.*
import com.soten.sotenshopclient.data.api.ShoppingApi
import com.soten.sotenshopclient.data.response.shopping.paging.CategoryDataSource
import com.soten.sotenshopclient.data.response.shopping.paging.SearchDataSource
import com.soten.sotenshopclient.data.response.shopping.product.ProductResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PagingRepositoryImpl @Inject constructor(
    private val shoppingApi: ShoppingApi,
): PagingRepository {

    override fun getCategoryPagingData(categoryId: Int): LiveData<PagingData<ProductResponse>> {
        val pagingData = CategoryDataSource(categoryId, shoppingApi)

        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = true
            ),
            pagingSourceFactory = {
                pagingData
            }
        ).liveData
    }

    override fun getSearchPagingData(keyword: String): Flow<PagingData<ProductResponse>> {
        val pagingData = SearchDataSource(keyword, shoppingApi)

        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = true
            ),
            pagingSourceFactory = {
                pagingData
            }
        ).flow
    }

}
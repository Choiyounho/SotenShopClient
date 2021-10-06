package com.soten.sotenshopclient.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.soten.sotenshopclient.data.api.ShoppingApi
import com.soten.sotenshopclient.data.response.paging.CategoryDataSource
import com.soten.sotenshopclient.data.response.product.ProductResponse
import javax.inject.Inject

class PagingRepositoryImpl @Inject constructor(
    private val shoppingApi: ShoppingApi,
): PagingRepository {

    override fun getPagingData(categoryId: Int): LiveData<PagingData<ProductResponse>> {
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

}
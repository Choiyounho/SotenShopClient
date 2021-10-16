package com.soten.sotenshopclient.data.repository.paging

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.soten.sotenshopclient.data.response.shopping.product.ProductResponse
import kotlinx.coroutines.flow.Flow

interface PagingRepository {

    fun getCategoryPagingData(categoryId: Int): LiveData<PagingData<ProductResponse>>

    fun getSearchPagingData(keyword: String): Flow<PagingData<ProductResponse>>

}
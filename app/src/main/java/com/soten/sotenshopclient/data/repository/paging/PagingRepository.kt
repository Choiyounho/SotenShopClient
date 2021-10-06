package com.soten.sotenshopclient.data.repository.paging

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.soten.sotenshopclient.data.response.product.ProductResponse

interface PagingRepository {

    fun getPagingData(categoryId: Int): LiveData<PagingData<ProductResponse>>

}
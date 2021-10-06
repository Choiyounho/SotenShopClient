package com.soten.sotenshopclient.data.response.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.soten.sotenshopclient.data.api.ShoppingApi
import com.soten.sotenshopclient.data.response.product.ProductResponse
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CategoryDataSource @Inject constructor(
    private val categoryId: Int,
    private val shoppingApi: ShoppingApi
) : PagingSource<Int, ProductResponse>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ProductResponse> {
        val page = params.key ?: 0
        return try {
            val response = shoppingApi.getAllProductByCategoryId(categoryId, page).content
            LoadResult.Page(
                response!!,
                prevKey = null,
                nextKey = if (response.isEmpty()) null else page + 1
            )
        } catch (e: IOException) {
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ProductResponse>): Int? {
        return null
    }
}
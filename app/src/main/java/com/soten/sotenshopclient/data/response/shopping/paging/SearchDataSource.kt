package com.soten.sotenshopclient.data.response.shopping.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.soten.sotenshopclient.data.api.ShoppingApi
import com.soten.sotenshopclient.data.response.shopping.product.ProductResponse
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class SearchDataSource @Inject constructor(
    private val keyword: String,
    private val shoppingApi: ShoppingApi
) : PagingSource<Int, ProductResponse>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ProductResponse> {
        val page = params.key ?: 0
        return try {
            val response = shoppingApi.search(keyword, page).content

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
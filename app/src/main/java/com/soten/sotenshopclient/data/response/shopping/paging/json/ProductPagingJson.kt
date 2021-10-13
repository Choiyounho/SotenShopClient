package com.soten.sotenshopclient.data.response.shopping.paging.json


import com.google.gson.annotations.SerializedName
import com.soten.sotenshopclient.data.response.shopping.product.ProductResponse

data class ProductPagingJson(

    @SerializedName("content")
    val content: List<ProductResponse>?,

    @SerializedName("pageable")
    val pageable: Pageable?,

)
package com.soten.sotenshopclient.data.response.paging.json


import com.google.gson.annotations.SerializedName
import com.soten.sotenshopclient.data.response.product.ProductResponse

data class ProductPagingJson(

    @SerializedName("content")
    val content: List<ProductResponse>?,

    @SerializedName("pageable")
    val pageable: Pageable?,

)
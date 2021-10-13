package com.soten.sotenshopclient.data.response.shopping.paging.json


import com.google.gson.annotations.SerializedName

data class Pageable(

    @SerializedName("pageNumber")
    val pageNumber: Int?,

)
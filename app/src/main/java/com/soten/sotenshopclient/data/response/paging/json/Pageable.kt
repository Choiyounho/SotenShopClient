package com.soten.sotenshopclient.data.response.paging.json


import com.google.gson.annotations.SerializedName

data class Pageable(

    @SerializedName("pageNumber")
    val pageNumber: Int?,

)
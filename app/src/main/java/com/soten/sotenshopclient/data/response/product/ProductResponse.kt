package com.soten.sotenshopclient.data.response.product


import com.google.gson.annotations.SerializedName

data class ProductResponse(

    @SerializedName("categoryId")
    val categoryId: Int,

    @SerializedName("createdAt")
    val createdAt: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("id")
    val id: Int?,

    @SerializedName("images")
    val images: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("price")
    val price: Int,

    @SerializedName("status")
    val status: String,

    @SerializedName("updatedAt")
    val updatedAt: String,

    @SerializedName("userId")
    val userId: Int

)
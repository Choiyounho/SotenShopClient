package com.soten.sotenshopclient.data.response.product


import com.google.gson.annotations.SerializedName
import com.soten.sotenshopclient.domain.model.ProductModel

data class ProductResponse(

    @SerializedName("categoryId")
    val categoryId: Int,

    @SerializedName("createdAt")
    val createdAt: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("updatedAt")
    val updatedAt: String,

    @SerializedName("id")
    val id: Int,

    @SerializedName("images")
    val images: List<String>,

    @SerializedName("name")
    val name: String,

    @SerializedName("price")
    val price: Int,

    @SerializedName("status")
    val status: String,

    @SerializedName("userId")
    val userId: Int

) {

    fun toModel() = ProductModel(
        id = id,
        thumbnailImage = images.first(),
        name = name,
        price = price
    )

}
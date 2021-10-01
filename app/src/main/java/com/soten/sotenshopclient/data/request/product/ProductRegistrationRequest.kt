package com.soten.sotenshopclient.data.request.product

data class ProductRegistrationRequest(
    val name: String,
    val description: String,
    val price: Int,
    val categoryId: Int,
    var imagePath: String? = null,
    val userId: Int
)
package com.soten.sotenshopclient.data.response.shopping

data class ShoppingApiResponse<T>(
    val success: Boolean,
    val data: T,
    val message: String? = null
)

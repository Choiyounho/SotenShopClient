package com.soten.sotenshopclient.data.response.common

data class ApiResponse<T>(
    val success: Boolean,
    val data: T,
    val message: String? = null
)

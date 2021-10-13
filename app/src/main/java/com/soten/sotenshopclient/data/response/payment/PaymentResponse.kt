package com.soten.sotenshopclient.data.response.payment

data class PaymentResponse<T>(
    val code: Int,
    val message: String,
    val response: T
)

package com.soten.sotenshopclient.data.repository.shopping.payment

import com.soten.sotenshopclient.data.response.payment.PaymentResponse
import com.soten.sotenshopclient.data.response.payment.auth.PaymentTokenResponse

interface PaymentRepository {

    suspend fun getToken(impKey: String, impSecret: String): PaymentResponse<PaymentTokenResponse>

}
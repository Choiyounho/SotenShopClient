package com.soten.sotenshopclient.data.repository.shopping.payment

import com.soten.sotenshopclient.data.response.payment.PaymentResponse
import com.soten.sotenshopclient.data.response.payment.auth.PaymentTokenResponse
import com.soten.sotenshopclient.data.response.payment.billingkey.BillingKeyResponse

interface PaymentRepository {

    suspend fun getToken(impKey: String, impSecret: String): PaymentResponse<PaymentTokenResponse>

    suspend fun registerCard(
        customerUid: String,
        token: String?,
        billingKey: HashMap<String, String>
    ): PaymentResponse<BillingKeyResponse>

}
package com.soten.sotenshopclient.data.repository.shopping.payment

import com.soten.sotenshopclient.data.api.IamPortApi
import com.soten.sotenshopclient.data.response.payment.PaymentResponse
import com.soten.sotenshopclient.data.response.payment.auth.PaymentTokenResponse
import com.soten.sotenshopclient.data.response.payment.billingkey.BillingKeyResponse
import com.soten.sotenshopclient.data.response.payment.receipt.Receipt
import javax.inject.Inject

class PaymentRepositoryImpl @Inject constructor(
    private val iamPortApi: IamPortApi,
) : PaymentRepository {

    override suspend fun getToken(
        impKey: String,
        impSecret: String,
    ): PaymentResponse<PaymentTokenResponse> {
        return iamPortApi.getToken(impKey, impSecret)
    }

    override suspend fun registerCard(
        customerUid: String,
        token: String?,
        billingKey: HashMap<String, String>,
    ): PaymentResponse<BillingKeyResponse> {
        return iamPortApi.registerCard(customerUid, token, billingKey)
    }

    override suspend fun payment(
        token: String?,
        receiptMap: HashMap<String, Any>,
    ): PaymentResponse<Receipt> {
        return iamPortApi.payment(token, receiptMap)
    }

}
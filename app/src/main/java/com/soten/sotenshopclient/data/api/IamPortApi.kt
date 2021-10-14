package com.soten.sotenshopclient.data.api

import com.soten.sotenshopclient.data.response.payment.PaymentResponse
import com.soten.sotenshopclient.data.response.payment.auth.PaymentTokenResponse
import com.soten.sotenshopclient.data.response.payment.billingkey.BillingKeyResponse
import com.soten.sotenshopclient.data.response.payment.receipt.Receipt
import retrofit2.http.*

interface IamPortApi {

    @FormUrlEncoded
    @POST("users/getToken")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    suspend fun getToken(
        @Field("imp_key") impKey: String,
        @Field("imp_secret") impSecret: String,
    ): PaymentResponse<PaymentTokenResponse>

    @FormUrlEncoded
    @POST("subscribe/customers/{customer_uid}")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    suspend fun registerCard(
        @Path("customer_uid") customerUid: String,
        @Header("Authorization") token: String?,
        @FieldMap billingKey: HashMap<String, String>,
    ): PaymentResponse<BillingKeyResponse>

    @FormUrlEncoded
    @POST("subscribe/payments/again")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    suspend fun payment(
        @Header("Authorization") token: String?,
        @FieldMap receiptMap: HashMap<String, Any>,
    ): PaymentResponse<Receipt>

}
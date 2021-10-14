package com.soten.sotenshopclient.data.request.payment

import com.google.gson.annotations.SerializedName

data class PaymentRequest(
    @SerializedName("customer_uid")
    val customerUid: String,
    @SerializedName("merchant_uid")
    val merchantUid: String,
    @SerializedName("amount")
    val amount: Int,
    @SerializedName("name")
    val name: String,
) {

    fun toHashMap(): HashMap<String, Any> {
        return hashMapOf(
            "customer_uid" to customerUid,
            "merchant_uid" to merchantUid,
            "amount" to amount,
            "name" to name
        )
    }

}
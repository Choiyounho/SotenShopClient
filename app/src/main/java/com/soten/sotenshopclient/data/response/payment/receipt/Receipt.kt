package com.soten.sotenshopclient.data.response.payment.receipt


import com.google.gson.annotations.SerializedName

data class Receipt(
    @SerializedName("response")
    val receiptResponse: ReceiptResponse?
)
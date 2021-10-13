package com.soten.sotenshopclient.data.response.payment.receipt


import com.google.gson.annotations.SerializedName

data class CancelHistory(
    @SerializedName("amount")
    val amount: Int?,
    @SerializedName("cancelled_at")
    val cancelledAt: Int?,
    @SerializedName("pg_tid")
    val pgTid: String?,
    @SerializedName("reason")
    val reason: String?,
    @SerializedName("receipt_url")
    val receiptUrl: String?
)
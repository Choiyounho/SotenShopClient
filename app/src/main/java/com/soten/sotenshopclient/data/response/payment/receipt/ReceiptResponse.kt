package com.soten.sotenshopclient.data.response.payment.receipt


import com.google.gson.annotations.SerializedName

data class ReceiptResponse(

    @SerializedName("amount")
    val amount: Int?,

    @SerializedName("apply_num")
    val applyNum: String?,

    @SerializedName("bank_code")
    val bankCode: String?,

    @SerializedName("bank_name")
    val bankName: String?,

    @SerializedName("buyer_addr")
    val buyerAddr: String?,

    @SerializedName("buyer_email")
    val buyerEmail: String?,

    @SerializedName("buyer_name")
    val buyerName: String?,

    @SerializedName("buyer_postcode")
    val buyerPostcode: String?,

    @SerializedName("buyer_tel")
    val buyerTel: String?,

    @SerializedName("cancel_amount")
    val cancelAmount: Int?,

    @SerializedName("cancel_history")
    val cancelHistory: List<CancelHistory>?,

    @SerializedName("cancel_reason")
    val cancelReason: String?,

    @SerializedName("cancel_receipt_urls")
    val cancelReceiptUrls: List<String>?,

    @SerializedName("cancelled_at")
    val cancelledAt: Int?,

    @SerializedName("card_code")
    val cardCode: String?,

    @SerializedName("card_name")
    val cardName: String?,

    @SerializedName("card_number")
    val cardNumber: String?,

    @SerializedName("card_quota")
    val cardQuota: Int?,

    @SerializedName("card_type")
    val cardType: String?,

    @SerializedName("cash_receipt_issued")
    val cashReceiptIssued: Boolean?,

    @SerializedName("channel")
    val channel: String?,

    @SerializedName("currency")
    val currency: String?,

    @SerializedName("custom_data")
    val customData: String?,

    @SerializedName("customer_uid")
    val customerUid: String?,

    @SerializedName("customer_uid_usage")
    val customerUidUsage: String?,

    @SerializedName("emb_pg_provider")
    val embPgProvider: String?,

    @SerializedName("escrow")
    val escrow: Boolean?,

    @SerializedName("fail_reason")
    val failReason: String?,

    @SerializedName("failed_at")
    val failedAt: Int?,

    @SerializedName("imp_uid")
    val impUid: String?,

    @SerializedName("merchant_uid")
    val merchantUid: String?,

    @SerializedName("name")
    val name: String?,

    @SerializedName("paid_at")
    val paidAt: Int?,

    @SerializedName("pay_method")
    val payMethod: String?,

    @SerializedName("pg_id")
    val pgId: String?,

    @SerializedName("pg_provider")
    val pgProvider: String?,

    @SerializedName("pg_tid")
    val pgTid: String?,

    @SerializedName("receipt_url")
    val receiptUrl: String?,

    @SerializedName("started_at")
    val startedAt: Int?,

    @SerializedName("status")
    val status: String?,

)
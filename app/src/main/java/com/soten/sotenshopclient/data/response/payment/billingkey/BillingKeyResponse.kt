package com.soten.sotenshopclient.data.response.payment.billingkey


import com.google.gson.annotations.SerializedName

data class BillingKeyResponse(

    @SerializedName("card_code")
    val cardCode: String?,

    @SerializedName("card_name")
    val cardName: String?,

    @SerializedName("card_number")
    val cardNumber: String?,

    @SerializedName("card_type")
    val cardType: String?,

    @SerializedName("customer_addr")
    val customerAddr: String?,

    @SerializedName("customer_email")
    val customerEmail: String?,

    @SerializedName("customer_name")
    val customerName: String?,

    @SerializedName("customer_postcode")
    val customerPostcode: String?,

    @SerializedName("customer_tel")
    val customerTel: String?,

    @SerializedName("customer_uid")
    val customerUid: String?,

    @SerializedName("inserted")
    val inserted: Int?,

    @SerializedName("pg_id")
    val pgId: String?,

    @SerializedName("pg_provider")
    val pgProvider: String?,

    @SerializedName("updated")
    val updated: Int?

)
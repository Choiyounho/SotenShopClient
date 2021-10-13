package com.soten.sotenshopclient.data.request.payment

import com.google.gson.annotations.SerializedName

data class PaymentBillingKeyRequest(

    @SerializedName("card_number")
    val cardNumber : String,

    @SerializedName("expiry")
    val expiry : String,

    @SerializedName("birth")
    val birth : String,

    @SerializedName("pwd_2digit")
    val pwd2digit : String

)

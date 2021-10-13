package com.soten.sotenshopclient.data.request.payment

import com.google.gson.annotations.SerializedName

data class PaymentTokenRequest(

    @SerializedName("imp_key")
    val importKey : String,

    @SerializedName("imp_secret")
    val importSecret : String

)
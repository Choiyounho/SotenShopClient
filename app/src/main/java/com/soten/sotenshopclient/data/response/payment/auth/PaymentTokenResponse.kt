package com.soten.sotenshopclient.data.response.payment.auth

import com.google.gson.annotations.SerializedName

data class PaymentTokenResponse(

    @SerializedName("access_token")
    val accessToken :String,

    @SerializedName("now")
    val now : Int,

    @SerializedName("expired_at")
    val expiredAt : Int

)

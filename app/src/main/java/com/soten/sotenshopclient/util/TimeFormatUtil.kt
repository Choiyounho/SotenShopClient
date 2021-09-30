package com.soten.sotenshopclient.util

import java.text.SimpleDateFormat
import java.util.*

object TimeFormatUtil {

    fun createdTimeForRegisterProduct(): String {
        val currentDateTime = Calendar.getInstance().time
        return SimpleDateFormat("yy.MM.dd.HH:mm", Locale.KOREA).format(currentDateTime)
    }

}
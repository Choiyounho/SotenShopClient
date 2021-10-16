package com.soten.sotenshopclient.util

import java.text.SimpleDateFormat
import java.util.*

object TimeFormatUtil {

    fun createdTimeForRegister(): String {
        val currentDateTime = Calendar.getInstance().time
        return SimpleDateFormat("yy.MM.dd.HH:mm:ss", Locale.KOREA).format(currentDateTime)
    }

}
package com.android.secretly.core.util.date

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DateFormatter {

    fun format(date: Long): String {
        val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH)
        return dateFormat.format(Date(date))
    }
}
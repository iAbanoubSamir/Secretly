package com.android.secretly.core.util.date

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DateFormatterTest {

    @Test
    fun `formatDate, returns formatted date string`() {

        val dateFormatter = formatDate(1719592620115)

        assertThat(dateFormatter).isEqualTo("28-06-2024")
    }

    private fun formatDate(date: Long): String {
        val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH)
        return dateFormat.format(Date(date))
    }
}
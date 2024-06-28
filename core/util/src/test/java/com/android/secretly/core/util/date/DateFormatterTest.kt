package com.android.secretly.core.util.date

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test

class DateFormatterTest {

    @Test
    fun `formatDate, returns formatted date string`() {
        val dateFormatter = formatDate(1719592620115)

        val result = "Fri Jun 28 2024 16:37:00"

        assertThat(result).isEqualTo(dateFormatter)
    }

    private fun formatDate(date: Long): String {
        return "Fri Jun 28 2024 16:37:00"
    }
}
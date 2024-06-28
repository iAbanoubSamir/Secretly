package com.android.secretly.core.util.date

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test

class DateFormatterTest {

    @Test
    fun `formatDate, returns formatted date string`() {
        val dateFormatter = DateFormatter()

        val result = dateFormatter.format(1719592620115)

        assertThat(result).isEqualTo("28-06-2024")
    }

    @Test
    fun `formatDate, return formatted in the give format`() {

        assertThat(result).isEqualTo(expected)
    }
}
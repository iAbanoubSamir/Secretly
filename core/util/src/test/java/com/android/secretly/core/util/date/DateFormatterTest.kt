package com.android.secretly.core.util.date

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class DateFormatterTest {

    @Test
    fun `formatDate, returns formatted date string`() {
        val dateFormatter = formatDate(1719592620115)

        val result = null

        assertThat(result).isEqualTo(expected)
    }

}
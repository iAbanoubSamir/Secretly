package com.android.secretly.core.util.date

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class DateFormatterTest {

    @Test
    fun `formatDate, returns formatted date string`() {
        val dateFormatter = DateFormatter()

        val result = dateFormatter.format(1719592620115)

        assertThat(result).isEqualTo("28-06-2024")
    }

    @ParameterizedTest
    @CsvSource(
        "'1719592620115', 'dd-MM-yyyy', '28-06-2024'",
        "'1719592620115', 'dd/MM/yyyy', '28/06/2024'",
        "'1719592620115', 'dd-MM', '28-06'"
    )
    fun `formatDate, return formatted date string in the give pattern`(
        date: Long,
        pattern: String,
        expected: String
    ) {
        val dateFormatter = DateFormatter()

        val result = dateFormatter.format(date, pattern)

        assertThat(result).isEqualTo(expected)
    }
}
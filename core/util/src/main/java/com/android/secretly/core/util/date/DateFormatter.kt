package com.android.secretly.core.util.date

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * A utility class for formatting dates into a specific string representation.
 */
class DateFormatter {

    /**
     * Format a date (represented as a Long timestamp) into a string in the format "dd-MM-yyyy".
     *
     * @param date The date to format, represented as a Long timestamp (milliseconds since the epoch).
     * @return The formatted date string in the format "dd-MM-yyyy".
     */
    fun format(date: Long): String {
        val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH)
        return dateFormat.format(Date(date))
    }

    /**
     * Formats a date (represented as a Long timestamp) into a string using the provided pattern.
     *
     * @param date The date to format, represented as a Long timestamp (milliseconds since the epoch).
     * @param pattern The desired date format pattern, as defined by [SimpleDateFormat].
     * @return The formatted date string according to the specified pattern.
     */
    fun format(date: Long, pattern: String): String {
        val dateFormat = SimpleDateFormat(pattern, Locale.ENGLISH)
        return dateFormat.format(Date(date))
    }

}
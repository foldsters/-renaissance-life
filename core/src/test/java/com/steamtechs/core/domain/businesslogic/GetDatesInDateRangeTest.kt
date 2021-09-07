package com.steamtechs.core.domain.businesslogic

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class GetDatesInDateRangeTest {

    @Test
    @DisplayName("Given start date equals end date, return a list of the start date.")
    fun `Given start date equals end date, return a list of the start date`() {
        val startDate = "2021-09-07"
        val datesInRange = GetDatesInDateRange(startDate, startDate)

        assertEquals(listOf(startDate), datesInRange)
    }

    @Test
    @DisplayName("Given start date after end date, return an empty list.")
    fun `Given start date after end date, return an empty list`() {
        val startDate = "2021-09-07"
        val endDate = "2021-09-05"
        val datesInRange = GetDatesInDateRange(startDate, endDate)

        assertEquals(listOf<String>(), datesInRange)
    }

    @Test
    @DisplayName("Given start date before end date, return all dates between start and end, inclusive.")
    fun `Given start date before end date, return all dates between start and end, inclusive`() {
        val startDate = "2021-09-28"
        val endDate = "2021-10-02"
        val datesInRange = GetDatesInDateRange(startDate, endDate)

        assertEquals(
            listOf<String>(
                "2021-09-28",
                "2021-09-29",
                "2021-09-30",
                "2021-10-01",
                "2021-10-02"),
            datesInRange
        )
    }

}
package com.steamtechs.core.domain.businesslogic

import java.text.SimpleDateFormat
import java.util.*

object GetDatesInDateRange {

    operator fun invoke(startDate: String, endDate: String) : List<String> {

        if (startDate == endDate) {
            return listOf(startDate)
        }

        if (startDate > endDate) {
            println("Start Date is AFTER End Date")
            return listOf()
        }

        val cal : Calendar = Calendar.getInstance()

        // More than one day
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        val parsedStartDate = sdf.parse(startDate)!!

        val dateStrings = mutableListOf<String>()
        cal.time = parsedStartDate
        var tempDateString = startDate

        dateStrings.add(tempDateString)

        // Get all of the days in the range
        while (tempDateString != endDate) {
            cal.add(Calendar.DATE, 1)
            tempDateString = SimpleDateFormat("yyyy-MM-dd", Locale.US).format(cal.time)
            dateStrings.add(tempDateString)
        }

        return dateStrings
    }
}
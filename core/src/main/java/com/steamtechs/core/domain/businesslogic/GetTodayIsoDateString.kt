package com.steamtechs.core.domain.businesslogic

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object GetTodayIsoDateString {
    operator fun invoke() : String {
        val currentDateTime = LocalDateTime.now()
        return currentDateTime.format(DateTimeFormatter.ISO_DATE)
    }
}
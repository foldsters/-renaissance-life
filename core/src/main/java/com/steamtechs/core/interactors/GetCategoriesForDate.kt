package com.steamtechs.core.interactors

import com.steamtechs.core.data.DayCategoryLog
import com.steamtechs.core.domain.Category

object GetCategoriesForDate {
    operator fun invoke(dayCategoryLog: DayCategoryLog, date: String): Iterable<Category> {
        return dayCategoryLog.filter { it.date == date }
    }
}
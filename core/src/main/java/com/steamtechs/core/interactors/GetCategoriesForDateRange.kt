package com.steamtechs.core.interactors

import com.steamtechs.core.data.DayCategoryLog
import com.steamtechs.core.domain.Category

object GetCategoriesForDateRange {
    operator fun invoke(dayCategoryLog: DayCategoryLog,
                        startDate: String,
                        endDate: String
                        ) : Iterable<Category> {
        return dayCategoryLog.takeWhile { startDate <= it.date && it.date <= endDate }
    }
}
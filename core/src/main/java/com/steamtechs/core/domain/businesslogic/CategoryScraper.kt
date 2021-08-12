package com.steamtechs.core.domain.businesslogic

import com.steamtechs.core.data.DayCategoryLog
import com.steamtechs.core.domain.Category

object CategoryScraper {
    fun getCategoryFromDate(dayCatLog: DayCategoryLog): Iterable<Category> {
        val localList = dayCatLog.getCategories()
        return localList
    }

}
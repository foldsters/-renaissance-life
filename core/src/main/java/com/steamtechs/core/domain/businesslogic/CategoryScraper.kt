package com.steamtechs.core.domain.businesslogic

import com.steamtechs.core.data.DayCatLog
import com.steamtechs.core.domain.Category

object CategoryScraper {
    fun getCategoryFromDate(dayCatLog: DayCatLog): Iterable<Category> {
        val localList = dayCatLog.getCategories()
        return localList
    }

}
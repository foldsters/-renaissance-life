package com.steamtechs.core.interactors

import com.steamtechs.core.data.DayCategoryLog
import com.steamtechs.core.domain.Category

object ClearAllCategoriesInStorage {
    operator fun invoke(dayCategoryLog: DayCategoryLog) {
        dayCategoryLog.clearAllCategories()
    }
}
package com.steamtechs.core.interactors

import com.steamtechs.core.data.DayCategoryLog
import com.steamtechs.core.domain.Category

object DeleteCategory {
    operator fun invoke(dayCategoryLog: DayCategoryLog, category: Category) {
        dayCategoryLog.deleteCategory(category)
    }
}
package com.steamtechs.core.interactors

import com.steamtechs.core.data.DayCatLog
import com.steamtechs.core.domain.Category

object DeleteCategory {
    operator fun invoke(dayCatLog: DayCatLog, category: Category) {
        dayCatLog.deleteCategory(category)
    }
}
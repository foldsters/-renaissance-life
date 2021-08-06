package com.steamtechs.core.interactors

import com.steamtechs.core.data.DayCategoryLog
import com.steamtechs.core.domain.Category

object AddCategory {
    operator fun invoke(dayCategoryLog: DayCategoryLog, category: Category){
        dayCategoryLog.addCategory(category)
    }
}
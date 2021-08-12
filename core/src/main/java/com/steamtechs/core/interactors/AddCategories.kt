package com.steamtechs.core.interactors

import com.steamtechs.core.data.DayCategoryLog
import com.steamtechs.core.domain.Category

object AddCategories {
    operator fun invoke(dayCategoryLog: DayCategoryLog, categories: List<Category>){
        dayCategoryLog.addCategories(categories)
    }
}
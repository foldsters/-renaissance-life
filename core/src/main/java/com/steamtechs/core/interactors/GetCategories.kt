package com.steamtechs.core.interactors

import com.steamtechs.core.data.DayCategoryLog
import com.steamtechs.core.domain.Category

object GetCategories {
    operator fun invoke(dayCategoryLog: DayCategoryLog) : Iterable<Category>{
        return dayCategoryLog.getCategories()
    }
}
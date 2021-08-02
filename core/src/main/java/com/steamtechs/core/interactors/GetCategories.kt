package com.steamtechs.core.interactors

import com.steamtechs.core.data.DayCatLog
import com.steamtechs.core.domain.Category

object GetCategories {
    operator fun invoke(dayCatLog: DayCatLog) : Iterable<Category>{
        return dayCatLog.getCategories()
    }
}
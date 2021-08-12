package com.steamtechs.core.domain

import com.steamtechs.core.data.DayCategoryLog

object InitDayCategoryLog {
    operator fun invoke(inputDayCategoryLog: DayCategoryLog) : DayCategoryLog {
        return DayCategoryLog(PDayCategoryLog().also { it.addCategories(inputDayCategoryLog.getCategories()) })
    }
}

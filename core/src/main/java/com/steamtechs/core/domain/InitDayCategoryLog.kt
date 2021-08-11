package com.steamtechs.core.domain

import com.steamtechs.core.data.DayCategoryLog
import com.steamtechs.core.data.platform.PDayCategoryLog

object InitDayCategoryLog {
    operator fun invoke(inputDayCategoryLog: DayCategoryLog) : DayCategoryLog {
        return DayCategoryLog(PDayCategoryLog().also { it.addCategories(inputDayCategoryLog.getCategories()) })
    }
}

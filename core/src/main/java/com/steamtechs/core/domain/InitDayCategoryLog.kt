package com.steamtechs.core.domain

import com.steamtechs.core.data.DayCategoryLog

object InitDayCategoryLog {
    operator fun invoke(
        sourceDayCategoryLog: DayCategoryLog,
        targetDayCategoryLog: DayCategoryLog
    ) : DayCategoryLog {
        targetDayCategoryLog.addCategories(sourceDayCategoryLog.getCategories())
        return targetDayCategoryLog
    }
}

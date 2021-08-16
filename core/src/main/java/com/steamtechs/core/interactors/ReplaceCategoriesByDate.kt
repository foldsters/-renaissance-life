package com.steamtechs.core.interactors

import com.steamtechs.core.data.DayCategoryLog
import com.steamtechs.core.domain.Category

object ReplaceCategoriesByDate {

    operator fun invoke(sourceDate : String, sourceDayCategoryLog: DayCategoryLog, targetDayCategoryLog: DayCategoryLog){
        val tempCategoryList  = targetDayCategoryLog.filter { it.date != sourceDate }
        ensureSourceNotEmpty(sourceDayCategoryLog)
        ensureAllSameDate(sourceDate,sourceDayCategoryLog)
        targetDayCategoryLog.addCategories(sourceDayCategoryLog + tempCategoryList)
    }

    fun ensureSourceNotEmpty(sourceDayCategoryLog: DayCategoryLog){
        if (sourceDayCategoryLog.getCategories().none()){
            throw IllegalArgumentException("sourceDayCategoryLog is Empty.")
        }
    }


    fun ensureAllSameDate(sourceDate: String, sourceDayCategoryLog: DayCategoryLog) {
        for (category in sourceDayCategoryLog) {
            if (category.date != sourceDate) {
                throw IllegalArgumentException("Not all Dates Match in sourceDayCategoryLog.")
            }
        }
    }

}


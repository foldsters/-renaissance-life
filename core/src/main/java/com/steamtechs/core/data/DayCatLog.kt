package com.steamtechs.core.data

import com.steamtechs.core.domain.Category

class DayCatLog(val dayCatLogDataSource: DayCatLogDataSource) {

    fun getCategories(): Iterable<Category> {
        return dayCatLogDataSource.getCategories()
    }

    fun addCategory(cat: Category) {
        dayCatLogDataSource.addCategory(cat)
    }

    fun deleteCategory(cat: Category) {
        dayCatLogDataSource.deleteCategory(cat)
    }



}
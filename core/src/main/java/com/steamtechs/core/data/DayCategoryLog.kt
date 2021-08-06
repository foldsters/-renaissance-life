package com.steamtechs.core.data

import com.steamtechs.core.domain.Category

class DayCategoryLog(val dayCategoryLogDataSource: DayCategoryLogDataSource) {

    fun getCategories(): Iterable<Category> {
        return dayCategoryLogDataSource.getCategories()
    }

    fun addCategory(cat: Category) {
        dayCategoryLogDataSource.addCategory(cat)
    }

    fun deleteCategory(cat: Category) {
        dayCategoryLogDataSource.deleteCategory(cat)
    }



}
package com.steamtechs.core.data

import com.steamtechs.core.domain.Category

class DayCategoryLog(val dayCategoryLogDataSource: DayCategoryLogDataSource) : Iterable<Category> {

    fun getCategories(): Iterable<Category> {
        return dayCategoryLogDataSource.getCategories()
    }

    fun clearAllCategories() {
        dayCategoryLogDataSource.clearAllCategories()
    }

    fun addCategories(categories: Iterable<Category>) {
        dayCategoryLogDataSource.addCategories(categories)
    }

    override fun iterator(): Iterator<Category> {
        return dayCategoryLogDataSource.iterator()
    }

}
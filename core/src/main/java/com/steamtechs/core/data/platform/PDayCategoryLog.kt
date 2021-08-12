package com.steamtechs.core.data.platform

import com.steamtechs.core.domain.Category
import com.steamtechs.core.data.DayCategoryLogDataSource

class PDayCategoryLog : DayCategoryLogDataSource
{
    private val categoryList = mutableListOf<Category>()

    override fun getCategories(): List<Category> {
        return categoryList
    }

    override fun clearAllCategories() {
        categoryList.clear()
    }

    override fun addCategories(categories: Iterable<Category>) {
        this.clearAllCategories()
        categoryList.addAll(categories)
    }


    override fun iterator(): Iterator<Category> {
        return categoryList.iterator()
    }


}
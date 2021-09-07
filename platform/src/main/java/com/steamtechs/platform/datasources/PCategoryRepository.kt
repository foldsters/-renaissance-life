package com.steamtechs.platform.datasources

import com.steamtechs.core.domain.Category
import com.steamtechs.core.data.CategoryDataSource


class PCategoryRepository : CategoryDataSource
{
    private val categoryList = mutableListOf<Category>()

    override fun getCategories(): List<Category> {
        return categoryList
    }

    override fun clearAllCategories() {
        categoryList.clear()
    }

    override fun addCategories(categories: Iterable<Category>) {
        categoryList.addAll(categories)
    }

}
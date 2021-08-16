package com.steamtechs.core.data

import com.steamtechs.core.domain.Category

class CategoryRepository(val categoryDataSource: CategoryDataSource) {

    fun getCategories(): Iterable<Category> {
        return categoryDataSource.getCategories()
    }

    fun clearAllCategories() {
        categoryDataSource.clearAllCategories()
    }

    fun addCategories(categories: Iterable<Category>) {
        categoryDataSource.clearAllCategories()
        categoryDataSource.addCategories(categories)
    }

}
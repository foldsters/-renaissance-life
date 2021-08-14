package com.steamtechs.renaissancelife.framework.datasources

import com.steamtechs.core.domain.Category
import com.steamtechs.renaissancelife.framework.db.CategoryDao
import com.steamtechs.renaissancelife.framework.db.CategoryEntity

class FCategoryDataSource: CategoryDao {
    private val categoryList: List<CategoryEntity> = listOf()

    override fun getAllCategories(): List<CategoryEntity> {
        return categoryList
    }

    override fun clearAllCategories() {
        categoryList.drop(categoryList.size)
    }

    override fun addCategories(categories: List<CategoryEntity>) {
        categoryList.plus(categories)
    }
}
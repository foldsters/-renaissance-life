package com.steamtechs.renaissancelife.framework.datasources

import com.steamtechs.core.domain.Category
import com.steamtechs.renaissancelife.framework.db.CategoryDao
import com.steamtechs.renaissancelife.framework.db.CategoryEntity

class FRoomCategoryDataSource: CategoryDao {
    private val categoryList: List<CategoryEntity> = listOf()

    override fun getAllCategoryEntities(): List<CategoryEntity> {
        return categoryList
    }

    override fun clearAllCategoryEntities() {
        categoryList.drop(categoryList.size)
    }

    override fun addCategoryEntities(categories: Iterable<CategoryEntity>) {
        categoryList.plus(categories)
    }
}
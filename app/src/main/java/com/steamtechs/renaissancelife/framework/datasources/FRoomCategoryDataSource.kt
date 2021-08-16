package com.steamtechs.renaissancelife.framework.datasources

import com.steamtechs.renaissancelife.framework.db.CategoryDao
import com.steamtechs.renaissancelife.framework.db.CategoryEntity

class FRoomCategoryDataSource: CategoryDao {
    private val categoryList: MutableList<CategoryEntity> = mutableListOf()

    override fun getAllCategoryEntities(): List<CategoryEntity> {
        return categoryList
    }

    override fun clearAllCategoryEntities() {
        categoryList.drop(categoryList.size)
    }

    override fun addCategoryEntities(categoryEntities: Iterable<CategoryEntity>) {
        categoryList.addAll(categoryEntities)
    }
}
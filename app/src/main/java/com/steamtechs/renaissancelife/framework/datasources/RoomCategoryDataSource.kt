package com.steamtechs.renaissancelife.framework.datasources

import com.steamtechs.core.data.CategoryDataSource
import com.steamtechs.core.domain.Category
import com.steamtechs.renaissancelife.framework.db.CategoryDao
import com.steamtechs.renaissancelife.framework.db.CategoryEntity

class RoomCategoryDataSource constructor(val categoryDao : CategoryDao) : CategoryDataSource {

    override fun getCategories(): Iterable<Category> {
        return categoryDao.getAllCategoryEntities().map {it.toCategory()}
    }

    override fun clearAllCategories() {
        categoryDao.clearAllCategoryEntities()
    }

    override fun addCategories(categories: Iterable<Category>) {
        categoryDao.addCategoryEntities( categories.map { CategoryEntity.fromCategory(it) } )
    }



}

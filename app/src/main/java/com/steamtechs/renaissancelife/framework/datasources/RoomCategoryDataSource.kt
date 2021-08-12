package com.steamtechs.renaissancelife.framework.datasources

import android.content.Context
import com.steamtechs.core.data.DayCategoryLogDataSource
import com.steamtechs.core.domain.Category
import com.steamtechs.renaissancelife.framework.db.AppRoomDatabase
import com.steamtechs.renaissancelife.framework.db.CategoryDao
import com.steamtechs.renaissancelife.framework.db.CategoryEntity
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

class RoomCategoryDataSource constructor(val categoryDao : CategoryDao) : DayCategoryLogDataSource {

    override fun getCategories(): Iterable<Category> {
        return categoryDao.getAllCategories().map {it.toCategory()}
    }

    override fun clearAllCategories() {
        categoryDao.clearAllCategories()
    }

    override fun addCategories(categories: Iterable<Category>) {
        categoryDao.clearAllCategories()
        categoryDao.addCategories( categories.map { CategoryEntity.fromCategory(it) } )
    }

    override fun iterator(): Iterator<Category> {
        return this.getCategories().iterator()
    }

}

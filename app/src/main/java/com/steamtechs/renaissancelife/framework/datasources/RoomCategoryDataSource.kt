package com.steamtechs.renaissancelife.framework.datasources

import android.content.Context
import com.steamtechs.core.data.DayCategoryLogDataSource
import com.steamtechs.core.domain.Category
import com.steamtechs.renaissancelife.framework.db.AppRoomDatabase
import com.steamtechs.renaissancelife.framework.db.CategoryEntity

class RoomCategoryDataSource(context: Context) : DayCategoryLogDataSource {

    private val categoryDao = AppRoomDatabase.getInstance(context).categoryDao()
    override fun getCategories(): Iterable<Category> {
        return categoryDao.getAllCategories().map {it.toCategory()}
    }

    override fun addCategory(category: Category) {
        categoryDao.addCategory(CategoryEntity.fromCategory(category))
    }

    override fun deleteCategory(category: Category) {

    }

    override fun changeCategoryName(category: Category, name: String) {
        TODO("To eventually remove")
    }

    override fun setCategoryTickValue(category: Category, count: Int) {
        TODO("To eventually remove")
    }

    override fun iterator(): Iterator<Category> {
        TODO("Not yet implemented")
    }

}

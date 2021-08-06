package com.steamtechs.renaissancelife.platform.datasources

import com.steamtechs.core.domain.Category
import com.steamtechs.core.data.DayCategoryLogDataSource
import kotlin.IllegalArgumentException

class PDayCategoryLog : DayCategoryLogDataSource
{
    private var categoryList = mutableListOf<Category>()

    override fun getCategories(): List<Category> {
        return categoryList
    }

    override fun addCategory(category: Category) {
        categoryList.add(category)
    }

    override fun deleteCategory(category: Category) {
        val removed = categoryList.remove(category)
        if (!removed) throw IllegalArgumentException("!")
    }

    override fun changeCategoryName(category: Category, name: String) {
        TODO("Not yet implemented")
    }

    override fun setCategoryTickValue(category: Category, count: Int) {
        TODO("Not yet implemented")
    }

    override fun iterator(): Iterator<Category> {
        return categoryList.iterator()
    }


}
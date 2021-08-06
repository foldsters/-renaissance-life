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

    override fun addCategory(cat: Category) {
        categoryList.add(cat)
    }

    override fun deleteCategory(cat: Category) {
        val removed = categoryList.remove(cat)
        if (!removed) throw IllegalArgumentException("!")
    }

    override fun changeCategoryName(cat: Category, name: String) {
        TODO("Not yet implemented")
    }

    override fun setCategoryTickValue(cat: Category, count: Int) {
        TODO("Not yet implemented")
    }

    override fun iterator(): Iterator<Category> {
        return categoryList.iterator()
    }


}
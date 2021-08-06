package com.steamtechs.core.data

import com.steamtechs.core.domain.Category

interface DayCategoryLogDataSource : Iterable<Category> {

    fun getCategories() : Iterable<Category>
    fun addCategory(category: Category)
    fun deleteCategory(category: Category)
    fun changeCategoryName(category: Category, name: String)
    fun setCategoryTickValue(category: Category, count: Int)

}
package com.steamtechs.core.data

import com.steamtechs.core.domain.Category

interface DayCatLogDataSource : Iterable<Category> {

    fun getCategories() : Iterable<Category>
    fun addCategory(cat: Category)
    fun deleteCategory(cat: Category)
    fun changeCategoryName(cat: Category, name: String)
    fun setCategoryTickValue(cat: Category, count: Int)

}
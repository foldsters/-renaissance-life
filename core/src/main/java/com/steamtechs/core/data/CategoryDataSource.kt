package com.steamtechs.core.data

import com.steamtechs.core.domain.Category

interface CategoryDataSource {

    fun getCategories() : Iterable<Category>

    fun clearAllCategories()

    fun addCategories(categories: Iterable<Category>)

}
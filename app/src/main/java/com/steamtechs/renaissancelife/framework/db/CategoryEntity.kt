package com.steamtechs.renaissancelife.framework.db

import com.steamtechs.core.domain.Category

data class CategoryEntity(
    val id: Int = 0,
    val date : String,
    val title : String,
    val tickvalue : Int) {

    constructor(date : String, category: Category) : this(id=0, date, category.title, category.tickValue)
}
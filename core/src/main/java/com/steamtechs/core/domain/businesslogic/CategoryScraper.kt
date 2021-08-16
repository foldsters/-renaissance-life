package com.steamtechs.core.domain.businesslogic

import com.steamtechs.core.data.CategoryRepository
import com.steamtechs.core.domain.Category

object CategoryScraper {
    fun getCategoryFromDate(catRepository: CategoryRepository): Iterable<Category> {
        val localList = catRepository.getCategories()
        return localList
    }

}
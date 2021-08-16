package com.steamtechs.core.interactors

import com.steamtechs.core.data.CategoryRepository
import com.steamtechs.core.domain.Category

object GetCategoriesForDateRange {
    operator fun invoke(categoryRepository: CategoryRepository,
                        startDate: String,
                        endDate: String
                        ) : Iterable<Category> {
        return categoryRepository.getCategories().takeWhile { startDate <= it.date && it.date <= endDate }
    }
}
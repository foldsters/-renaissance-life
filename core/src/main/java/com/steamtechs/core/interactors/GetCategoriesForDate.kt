package com.steamtechs.core.interactors

import com.steamtechs.core.data.CategoryRepository
import com.steamtechs.core.domain.Category

object GetCategoriesForDate {
    operator fun invoke(categoryRepository: CategoryRepository, date: String): Iterable<Category> {
        return categoryRepository.getCategories().filter { it.date == date }
    }
}
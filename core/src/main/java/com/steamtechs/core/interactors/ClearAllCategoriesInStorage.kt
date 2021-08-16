package com.steamtechs.core.interactors

import com.steamtechs.core.data.CategoryRepository

object ClearAllCategoriesInStorage {
    operator fun invoke(categoryRepository: CategoryRepository) {
        categoryRepository.clearAllCategories()
    }
}
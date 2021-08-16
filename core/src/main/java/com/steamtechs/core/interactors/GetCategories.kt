package com.steamtechs.core.interactors

import com.steamtechs.core.data.CategoryRepository
import com.steamtechs.core.domain.Category

object GetCategories {
    operator fun invoke(categoryRepository: CategoryRepository) : Iterable<Category>{
        return categoryRepository.getCategories()
    }
}
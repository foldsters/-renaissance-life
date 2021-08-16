package com.steamtechs.core.interactors

import com.steamtechs.core.data.CategoryRepository
import com.steamtechs.core.domain.Category

object AddCategories {
    operator fun invoke(categoryRepository: CategoryRepository, categories: List<Category>){
        categoryRepository.addCategories(categories)
    }
}
package com.steamtechs.core.interactors

import com.steamtechs.core.data.CategoryRepository

object SaveToExternalCategoryRepository {
    operator fun invoke(
        sourceCategoryRepository: CategoryRepository,
        targetCategoryRepository: CategoryRepository
    ){
        targetCategoryRepository.addCategories(sourceCategoryRepository.getCategories())
    }
}
package com.steamtechs.core.domain

import com.steamtechs.core.data.CategoryRepository

object InitDayCategoryLog {
    operator fun invoke(
        sourceCategoryRepository: CategoryRepository,
        targetCategoryRepository: CategoryRepository
    ) : CategoryRepository {
        targetCategoryRepository.addCategories(sourceCategoryRepository.getCategories())
        return targetCategoryRepository
    }
}

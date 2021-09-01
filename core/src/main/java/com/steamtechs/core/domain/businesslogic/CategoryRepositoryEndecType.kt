package com.steamtechs.core.domain.businesslogic

import com.steamtechs.core.data.CategoryRepository
import com.steamtechs.core.domain.Category

interface CategoryRepositoryEndecType {

    fun encodeCategoryRepository(categoryRepository: CategoryRepository) : String

    fun decodeString(encodedString: String, targetRepo : CategoryRepository)
}
package com.steamtechs.core.domain.businesslogic

import com.steamtechs.core.data.CategoryRepository
import com.steamtechs.core.domain.Category
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class StringCategoryRepositorySerializable  {

    fun encodeCategoryRepository(categoryRepository: CategoryRepository): String {
        return Json.encodeToString(categoryRepository.getCategories().toList())
    }

    fun decodeString(encodedString: String, targetRepository: CategoryRepository) {
        targetRepository.addCategories(Json.decodeFromString<List<Category>>(encodedString))
    }

}
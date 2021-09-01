package com.steamtechs.core.domain.businesslogic

import com.steamtechs.core.data.CategoryRepository
import com.steamtechs.core.domain.Category
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class StringCategoryRepositorySerializable : CategoryRepositoryEndecType {

    override fun encodeCategoryRepository(categoryRepository: CategoryRepository): String {
        return Json.encodeToString(categoryRepository.getCategories().toList())
    }

    override fun decodeString(encodedString: String, targetRepo : CategoryRepository) {
        val decodedList = Json.decodeFromString<List<Category>>(encodedString)
        targetRepo.addCategories(decodedList)
    }

}
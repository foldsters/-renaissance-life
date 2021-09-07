package com.steamtechs.core.domain.businesslogic

import com.steamtechs.core.data.CategoryRepository
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object StringCategoryRepositorySerializable : CategoryRepositoryEndecType {

    override fun encodeCategoryRepository(categoryRepository: CategoryRepository): String {
        return Json.encodeToString(categoryRepository)
    }

    override fun decodeString(encodedString: String): CategoryRepository {
        val decodedRepo = Json.decodeFromString<CategoryRepository>(encodedString)
        return decodedRepo
    }

}
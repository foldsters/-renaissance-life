package com.steamtechs.core.domain.businesslogic

import com.steamtechs.core.data.CategoryRepository
import com.steamtechs.core.domain.Category
import com.steamtechs.platform.datasources.PCategoryRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class StringCategoryRepositorySerializableTest{

    val rawEndec = StringCategoryRepositorySerializable()
    val categoryRepository = CategoryRepository(
        PCategoryRepository().also {
            it.addCategories(
                listOf(
                    Category("oldCat1","2020-08-29", 3),
                    Category("oldCat2","2021-08-29", 4),
                    Category("oldCat3","2021-08-29", 6),
                    Category("oldCat4","2020-03-09", 1),
                )
            ) })

    
    @Test
    @DisplayName("Create Instance of StringCategoryRepositorySerializable<T>.")
    fun `Create Instance of RawStringEndec`() {
        assertInstanceOf(StringCategoryRepositorySerializable::class.java, rawEndec)
    }


    @Test
    @DisplayName("EncodeCateogryRepository returns a String of a Serialized Category Repository.")
    fun `EncodeCateogryRepository returns a String of a Serialized Category Repository`() {
        val encodedCategoryRepository = rawEndec.encodeCategoryRepository(categoryRepository)
        assertInstanceOf(String::class.java, encodedCategoryRepository)
    }

    @Test
    @DisplayName("DecodeString copies a decoded, Serialized Category Repository to a targetRepository.")
    fun `DecodeString copies a decoded, Serialized Category Repository to a targetRepository`() {
        val encodedCategoryRepository = rawEndec.encodeCategoryRepository(categoryRepository)
        val targetRepository = CategoryRepository(PCategoryRepository())
        rawEndec.decodeString(encodedCategoryRepository, targetRepository)
        assertEquals(categoryRepository.getCategories(), targetRepository.getCategories())
    }


}
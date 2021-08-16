package com.steamtechs.core.interactors

import com.steamtechs.core.data.CategoryRepository
import com.steamtechs.renaissancelife.platform.datasources.PCategoryRepository
import com.steamtechs.core.domain.Category
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class AddCategoriesTest {

    // SETUP

    lateinit var categoryRepository: CategoryRepository
    lateinit var categoryList : List<Category>

    @BeforeEach
    fun setup() {
        val pDayCategoryLog = PCategoryRepository()
        categoryRepository = CategoryRepository(pDayCategoryLog)
        categoryList = listOf<Category>(Category("Test1"), Category("Test2"), Category("Test3"))
    }


    // TESTS

    @Test
    @DisplayName("Show AddCategory.")
    fun `Show AddCategory`() {
        AddCategories(categoryRepository, categoryList)
        assertEquals(categoryList, categoryRepository.getCategories())
    }
}
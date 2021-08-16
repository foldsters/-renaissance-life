package com.steamtechs.core.interactors

import com.steamtechs.core.data.CategoryRepository
import com.steamtechs.renaissancelife.platform.datasources.PCategoryRepository
import com.steamtechs.core.domain.Category
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class ClearAllCategoriesInStorageTest{

    // SETUP

    private lateinit var catRepository : CategoryRepository

    @BeforeEach
    fun setup() {
        val pDayCatLog = PCategoryRepository()
        val categoryList = listOf(Category("Test1"), Category("Test2"), Category("Test3"))
        catRepository = CategoryRepository(pDayCatLog).also { AddCategories(it, categoryList) }
    }

    // TESTS

    @Test
    @DisplayName("Clear All Categories on Populated Instance")
    fun clearAllCategoriesOnPopulatedInstance() {
        ClearAllCategoriesInStorage(catRepository)
    }

}
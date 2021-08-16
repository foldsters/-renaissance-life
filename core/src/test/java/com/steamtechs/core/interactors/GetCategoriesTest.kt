package com.steamtechs.core.interactors

import com.steamtechs.core.data.CategoryRepository
import com.steamtechs.renaissancelife.platform.datasources.PCategoryRepository
import com.steamtechs.core.domain.Category
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class GetCategoriesTest {

    // SETUP

    lateinit var testPCatRepository : PCategoryRepository
    lateinit var testCatRepository : CategoryRepository
    lateinit var categoryList : List<Category>

    @BeforeEach
    fun setup() {
        testPCatRepository = PCategoryRepository()
        testCatRepository = CategoryRepository(testPCatRepository)
        categoryList = listOf(Category("Test1"), Category("Test2"), Category("Test3"))
    }


    // TESTS

    @Test
    @DisplayName("GetCategories returns Iterable of Category.")
    fun `GetCategories returns Iterable of Category`() {
        testPCatRepository.addCategories(categoryList)
        assertInstanceOf(Iterable::class.java, GetCategories(testCatRepository))
    }
}
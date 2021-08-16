package com.steamtechs.core.interactors

import com.steamtechs.core.data.CategoryRepository
import com.steamtechs.core.domain.Category
import com.steamtechs.platform.datasources.PCategoryRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class InitLocalCategoryRepositoryTest {

    // SETUP

    lateinit var sourceCategoryRepository: CategoryRepository
    lateinit var targetCategoryRepository: CategoryRepository

    @BeforeEach
    @DisplayName("Given a CategoryRepository, ")
    fun `Given a CategoryRepository`(){
        sourceCategoryRepository = CategoryRepository(PCategoryRepository())
        targetCategoryRepository = CategoryRepository(PCategoryRepository())
    }


    // TESTS

    @Test
    @DisplayName("Return Local CategoryRepository.")
    fun `Return copied CategoryRepository`() {
        assertInstanceOf(CategoryRepository::class.java, InitLocalCateogryRepository(sourceCategoryRepository, targetCategoryRepository))
    }

    @Test
    @DisplayName("Show returned Instance is a Copy of given Instance.")
    fun `Show returned Instance is a Copy of given Instance`() {
        val categoryList = listOf<Category>(Category("Cat1"), Category("Cat2"))
        sourceCategoryRepository.addCategories(categoryList)
        InitLocalCateogryRepository(sourceCategoryRepository, targetCategoryRepository)
        assertEquals(categoryList, targetCategoryRepository.getCategories())
    }
}
package com.steamtechs.core.data


import com.steamtechs.platform.datasources.PCategoryRepository
import com.steamtechs.core.domain.Category
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class CategoryRepositoryTest {

    // SETUP

    lateinit var categoryDataSource: CategoryDataSource

    @BeforeEach
    fun setup() {
        categoryDataSource = PCategoryRepository()
    }


    // TESTS

    // Instance / Constructor Tests
    @Test
    @DisplayName("Create instance of CategoryRepository.")
    fun `Create instance of CategoryRepository`() {
        val categoryRepository = CategoryRepository(categoryDataSource)
        assertInstanceOf(CategoryRepository::class.java, categoryRepository)
    }


    // Tests Assuming Instance of CategoryRepository
    @Nested
    @DisplayName("Given instance of CategoryRepository,")
    inner class GivenCategoryRepository {

        // SETUP

        lateinit var categoryRepository: CategoryRepository
        var categoryList = listOf(Category("Test1"), Category("Test2"), Category("Test3"))

        @BeforeEach
        fun `Given instance of PCategoryRepository`() {
            categoryRepository = CategoryRepository(categoryDataSource)
        }


        // TESTS

        // Getter Tests
        @Test
        @DisplayName("Get Categories from Instance.")
        fun `Get Categories from Instance`() {
            val emptyCategoryList = categoryRepository.getCategories()
            assertEquals(listOf<Category>(), emptyCategoryList)

        }


        // Clear Categories Tests
        @Test
        @DisplayName("Clear All Categories.")
        fun clearAllCategories() {
            categoryRepository.clearAllCategories()
            assertTrue(categoryRepository.getCategories().toList().isEmpty())
        }


        // Add Categories Tests
        @Test
        @DisplayName("Clear DB then Add Categories from Iterable.")
        fun clearDbThenAddCategoriesFromIterable() {
            categoryRepository.addCategories(categoryList)
            assertIterableEquals(categoryList, categoryRepository.getCategories())
        }


        // Assuming Instance of categoryRepository has Categories in it
        @Nested
        @DisplayName("Given Populated Instance,")
        inner class GivenPopulatedInstance {

            // SETUP

            @BeforeEach
            fun `Given Populated Instance`() {
                categoryRepository.addCategories(categoryList)
            }


            // TESTS

            // Getter Tests
            @Test
            @DisplayName("Show Getter on Populated Instance.")
            fun showGetterOnPopulatedInstance() {
                assertIterableEquals(categoryList, categoryRepository.getCategories())
            }

            // Clear Categories Tests
            @Test
            @DisplayName("Clear All Categories on Populated Instance.")
            fun clearAllCategoriesOnPopulatedInstance() {
                categoryRepository.clearAllCategories()
                assertTrue(categoryRepository.getCategories().toList().isEmpty())
            }

            // Add Categories Tests
            @Test
            @DisplayName("Clear DB then Add Categories to Populated Instance.")
            fun clearDbThenAddCategoriesToPopulatedInstance() {
                val newCategories: Iterable<Category> =
                    listOf(Category("Test3"), Category("Test4"), Category("Test5"))
                categoryRepository.addCategories(newCategories)
                assertIterableEquals(newCategories, categoryRepository.getCategories())
            }
        }
    }
}
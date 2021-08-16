package com.steamtechs.core.data.platform

import com.steamtechs.core.domain.Category
import com.steamtechs.core.data.CategoryDataSource
import com.steamtechs.platform.datasources.PCategoryRepository
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*

internal class PCategoryRepositoryTest {

    // TESTS

    // Constructor and Instantiation Tests
    @Test
    @DisplayName("Create instance of PCategoryRepository.")
    fun `Create instance of PCategoryRepository`() {
        val pCategoryRepository = PCategoryRepository()

        assertInstanceOf(PCategoryRepository::class.java, pCategoryRepository)
    }

    @Test
    @DisplayName("Check PCategoryRepository inherits from CategoryRepository.")
    fun `Check PCategoryRepository inherits from CategoryRepository`() {
        val pCategoryRepository = PCategoryRepository()

        assertInstanceOf(CategoryDataSource::class.java, pCategoryRepository)
    }


    // Tests Assuming Instantiation
    @Nested
    @DisplayName("Given instance of PCategoryRepository,")
    inner class GivenPCategoryRepository {

        // SETUP

        lateinit var pCategoryRepository : PCategoryRepository
        lateinit var categoryList : List<Category>

        @BeforeEach
        fun `Given instance of PCategoryRepository`() {
            pCategoryRepository = PCategoryRepository()
            categoryList = listOf(Category("Test1"), Category("Test2"))
        }


        // TESTS

        // Getter Tests
        @Test
        @DisplayName("Get Categories from Instance.")
        fun `Get Categories from Instance`() {
            val categories = pCategoryRepository.getCategories()

            assertEquals(listOf<Category>(), categories)

        }

        @Test
        @DisplayName("Get Categories from Interface Instance.")
        fun `Get Categories from Interface Instance`() {
            val cats = (pCategoryRepository as CategoryDataSource).getCategories()

            assertEquals(listOf<Category>(), cats)
        }


        // Clear Categories Tests
        @Test
        @DisplayName("Clear All Categories.")
        fun clearAllCategories() {
            pCategoryRepository.clearAllCategories()

            assertTrue(pCategoryRepository.getCategories().isEmpty())
        }


        // Add Categories Tests
        @Test
        @DisplayName("Add Categories from Iterable.")
        fun addCategoriesFromIterable() {
            pCategoryRepository.addCategories(categoryList)

            assertIterableEquals(categoryList, pCategoryRepository.getCategories())
        }


        // Tests Assuming a Category is in pCategoryRepository
        @Nested
        @DisplayName("Given Category in instance,")
        inner class GivenCategoryInInstance {

            // SETUP

            @BeforeEach
            fun `Given Category in instance`(){
                pCategoryRepository.addCategories(categoryList)
            }

            // TESTS

            // Getter Tests
            @Test
            @DisplayName("Show Getter on Populated Instance.")
            fun showGetterOnPopulatedInstance() {
                assertIterableEquals(categoryList,pCategoryRepository.getCategories())
            }


            // Clear Categories Tests
            @Test
            @DisplayName("Clear All Categories on Populated Instance.")
            fun clearAllCategoriesOnPopulatedInstance() {
                pCategoryRepository.clearAllCategories()
                assertTrue(pCategoryRepository.getCategories().isEmpty())
            }


            // Add Categories Tests
            @Test
            @DisplayName("Add Categories to Populated Instance.")
            fun addCategoriesToPopulatedInstance() {
                val newCategories : Iterable<Category> =
                    listOf(Category("Test3"), Category("Test4"), Category("Test5"))
                pCategoryRepository.addCategories(newCategories)
                assert(pCategoryRepository.getCategories().containsAll(newCategories as Collection))
            }
        }
    }
}
package com.steamtechs.core.data.platform

import com.steamtechs.core.domain.Category
import com.steamtechs.core.data.DayCategoryLogDataSource
import com.steamtechs.renaissancelife.platform.datasources.PDayCategoryLog
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*

internal class PDayCategoryLogTest {

    // TESTS

    // Constructor and Instantiation Tests
    @Test
    @DisplayName("Create instance of PDayCatLog.")
    fun `Create instance of PDayCatLog`() {
        val pDayCatLog = PDayCategoryLog()

        assertInstanceOf(PDayCategoryLog::class.java, pDayCatLog)
    }

    @Test
    @DisplayName("Check PDayCatLog inherits from DayCatLog.")
    fun `Check PDayCatLog inherits from DayCatLog`() {
        val pDayCatLog = PDayCategoryLog()

        assertInstanceOf(DayCategoryLogDataSource::class.java, pDayCatLog)
    }


    // Tests Assuming Instantiation
    @Nested
    @DisplayName("Given instance of PDayCatLog,")
    inner class GivenPDayCategoryLog {

        // SETUP

        lateinit var pDayCategoryLog : PDayCategoryLog
        lateinit var categoryList : List<Category>

        @BeforeEach
        fun `Given instance of PDayCatLog`() {
            pDayCategoryLog = PDayCategoryLog()
            categoryList = listOf(Category("Test1"), Category("Test2"))
        }


        // TESTS

        // Getter Tests
        @Test
        @DisplayName("Get Categories from Instance.")
        fun `Get Categories from Instance`() {
            val categories = pDayCategoryLog.getCategories()

            assertEquals(listOf<Category>(), categories)

        }

        @Test
        @DisplayName("Get Categories from Interface Instance.")
        fun `Get Categories from Interface Instance`() {
            val cats = (pDayCategoryLog as DayCategoryLogDataSource).getCategories()

            assertEquals(listOf<Category>(), cats)
        }


        // Clear Categories Tests
        @Test
        @DisplayName("Clear All Categories.")
        fun clearAllCategories() {
            pDayCategoryLog.clearAllCategories()

            assertTrue(pDayCategoryLog.getCategories().isEmpty())
        }


        // Add Categories Tests
        @Test
        @DisplayName("Add Categories from Iterable.")
        fun addCategoriesFromIterable() {
            pDayCategoryLog.addCategories(categoryList)

            assertIterableEquals(categoryList, pDayCategoryLog.getCategories())
        }

        @Test
        @DisplayName("Add Categories first calls Clear All Categories.")
        fun addCategoriesFirstCallsClearAllCategories() {
            pDayCategoryLog.addCategories(categoryList)
            pDayCategoryLog.addCategories(emptyList<Category>())

            assertTrue(pDayCategoryLog.getCategories().isEmpty())
        }


        // Tests Assuming a Category is in pDayCatLog
        @Nested
        @DisplayName("Given Category in instance,")
        inner class GivenCategoryInInstance {

            // SETUP

            @BeforeEach
            fun `Given Category in instance`(){
                pDayCategoryLog.addCategories(categoryList)
            }

            // TESTS

            // Getter Tests
            @Test
            @DisplayName("Show Getter on Populated Instance.")
            fun showGetterOnPopulatedInstance() {
                assertIterableEquals(categoryList,pDayCategoryLog.getCategories())
            }


            // Clear Categories Tests
            @Test
            @DisplayName("Clear All Categories on Populated Instance.")
            fun clearAllCategoriesOnPopulatedInstance() {
                pDayCategoryLog.clearAllCategories()
                assertTrue(pDayCategoryLog.getCategories().isEmpty())
            }


            // Add Categories Tests
            @Test
            @DisplayName("Add Categories to Populated Instance.")
            fun addCategoriesToPopulatedInstance() {
                val newCategories : Iterable<Category> =
                    listOf(Category("Test3"), Category("Test4"), Category("Test5"))
                pDayCategoryLog.addCategories(newCategories)
                assertIterableEquals(newCategories, pDayCategoryLog)
            }


            // Iterator Tests
            @Test
            @DisplayName("Show Iterator.")
            fun showIterator() {
                assertInstanceOf(Iterator::class.java, pDayCategoryLog.iterator())
            }
        }
    }
}
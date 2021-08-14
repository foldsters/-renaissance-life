package com.steamtechs.core.data


import com.steamtechs.core.data.platform.PDayCategoryLog
import com.steamtechs.core.domain.Category
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class DayCategoryLogTest {

    // SETUP

    lateinit var dayCategoryLogDataSource: DayCategoryLogDataSource

    @BeforeEach
    fun setup() {
        dayCategoryLogDataSource = PDayCategoryLog()
    }


    // TESTS

    // Instance / Constructor Tests
    @Test
    @DisplayName("Create instance of DayCategoryLog.")
    fun `Create instance of DayCategoryLog`() {
        val dayCategoryLog = DayCategoryLog(dayCategoryLogDataSource)
        assertInstanceOf(DayCategoryLog::class.java, dayCategoryLog)
    }


    // Tests Assuming Instance of DayCategoryLog
    @Nested
    @DisplayName("Given instance of DayCategoryLog,")
    inner class GivenDayCategoryLog {

        // SETUP

        lateinit var dayCategoryLog: DayCategoryLog
        var categoryList = listOf(Category("Test1"), Category("Test2"), Category("Test3"))

        @BeforeEach
        fun `Given instance of PDayCategoryLog`() {
            dayCategoryLog = DayCategoryLog(dayCategoryLogDataSource)
        }


        // TESTS

        // Getter Tests
        @Test
        @DisplayName("Get Categories from Instance.")
        fun `Get Categories from Instance`() {
            val emptyCategoryList = dayCategoryLog.getCategories()
            assertEquals(listOf<Category>(), emptyCategoryList)

        }


        // Clear Categories Tests
        @Test
        @DisplayName("Clear All Categories.")
        fun clearAllCategories() {
            dayCategoryLog.clearAllCategories()
            assertTrue(dayCategoryLog.getCategories().toList().isEmpty())
        }


        // Add Categories Tests
        @Test
        @DisplayName("Clear DB then Add Categories from Iterable.")
        fun clearDbThenAddCategoriesFromIterable() {
            dayCategoryLog.addCategories(categoryList)
            assertIterableEquals(categoryList, dayCategoryLog.getCategories())
        }


        // Assuming Instance of dayCategoryLog has Categories in it
        @Nested
        @DisplayName("Given Populated Instance,")
        inner class GivenPopulatedInstance {

            // SETUP

            @BeforeEach
            fun `Given Populated Instance`() {
                dayCategoryLog.addCategories(categoryList)
            }


            // TESTS

            // Getter Tests
            @Test
            @DisplayName("Show Getter on Populated Instance.")
            fun showGetterOnPopulatedInstance() {
                assertIterableEquals(categoryList, dayCategoryLog.getCategories())
            }

            // Clear Categories Tests
            @Test
            @DisplayName("Clear All Categories on Populated Instance.")
            fun clearAllCategoriesOnPopulatedInstance() {
                dayCategoryLog.clearAllCategories()
                assertTrue(dayCategoryLog.getCategories().toList().isEmpty())
            }

            // Add Categories Tests
            @Test
            @DisplayName("Clear DB then Add Categories to Populated Instance.")
            fun clearDbThenAddCategoriesToPopulatedInstance() {
                val newCategories: Iterable<Category> =
                    listOf(Category("Test3"), Category("Test4"), Category("Test5"))
                dayCategoryLog.addCategories(newCategories)
                assertIterableEquals(newCategories, dayCategoryLog.getCategories())
            }
        }
    }
}
package com.steamtechs.core.data


import com.steamtechs.core.domain.Category
import com.steamtechs.renaissancelife.platform.datasources.PDayCategoryLog
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class DayCategoryLogTest {

    lateinit var dayCategoryLog: DayCategoryLog

    val dCDS: DayCategoryLogDataSource = PDayCategoryLog()
    val categoryList = listOf(Category("Test1"), Category("Test2"), Category("Test3"))


    @Test
    @DisplayName("Create instance of DayCatLog.")
    fun `Create instance of DayCatLog`() {
        dayCategoryLog = DayCategoryLog(dCDS)
        assertInstanceOf(DayCategoryLog::class.java, dayCategoryLog)
    }

    @Nested
    @DisplayName("Given instance of DayCatLog,")
    inner class GivenDayCategoryLog {


        @BeforeEach
        fun `Given instance of PDayCatLog`() {
            dayCategoryLog = DayCategoryLog(dCDS)
        }

        @Test
        @DisplayName("Get Categories from Instance.")
        fun `Get Categories from Instance`() {
            val categoryList = dayCategoryLog.getCategories()
            assertEquals(listOf<Category>(), categoryList)

        }

        @Test
        @DisplayName("Clear All Categories.")
        fun clearAllCategories() {
            dayCategoryLog.clearAllCategories()
            assertTrue(dayCategoryLog.getCategories().toList().isEmpty())
        }

        @Test
        @DisplayName("Add Categories from Iterable.")
        fun addCategoriesFromIterable() {
            dayCategoryLog.addCategories(categoryList)
            assertIterableEquals(categoryList, dayCategoryLog.getCategories())
        }

        @Nested
        @DisplayName("Given Populated Instance,")
        inner class GivenPopulatedInstance {

            @BeforeEach
            fun `Given Populated Instance`() {
                dayCategoryLog.addCategories(categoryList)
            }

            @Test
            @DisplayName("Show Getter on Populated Instance.")
            fun showGetterOnPopulatedInstance() {
                assertIterableEquals(categoryList, dayCategoryLog.getCategories())
            }

            @Test
            @DisplayName("Clear All Categories on Populated Instance.")
            fun clearAllCategoriesOnPopulatedInstance() {
                dayCategoryLog.clearAllCategories()
                assertTrue(dayCategoryLog.getCategories().toList().isEmpty())
            }

            @Test
            @DisplayName("Add Categories to Populated Instance.")
            fun addCategoriesToPopulatedInstance() {
                val newCategories: Iterable<Category> =
                    listOf(Category("Test3"), Category("Test4"), Category("Test5"))
                dayCategoryLog.addCategories(newCategories)
                assertIterableEquals(newCategories, dayCategoryLog.getCategories())
            }

        }
    }
}
package com.steamtechs.core.data.platform

import com.steamtechs.core.domain.Category
import com.steamtechs.core.data.DayCategoryLogDataSource
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*

internal class PDayCategoryLogTest {

    @Test
    @DisplayName("Create instance of PDayCatLog.")
    fun `Create instance of PDayCatLog`() {
        val pdcl = PDayCategoryLog()
        assertInstanceOf(PDayCategoryLog::class.java, pdcl)
    }

    @Test
    @DisplayName("Check PDayCatLog inherits from DayCatLog.")
    fun `Check PDayCatLog inherits from DayCatLog`() {
        val pdcl = PDayCategoryLog()
        assertInstanceOf(DayCategoryLogDataSource::class.java, pdcl)
    }


    @Nested
    @DisplayName("Given instance of PDayCatLog,")
    inner class GivenPDayCategoryLog {
        lateinit var pdcl1: PDayCategoryLog
        val categoriesList = listOf<Category>(Category("Test1"), Category("Test2"))


        @BeforeEach
        fun `Given instance of PDayCatLog`() {
            pdcl1 = PDayCategoryLog()
        }

        @Test
        @DisplayName("Get Categories from Instance.")
        fun `Get Categories from Instance`() {
            val cats = pdcl1.getCategories()
            assertEquals(listOf<Category>(), cats)

        }

        @Test
        @DisplayName("Get Categories from Interface Instance.")
        fun `Get Categories from Interface Instance`() {
            val cats = (pdcl1 as DayCategoryLogDataSource).getCategories()
            assertEquals(listOf<Category>(), cats)
        }

        @Test
        @DisplayName("Clear All Categories.")
        fun clearAllCategories() {
            pdcl1.clearAllCategories()
            assertTrue(pdcl1.getCategories().isEmpty())
        }

        @Test
        @DisplayName("Add Categories from Iterable.")
        fun addCategoriesFromIterable() {
            pdcl1.addCategories(categoriesList)
            assertIterableEquals(categoriesList, pdcl1.getCategories())
        }

        @Test
        @DisplayName("Add Categories first calls Clear All Categories.")
        fun addCategoriesFirstCallsClearAllCategories() {
            pdcl1.addCategories(categoriesList)
            pdcl1.addCategories(emptyList<Category>())
            assertTrue(pdcl1.getCategories().isEmpty())
        }

        @Nested
        @DisplayName("Given Category in instance,")
        inner class GivenCategoryInInstance {

            @BeforeEach
            fun `Given Category in instance`(){
                pdcl1.addCategories(categoriesList)
            }

            @Test
            @DisplayName("Show Getter on Populated Instance.")
            fun showGetterOnPopulatedInstance() {
                assertIterableEquals(categoriesList,pdcl1.getCategories())
            }

            @Test
            @DisplayName("Clear All Categories on Populated Instance.")
            fun clearAllCategoriesOnPopulatedInstance() {
                pdcl1.clearAllCategories()
                assertTrue(pdcl1.getCategories().isEmpty())
            }

            @Test
            @DisplayName("Add Categories to Populated Instance.")
            fun addCategoriesToPopulatedInstance() {
                val newCategories : Iterable<Category> =
                    listOf(Category("Test3"), Category("Test4"), Category("Test5"))
                pdcl1.addCategories(newCategories)
                assertIterableEquals(newCategories, pdcl1)
            }

            @Test
            @DisplayName("Show Iterator.")
            fun showIterator() {
                assertInstanceOf(Iterator::class.java, pdcl1.iterator())
            }

        }
    }
}
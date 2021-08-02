package com.steamtechs.renaissancelife.platform.datasources

import com.steamtechs.core.domain.Category
import com.steamtechs.core.data.DayCatLogDataSource
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.Assertions.assertThrows
import kotlin.IllegalArgumentException

internal class PDayCatLogTest {

    @Test
    @DisplayName("Create instance of PDayCatLog.")
    fun `Create instance of PDayCatLog`() {
        var pdcl = PDayCatLog()
        assertInstanceOf(PDayCatLog::class.java, pdcl)
    }

    @Test
    @DisplayName("Check PDayCatLog inherets from DayCatLog.")
    fun `Check PDayCatLog inherets from DayCatLog`() {
        var pdcl = PDayCatLog()
        assertInstanceOf(DayCatLogDataSource::class.java, pdcl)
    }


    @Nested
    @DisplayName("Given instance of PDayCatLog,")
    inner class GivenPDayCatLog {
        lateinit var pdcl1: PDayCatLog
        lateinit var cat1: Category
        lateinit var cat2: Category


        @BeforeEach
        fun `Given instance of PDayCatLog`() {
            pdcl1 = PDayCatLog()
            cat1 = Category("Test")
            cat2 = Category("Test2")
        }

        @Test
        @DisplayName("Get Categories from Instance.")
        fun `Get Categories from Instance`() {
            var cats = pdcl1.getCategories()
            assertEquals(listOf<Category>(), cats)

        }

        @Test
        @DisplayName("Get Categories from Interface Instance.")
        fun `Get Categories from Interface Instance`() {
            var cats = (pdcl1 as DayCatLogDataSource).getCategories()
            assertEquals(listOf<Category>(), cats)

        }

        @Test
        @DisplayName("Add Category to PDayCatLog.")
        fun `Add Category to PDayCatLog`() {
            pdcl1.addCategory(cat1)
            assertEquals(cat1, pdcl1.iterator().next())
        }

        @Test
        @DisplayName("Add another category to instance.")
        fun `Add another category to instance`() {
            pdcl1.addCategory(cat1)
            pdcl1.addCategory(cat2)
            assertAll(
                {assertTrue(pdcl1.contains(cat1))},
                {assertTrue(pdcl1.contains(cat2))}
            )

        }

        @Nested
        @DisplayName("Given Category in instance,")
        inner class GivenCategoryInInstance {

            @BeforeEach
            fun `Given Category in instance`(){
                pdcl1.addCategory(cat1)
            }

            @Test
            @DisplayName("Delete Category from PDayCatLog.")
            fun `Add Category to PDayCatLog`() {
                pdcl1.deleteCategory(cat1)
                assertFalse(pdcl1.contains(cat1))
            }

            @Test
            @DisplayName("Throw IllegalArgumentsException when deleting Category not in PDayCatLog.")
            fun `Throw IllegalArgumentsException when deleting Category not in PDayCatLog`() {
                assertThrows<IllegalArgumentException> {
                    pdcl1.deleteCategory(cat2)
                }
            }



        }








    }

}
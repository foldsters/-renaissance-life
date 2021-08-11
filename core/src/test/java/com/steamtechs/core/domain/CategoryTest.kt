package com.steamtechs.core.domain

import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertInstanceOf

internal class CategoryTest{

    // TESTS

    @Test
    @DisplayName("Create instance of Category.")
    fun `Create instance of Category`() {
        val category = Category("Test Category")

        assertInstanceOf(Category::class.java, category)
    }

    @Test
    @DisplayName("Instance of Category initialized with Negative tickValue throws Error")
    fun `Instance of Category initialized with negative tick value throws error`() {
        assertThrows<java.lang.IllegalArgumentException> {
            Category("Test Category", _tickValue = -4)
        }
    }


    // Tests Assuming instance of Category
    @Nested
    @DisplayName("Given instance of Category,")
    inner class GivenTestCategory{

        // SETUP

        private lateinit var category : Category

        @BeforeEach
        fun `Given instance of Category`(){
            category = Category("Test Category").also {it.date = "2021-08-10"}
        }


        // TESTS

        // Getter Tests
        @Test
        @DisplayName("show getter for title.")
        fun `show getter for title`() {
            assertEquals("Test Category", category.title)
        }

        @Test
        @DisplayName("show getter for tickValue.")
        fun `show getter for tickValue`() {
            assertEquals(0, category.tickValue)
        }

        @Test
        @DisplayName("show getter for date.")
        fun `show getter for date`() {
            assertEquals("2021-08-10", category.date)
        }


        // Setter Tests
        @Test
        @DisplayName("show setter for title.")
        fun `show setter for title`() {
            val newTitle = "New Category Title"
            category.title = newTitle

            assertEquals(newTitle, category.title)
        }

        @Test
        @DisplayName("show setter for tickValue.")
        fun `show setter for tickValue`() {
            category.tickValue = 248

            assertEquals(248, category.tickValue)
        }

        @Test
        @DisplayName("show setter for date.")
        fun `show setter for date`() {
            category.date = "2021-08-11"

            assertEquals("2021-08-11", category.date)
        }


        // Logical Tests
        @Test
        @DisplayName("tickValue cannot be set with Negative value.")
        fun `tickValue cannot be set with Negative value`() {
            assertThrows<IllegalArgumentException> {
                category.tickValue = -3
            }
        }
    }
}
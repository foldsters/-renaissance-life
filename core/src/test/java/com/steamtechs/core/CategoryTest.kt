package com.steamtechs.core

import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertInstanceOf

internal class CategoryTest{

    @Test
    @DisplayName("Create instance of Category.")
    fun `Create instance of Category`() {
        val testCategory = Category("Test Category")
        assertInstanceOf(Category::class.java, testCategory)
    }

    @Nested
    @DisplayName("Given instance of Category called 'testCategory', ")
    inner class GivenTestCategory{
        lateinit var testCategory : Category

        @BeforeEach
        fun `Given instance of Category called 'testCategory'`(){
            testCategory = Category("Test Category")
        }

        @Test
        @DisplayName("show getter for title.")
        fun `show getter for title`() {
            assertEquals("Test Category", testCategory.title)
        }

        @Test
        @DisplayName("show setter for title.")
        fun `show setter for title`() {
            val newTitle = "New Category Title"
            testCategory.title = newTitle
            assertEquals(newTitle, testCategory.title)
        }

        //show getter for tickValue
        @Test
        @DisplayName("show tickValue is init to zero.")
        fun `show tickValue is init to zero`() {
            assertEquals(0,testCategory.tickValue)
        }

        @Test
        @DisplayName("show setter for tickValue.")
        fun `show setter for tickValue`() {
            testCategory.tickValue = 248
            assertEquals(248, testCategory.tickValue)
        }

        @Test
        @DisplayName("tickValue cannot be set with Negative value.")
        fun `tickValue cannot be set with Negative value`() {
            assertThrows<IllegalArgumentException> { testCategory.tickValue = -3}
        }

    }
}
package com.steamtechs.core.interactors

import com.steamtechs.core.data.DayCategoryLog
import com.steamtechs.core.domain.Category
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class GetCategoriesTest {

    // SETUP

    lateinit var testPDayCatLog : PDayCategoryLog
    lateinit var testDayCatLog : DayCategoryLog
    lateinit var categoryList : List<Category>

    @BeforeEach
    fun setup() {
        testPDayCatLog = PDayCategoryLog()
        testDayCatLog = DayCategoryLog(testPDayCatLog)
        categoryList = listOf(Category("Test1"), Category("Test2"), Category("Test3"))
    }


    // TESTS

    @Test
    @DisplayName("GetCategories returns Iterable of Category.")
    fun `GetCategories returns Iterable of Category`() {
        testPDayCatLog.addCategories(categoryList)
        assertInstanceOf(Iterable::class.java, GetCategories(testDayCatLog))
    }
}
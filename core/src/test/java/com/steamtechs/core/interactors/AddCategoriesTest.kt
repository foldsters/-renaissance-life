package com.steamtechs.core.interactors

import com.steamtechs.core.data.DayCategoryLog
import com.steamtechs.core.data.platform.PDayCategoryLog
import com.steamtechs.core.domain.Category
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class AddCategoriesTest {

    // SETUP

    lateinit var dayCategoryLog: DayCategoryLog
    lateinit var categoryList : List<Category>

    @BeforeEach
    fun setup() {
        val pDayCategoryLog = PDayCategoryLog()
        dayCategoryLog = DayCategoryLog(pDayCategoryLog)
        categoryList = listOf<Category>(Category("Test1"), Category("Test2"), Category("Test3"))
    }


    // TESTS

    @Test
    @DisplayName("Show AddCategory.")
    fun `Show AddCategory`() {
        AddCategories(dayCategoryLog, categoryList)
        assertEquals(categoryList, dayCategoryLog.getCategories())
    }
}
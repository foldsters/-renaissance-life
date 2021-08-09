package com.steamtechs.core.interactors

import com.steamtechs.core.data.DayCategoryLog
import com.steamtechs.core.domain.Category
import com.steamtechs.renaissancelife.platform.datasources.PDayCategoryLog
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class GetCategoriesTest{

    private val testPDayCatLog = PDayCategoryLog()
    private val testDayCatLog = DayCategoryLog(testPDayCatLog)
    private val categoryList = listOf(Category("Test1"), Category("Test2"), Category("Test3"))

    @Test
    @DisplayName("GetCategories returns Iterable of Category.")
    fun `GetCategories returns Iterable of Category`() {
        testPDayCatLog.addCategories(categoryList)
        assertInstanceOf(Iterable::class.java, GetCategories(testDayCatLog))
    }
}
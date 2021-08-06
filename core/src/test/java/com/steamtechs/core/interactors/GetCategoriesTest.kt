package com.steamtechs.core.interactors

import com.steamtechs.core.data.DayCategoryLog
import com.steamtechs.core.domain.Category
import com.steamtechs.renaissancelife.platform.datasources.PDayCategoryLog
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class GetCategoriesTest{

    val testPDayCatLog = PDayCategoryLog()
    val testCat1 = Category("Test1")
    val testCat2 = Category("Test2")
    val testDayCatLog = DayCategoryLog(testPDayCatLog)

    @Test
    @DisplayName("GetCategories returns Iterable of Category.")
    fun `GetCategories returns Iterable of Category`() {
        testPDayCatLog.addCategory(testCat1)
        testPDayCatLog.addCategory(testCat2)
        assertInstanceOf(Iterable::class.java, GetCategories(testDayCatLog))
    }
}
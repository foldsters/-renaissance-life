package com.steamtechs.core.interactors

import com.steamtechs.core.data.DayCategoryLog
import com.steamtechs.core.domain.Category
import com.steamtechs.renaissancelife.platform.datasources.PDayCategoryLog
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.IllegalArgumentException

internal class DeleteCategoryTest{

    val testPDayCatLog = PDayCategoryLog()
    val testCat1 = Category("Test1")
    val testDayCatLog= DayCategoryLog(testPDayCatLog).also { AddCategory(it, testCat1) }

    @Test
    @DisplayName("show DeleteCategory.")
    fun `show DeleteCategory`() {
        DeleteCategory(testDayCatLog, testCat1)
    }

    @Test
    @DisplayName("Don't allow DeleteCategory on Category not in DayCatLog.")
    fun `Don't allow DeleteCategory on Category not in DayCatLog`() {
        val testCat2 = Category("Test2")
        assertThrows<IllegalArgumentException>{DeleteCategory(testDayCatLog, testCat2)}
    }
}
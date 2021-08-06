package com.steamtechs.core.interactors

import com.steamtechs.core.data.DayCategoryLog
import com.steamtechs.core.domain.Category
import com.steamtechs.renaissancelife.platform.datasources.PDayCategoryLog
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class AddCategoryTest{ //

    val testPDayCatLog = PDayCategoryLog()
    val testCat1 = Category("Test1")
    val testDayCatLog = DayCategoryLog(testPDayCatLog)

    @Test
    @DisplayName("Show AddCategory.")
    fun `Show AddCategory`() {
        AddCategory(testDayCatLog, testCat1)
        assert(testDayCatLog.getCategories().contains(testCat1))
    }


}
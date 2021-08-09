package com.steamtechs.core.interactors

import com.steamtechs.core.data.DayCategoryLog
import com.steamtechs.core.domain.Category
import com.steamtechs.core.data.platform.PDayCategoryLog
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class ClearAllCategoriesInStorageTest{

    val testPDayCatLog = PDayCategoryLog()
    private val categoryList = listOf(Category("Test1"), Category("Test2"), Category("Test3"))
    val dayCatLog = DayCategoryLog(testPDayCatLog).also { AddCategories(it, categoryList) }

    @Test
    @DisplayName("Clear All Categories on Populated Instance")
    fun clearAllCategoriesOnPopulatedInstance() {
        ClearAllCategoriesInStorage(dayCatLog)
    }

}
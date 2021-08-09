package com.steamtechs.core.interactors

import com.steamtechs.core.data.DayCategoryLog
import com.steamtechs.core.domain.Category
import com.steamtechs.renaissancelife.platform.datasources.PDayCategoryLog
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.IllegalArgumentException

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
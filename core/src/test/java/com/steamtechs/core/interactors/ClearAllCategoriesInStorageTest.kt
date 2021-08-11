package com.steamtechs.core.interactors

import com.steamtechs.core.data.DayCategoryLog
import com.steamtechs.core.domain.Category
import com.steamtechs.core.data.platform.PDayCategoryLog
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class ClearAllCategoriesInStorageTest{

    // SETUP

    lateinit var dayCatLog : DayCategoryLog

    @BeforeEach
    fun setup() {
        val pDayCatLog = PDayCategoryLog()
        val categoryList = listOf(Category("Test1"), Category("Test2"), Category("Test3"))
        dayCatLog = DayCategoryLog(pDayCatLog).also { AddCategories(it, categoryList) }
    }

    // TESTS

    @Test
    @DisplayName("Clear All Categories on Populated Instance")
    fun clearAllCategoriesOnPopulatedInstance() {
        ClearAllCategoriesInStorage(dayCatLog)
    }

}
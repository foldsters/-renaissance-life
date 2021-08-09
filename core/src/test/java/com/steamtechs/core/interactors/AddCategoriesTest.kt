package com.steamtechs.core.interactors

import com.steamtechs.core.data.DayCategoryLog
import com.steamtechs.core.domain.Category
import com.steamtechs.renaissancelife.platform.datasources.PDayCategoryLog
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class AddCategoriesTest{ //

    private val pDayCategoryLog = PDayCategoryLog()
    private val dayCategoryLog = DayCategoryLog(pDayCategoryLog)
    val categoryList = listOf<Category>(Category("Test1"), Category("Test2"), Category("Test3"))

    @Test
    @DisplayName("Show AddCategory.")
    fun `Show AddCategory`() {
        AddCategories(dayCategoryLog, categoryList)
        assertEquals(categoryList, dayCategoryLog.getCategories())
    }


}
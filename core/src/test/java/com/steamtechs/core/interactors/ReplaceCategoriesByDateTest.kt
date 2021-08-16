package com.steamtechs.core.interactors

import com.steamtechs.core.data.DayCategoryLog
import com.steamtechs.core.domain.Category
import com.steamtechs.renaissancelife.platform.datasources.PDayCategoryLog
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class ReplaceCategoriesByDateTest{

    val sourceCategoryList = listOf<Category>(
        Category("Cat1", "2021-08-16", 21),
        Category("Cat2", "2021-08-16", 23),
        Category("Cat3", "2021-08-16", 0),
        Category("Cat4", "2021-08-16", 14),
    )
    val sourceDayCategoryLog = DayCategoryLog(PDayCategoryLog().also { it.addCategories(sourceCategoryList) })
    lateinit var targetDayCategoryLog: DayCategoryLog

    @Test
    @DisplayName("Empty sourceDayCategoryLog throws IllegalArgumentException.")
    fun `Empty sourceDayCategoryLog throws Exception`() {
        assertThrows(IllegalArgumentException::class.java,
            {ReplaceCategoriesByDate.ensureSourceNotEmpty(DayCategoryLog(PDayCategoryLog()))})
    }

    @Test
    @DisplayName("sourceDayCategoryLog with Categories of Multiple Dates throws IllegalArgumentsException.")
    fun `sourceDayCategoryLog with Categories of Multiple Dates throws IllegalArgumentsException`() {
        sourceDayCategoryLog.addCategories(listOf(Category("DifferentDateCat", "2020-03-09", 9)))

        assertThrows(IllegalArgumentException::class.java,
            {ReplaceCategoriesByDate.ensureAllSameDate("2020-03-09", sourceDayCategoryLog)})
    }

    @Nested
    @DisplayName("Given a targetDayCategoryLog, ")
    inner class GivenTargetDayCategoryLog{
        val targetCategoryList = listOf<Category>(
            Category("Cat1", "2021-08-16", 13),
            Category("Cat2", "2021-08-16", 9),
            Category("Cat3", "2021-08-16", 1),
            Category("Cat4", "2021-08-16", 24),
            Category("DifferentDateCat", "2020-03-09", 6)
        )
        lateinit var targetDayCategoryLog : DayCategoryLog

        @BeforeEach
        fun `Given a targetDayCategoryLog`(){
            targetDayCategoryLog = DayCategoryLog(PDayCategoryLog().also { it.addCategories(targetCategoryList) })
        }


    }

}
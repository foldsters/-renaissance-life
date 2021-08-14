package com.steamtechs.core.interactors

import com.steamtechs.core.data.DayCategoryLog
import com.steamtechs.core.data.platform.PDayCategoryLog
import com.steamtechs.core.domain.Category
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertInstanceOf
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

internal class GetCategoriesForDateRangeTest{
    private val categoryList = listOf(
        Category("Cat1", "2021-08-21", 3),
        Category("Cat2", "2121-06-13", 23),
        Category("Cat3", "2021-01-21", 5),
        Category("Cat4", "2021-12-08", 0),
        Category("Cat5", "2019-08-21", 9),
    )
    val dayCategoryLog = DayCategoryLog(PDayCategoryLog().also { it.addCategories(categoryList) })
    val startDate : String = "2021-01-01"
    val endDate : String = "2021-12-31"

    private val checkList = listOf(
        Category("Cat1", "2021-08-21", 3),
        Category("Cat3", "2021-01-21", 5),
        Category("Cat4", "2021-12-08", 0)
    )

    val newCategoryList = GetCategoriesForDateRange(dayCategoryLog, startDate, endDate)


    @Test
    @DisplayName("GetCategoriesForDateRange return an Iterable of Category.")
    fun `GetCategoriesForDateRange return an Iterable of Category`() {
        assertAll(
            { assertInstanceOf(Iterable::class.java, newCategoryList) },
            { assertInstanceOf(Category::class.java, newCategoryList.iterator().next()) }
        )
    }

    @Test
    @DisplayName("Returned Categories are between the StartDate and EndDate (Inclusive).")
    fun `Returned Categories are between the StartDate and EndDate (Inclusive)`() {
        val strippedNewCategoryList = newCategoryList.filter { startDate < it.date && it.date > endDate }
        assert(strippedNewCategoryList.isEmpty())
    }
}
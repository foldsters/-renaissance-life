package com.steamtechs.core.interactors

import com.steamtechs.core.data.DayCategoryLog
import com.steamtechs.core.data.platform.PDayCategoryLog
import com.steamtechs.core.domain.Category
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class GetCategoriesForDateTest{
    private val categoryList = listOf(
        Category("Cat1", "2021-08-21", 3),
        Category("Cat2", "2021-06-13", 23),
        Category("Cat3", "2021-08-21", 5),
        Category("Cat4", "2021-12-21", 0),
        Category("Cat5", "2021-08-21", 9),
    )
    private val dayCategoryLog = DayCategoryLog(PDayCategoryLog().also { it.addCategories(categoryList) })

    private val checkList = listOf(
        Category("Cat1", "2021-08-21", 3),
        Category("Cat3", "2021-08-21", 5),
        Category("Cat5", "2021-08-21", 9)
    )

    private val newCategoryList = GetCategoriesForDate(dayCategoryLog, "2021-08-21")



    @Test
    @DisplayName("GetCategoriesForDate returns an Iterable of Category.")
    fun `GetCategoriesForDate returns an Iterable of Category`() {
        assertAll(
            {assertInstanceOf(Iterable::class.java, newCategoryList)},
            {assertInstanceOf(Category::class.java, newCategoryList.iterator().next())}
        )
    }

    @Test
    @DisplayName("Get Iterable of Categories for a given Date.")
    fun `Get Iterable of Categories for a given Date`() {
        val newCategoryList = GetCategoriesForDate(dayCategoryLog, "2021-08-21")
        assertIterableEquals(checkList, newCategoryList)
    }

}
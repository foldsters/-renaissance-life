package com.steamtechs.core.domain.businesslogic

import com.steamtechs.core.data.DayCategoryLog
import com.steamtechs.core.data.platform.PDayCategoryLog
import com.steamtechs.core.domain.Category
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class CategoryScraperTest{

    private val dayCategoryLog = DayCategoryLog(PDayCategoryLog().also {
        it.addCategories(listOf(Category("This Category"))) })

    @Test
    @DisplayName("getCategoryFromDate returns an Iterable of Category.")
    fun `getCategoryFromDate returns an Iterable of Category`() {
        val catIter = CategoryScraper.getCategoryFromDate(dayCategoryLog)
        assertAll(
            {assertInstanceOf(Iterable::class.java, catIter)},
            {assertInstanceOf(Category::class.java, catIter.iterator().next())}
        )
    }


}
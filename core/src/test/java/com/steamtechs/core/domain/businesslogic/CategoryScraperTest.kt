package com.steamtechs.core.domain.businesslogic

import com.steamtechs.core.data.DayCatLog
import com.steamtechs.core.domain.Category
import com.steamtechs.renaissancelife.platform.datasources.PDayCatLog
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class CategoryScraperTest{

    private val dayCatLog = DayCatLog(PDayCatLog().also { it.addCategory(Category("This Category")) })

    @Test
    @DisplayName("getCategoryFromDate returns an Iterable of Category.")
    fun `getCategoryFromDate returns an Iterable of Category`() {
        val catIter = CategoryScraper.getCategoryFromDate(dayCatLog)
        assertAll(
            {assertInstanceOf(Iterable::class.java, catIter)},
            {assertInstanceOf(Category::class.java, catIter.iterator().next())}
        )
    }


}
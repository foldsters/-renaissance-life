package com.steamtechs.core.interactors

import com.steamtechs.core.data.CategoryRepository
import com.steamtechs.core.domain.Category
import com.steamtechs.platform.datasources.PCategoryRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import com.steamtechs.core.interactors.TicklessCategoryRepositoryFromDateList

internal class TicklessCategoryRepositoryFromDateListTest{
    val dateList = listOf<String>(
        "2021-09-02", "2001-06-18", "1987-02-09" ,"2013-09-13" ,"2022-11-31", "2015-01-17"
    )

    val sourceRepository = CategoryRepository(PCategoryRepository().apply {
        addCategories( listOf<Category>(
            Category("This One", "1995-12-27", 0),
            Category("No", "1999-07-21", 3),
            Category("This Way", "2005-06-15", 5),
            Category("No", "2020-03-09", 6)
        ))
    })

    val targetRepository = CategoryRepository(PCategoryRepository())

    val expectedRepository = CategoryRepository(PCategoryRepository().apply {
        addCategories( listOf(
            Category("This One", "2021-09-02", 0),
            Category("No", "2021-09-02", 0),
            Category("This Way", "2021-09-02", 0),
            Category("This One", "2001-06-18", 0),
            Category("No", "2001-06-18", 0),
            Category("This Way", "2001-06-18", 0),
            Category("This One", "1987-02-09", 0),
            Category("No", "1987-02-09", 0),
            Category("This Way", "1987-02-09", 0),
            Category("This One", "2013-09-13", 0),
            Category("No", "2013-09-13", 0),
            Category("This Way", "2013-09-13", 0),
            Category("This One", "2022-11-31", 0),
            Category("No", "2022-11-31", 0),
            Category("This Way", "2022-11-31", 0),
            Category("This One", "2015-01-17", 0),
            Category("No", "2015-01-17", 0),
            Category("This Way", "2015-01-17", 0),
        ))
    })

    @Test
    @DisplayName("Populated targetRepository looks like expectedRepository.")
    fun `Populated targetRepository looks like expectedRepository`() {
        TicklessCategoryRepositoryFromDateList(
            dateList,
            sourceRepository,
            targetRepository
        )
        assertEquals(expectedRepository.getCategories(), targetRepository.getCategories())
    }




}
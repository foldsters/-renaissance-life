package com.steamtechs.core.interactors

import com.steamtechs.core.data.CategoryRepository
import com.steamtechs.core.domain.Category
import com.steamtechs.platform.datasources.PCategoryRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class UpdateTargetRepositoryWithSourceRepositoryTest{

    lateinit var targetRepository : CategoryRepository

    val sourceRepository = CategoryRepository(PCategoryRepository().apply {
        addCategories( listOf(
            Category("This One", "2021-09-02", 4),
            Category("No", "2021-09-02", 6),
            Category("Cat1", "2021-09-02", 8),
            Category("This One", "2001-06-18", 2),
            Category("No", "2001-06-18", 3),
            Category("Cat1", "2001-06-18", 4),
            Category("This One", "1987-02-09", 14),
            Category("No", "1987-02-09", 0),
            Category("Cat1", "1987-02-09", 0),
            Category("This One", "2013-09-13", 9),
            Category("No", "2013-09-13", 4),
            Category("Cat1", "2013-09-13", 8),
            Category("This One", "2022-11-31", 7),
            Category("No", "2022-11-31", 1),
            Category("Cat1", "2022-11-31", 1),
            Category("This One", "2015-01-16", 0),
            Category("No", "2015-01-16", 10),
            Category("Cat1", "2015-01-16", 9),
        ))
    })

    @BeforeEach
    @DisplayName("Init targetRepository")
    fun `Init targetRepository`(){
        targetRepository = CategoryRepository(PCategoryRepository().apply {
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
    }

    @Test
    @DisplayName("Interactor removes Category 'This Way' from targetRepository.")
    fun `Interactor removes Category 'This Way' from targetRepository`() {
        UpdateTargetRepositoryWithSourceRepository(targetRepository, sourceRepository)
        val updatedTargetCategoryTitles = targetRepository.getCategories().groupBy { it.title }
        assertFalse(updatedTargetCategoryTitles.containsKey("This Way"))
    }

    @Test
    @DisplayName("Updated targetRepository does not contain Categories for '2015-01-17' after Update.")
    fun `Updated targetRepository does not contain Categories for '2015-01-17' after Update`() {
        UpdateTargetRepositoryWithSourceRepository(targetRepository, sourceRepository)
        val updatedTargetCategoryDates = targetRepository.getCategories().groupBy { it.date }
        assertFalse(updatedTargetCategoryDates.containsKey("2015-01-17"))
    }



}
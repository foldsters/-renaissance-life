package com.steamtechs.core.interactors

import com.steamtechs.core.data.CategoryRepository
import com.steamtechs.core.domain.Category
import com.steamtechs.platform.datasources.PCategoryRepository
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*

internal class ReplaceCategoriesByDateTest{

    val sourceDate = "2021-08-16"

    val sourceCategoryList = listOf<Category>(
        Category("Updated Tick Category", "2021-08-16", 21),
        Category("Unchanged Category", "2021-08-16", 23),
    )
    val sourceCategoryRepository = CategoryRepository(PCategoryRepository().also { it.addCategories(sourceCategoryList) })
    lateinit var targetCategoryRepository: CategoryRepository

    @Test
    @DisplayName("Empty sourceCategoryRepository throws IllegalArgumentException.")
    fun `Empty sourceCategoryRepository throws Exception`() {
        assertThrows(IllegalArgumentException::class.java,
            {ReplaceCategoriesByDate.ensureSourceNotEmpty(CategoryRepository(PCategoryRepository()))})
    }

    @Test
    @DisplayName("sourceCategoryRepository with Categories of Multiple Dates throws IllegalArgumentsException.")
    fun `sourceCategoryRepository with Categories of Multiple Dates throws IllegalArgumentsException`() {
        val tempSourceCategoryList = sourceCategoryRepository.getCategories()
        val tempAddlCategoryList = listOf(Category("DifferentDateCat", "2020-03-09", 9))
        sourceCategoryRepository.addCategories( tempSourceCategoryList + tempAddlCategoryList)
        assertThrows(IllegalArgumentException::class.java
        ) { ReplaceCategoriesByDate.ensureAllSameDate(sourceDate, sourceCategoryRepository) }
    }

    @Nested
    @DisplayName("Given a targetCategoryRepository, ")
    inner class GivenTargetCategoryRepository{
        val targetCategoryList = listOf<Category>(
            Category("Updated Tick Category", "2021-08-16", 99),
            Category("Unchanged Category", "2021-08-16", 23),
            Category("To Be Deleted Category", "2021-08-16", 11),
            Category("DifferentDateCat", "2020-03-09", 6)
        )
        lateinit var targetCategoryRepository : CategoryRepository

        @BeforeEach
        fun `Given a targetCategoryRepository`(){
            targetCategoryRepository = CategoryRepository(PCategoryRepository().also { it.addCategories(targetCategoryList) })
        }

        @Test
        @DisplayName("Target contains Categories from Source.")
        fun `Target contains Categories from Source`() {
            ReplaceCategoriesByDate(sourceDate, sourceCategoryRepository, targetCategoryRepository)
            assert((targetCategoryRepository.getCategories() as Collection).containsAll(sourceCategoryList))
        }

        @Test
        @DisplayName("All Target Categories from SourceDate are overwritten by Source Categories.")
        fun `All Target Categories from SourceDate are overwritten by Source Categories`() {
            ReplaceCategoriesByDate(sourceDate, sourceCategoryRepository, targetCategoryRepository)
            val updatedCategoriesList = targetCategoryRepository.getCategories().toList()
            val sourceDateTargetCategoriesList = updatedCategoriesList.filter { it.date == "2021-08-16" }
            assertEquals(sourceCategoryList,sourceDateTargetCategoriesList)
        }

    }
}
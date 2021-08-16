package com.steamtechs.core.interactors

import com.steamtechs.core.data.CategoryRepository
import com.steamtechs.core.domain.Category
import com.steamtechs.platform.datasources.PCategoryRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class SaveToExternalCategoryRepositoryTest{

    val sourceCategoryList = listOf<Category>(
        Category("Cat1", "2021-08-16", 99),
        Category("Cat2", "2021-08-16", 3),
        Category("Cat3", "2021-08-16", 17),
        Category("Cat4", "2020-03-09", 6)
    )
    val sourceCategoryRepository = CategoryRepository(PCategoryRepository().also {
        it.addCategories(sourceCategoryList) })

    val targetCategoryList = listOf<Category>(
        Category("Cat1", "2021-08-16", 99),
        Category("Cat2", "2021-08-16", 23),
        Category("Cat3", "2021-08-16", 11),
        Category("Cat4", "2020-03-09", 6)
    )
    val targetCategoryRepository = CategoryRepository(PCategoryRepository().also {
        it.addCategories(targetCategoryList) })

    @Test
    @DisplayName("Interactor writes Categories from sourceCategoryRepository to targetCategoryRepository.")
    fun `Interactor writes Categories from sourceCategoryRepository to targetCategoryRepository`() {
        SaveToExternalCategoryRepository(sourceCategoryRepository, targetCategoryRepository)
        assertEquals(sourceCategoryRepository.getCategories(), targetCategoryRepository.getCategories())
    }


}
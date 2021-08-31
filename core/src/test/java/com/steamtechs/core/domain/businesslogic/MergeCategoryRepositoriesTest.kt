package com.steamtechs.core.domain.businesslogic

import com.steamtechs.core.data.CategoryRepository
import com.steamtechs.core.domain.Category
import com.steamtechs.platform.datasources.PCategoryRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class MergeCategoryRepositoriesTest{

    var oldRepo = CategoryRepository(
        PCategoryRepository().also {
            it.addCategories(
                listOf(
                    Category("oldCat1","2020-08-29", 3),
                    Category("oldCat2","2021-08-29", 4),
                    Category("oldCat3","2021-08-29", 6),
                    Category("oldCat4","2020-03-09", 1),
                )
            ) })

    var newRepo = CategoryRepository(
        PCategoryRepository().also {
            it.addCategories(
                listOf(
                    Category("oldCat2","2021-08-29", 3),
                    Category("oldCat3","2021-08-29", 4),
                    Category("newCat1","2020-03-09", 2),
                )
            ) })

    @Test
    @DisplayName("MergeCategoryRepositores returns a CategoryRepository instance.")
    fun `MergeCategoryRepositories returns a CategoryRepository instance`() {
        assertInstanceOf(CategoryRepository::class.java, MergeCategoryRepositories(oldRepo, newRepo))
    }


    @Test
    @DisplayName("Merge Overwrites/Appends to oldRepo with categories from newRepo.")
    fun `Merge Overwrites Appends to oldRepo with categories from newRepo`() {
        assertAll(
            { assertTrue(MergeCategoryRepositories(oldRepo,newRepo).getCategories().toList().containsAll(newRepo.getCategories().toList())) },
            { assertTrue(MergeCategoryRepositories(oldRepo,newRepo).getCategories().contains(Category("oldCat1","2020-08-29", 3))) }

        )
    }

}
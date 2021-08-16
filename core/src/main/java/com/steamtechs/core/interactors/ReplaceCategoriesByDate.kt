package com.steamtechs.core.interactors

import com.steamtechs.core.data.CategoryRepository

object ReplaceCategoriesByDate {

    operator fun invoke(sourceDate : String, sourceCategoryRepository: CategoryRepository, targetCategoryRepository: CategoryRepository){
        val tempCategoryList  = targetCategoryRepository.getCategories().filter { it.date != sourceDate }
        ensureSourceNotEmpty(sourceCategoryRepository)
        ensureAllSameDate(sourceDate,sourceCategoryRepository)
        targetCategoryRepository.addCategories(sourceCategoryRepository.getCategories() + tempCategoryList)
    }

    fun ensureSourceNotEmpty(sourceCategoryRepository: CategoryRepository){
        if (sourceCategoryRepository.getCategories().none()){
            throw IllegalArgumentException("sourceCategoryRepository is Empty.")
        }
    }


    fun ensureAllSameDate(sourceDate: String, sourceCategoryRepository: CategoryRepository) {
        for (category in sourceCategoryRepository.getCategories()) {
            if (category.date != sourceDate) {
                throw IllegalArgumentException("Not all Dates Match in sourceCategoryRepository.")
            }
        }
    }

}


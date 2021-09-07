package com.steamtechs.core.domain.businesslogic

import com.steamtechs.core.data.CategoryRepository

object MergeCategoryRepositories {
    operator fun invoke(sourceRepository: CategoryRepository, targetRepository: CategoryRepository){
        val oldMap = sourceRepository.getCategories().associateBy { Pair(it.title,it.date) }
        val newMap = targetRepository.getCategories().associateBy { Pair(it.title,it.date) }
        sourceRepository.addCategories((oldMap + newMap).values)
    }

}
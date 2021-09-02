package com.steamtechs.core.interactors

import com.steamtechs.core.data.CategoryRepository
import com.steamtechs.core.domain.Category

object UpdateTargetRepositoryWithSourceRepository {
    operator fun invoke(targetRepository: CategoryRepository, sourceRepository: CategoryRepository){
        val targetTitleDatePairsMap = targetRepository.getCategories().associateBy { Pair(it.title, it.date) }
        val sourceTitleDatePairsMap = sourceRepository.getCategories().associateBy { Pair(it.title, it.date) }
        val tempCategoryList = mutableListOf<Category>()
        for (tdPair in targetTitleDatePairsMap.keys){
            sourceTitleDatePairsMap[tdPair]?.let { tempCategoryList.add(it) } ?: continue
        }
        targetRepository.addCategories(tempCategoryList)

    }
}
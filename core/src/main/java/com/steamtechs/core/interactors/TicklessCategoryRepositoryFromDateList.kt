package com.steamtechs.core.interactors

import com.steamtechs.core.data.CategoryRepository
import com.steamtechs.core.domain.Category

object TicklessCategoryRepositoryFromDateList {

    operator fun invoke(dateList: List<String>, sourceRepository : CategoryRepository, targetRepository: CategoryRepository){
        val sourceTitleMap = sourceRepository.getCategories().groupBy { it.title }
        val sourceCategoryList = mutableListOf<Category>()
        for (date in dateList){
            for (key in sourceTitleMap.keys){
                sourceCategoryList.add(Category(key, date, 0))
            }
        }
        targetRepository.addCategories(sourceCategoryList)
    }
}
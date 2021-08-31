package com.steamtechs.core.domain.businesslogic

import com.steamtechs.core.data.CategoryRepository

object MergeCategoryRepositories {
    operator fun invoke(oldRepo: CategoryRepository, newRepo: CategoryRepository) : CategoryRepository {
        val oldMap = oldRepo.getCategories().associateBy { Pair(it.title,it.date) }
        val newMap = newRepo.getCategories().associateBy { Pair(it.title,it.date) }
        oldRepo.addCategories((oldMap + newMap).values)
        return oldRepo
    }

}
package com.steamtechs.core.interactors

import com.steamtechs.core.domain.Category

object SummarizeCategories {
    operator fun invoke(dateCategoryIterable: Iterable<Category>): Map<String, Int> =
        dateCategoryIterable.groupBy({ it.title }, { it.tickValue }).mapValues { (_, v: List<Int>) -> v.sum() }
}
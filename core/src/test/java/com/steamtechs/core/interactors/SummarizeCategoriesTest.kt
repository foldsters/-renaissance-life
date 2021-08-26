package com.steamtechs.core.interactors

import com.steamtechs.core.domain.Category
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class SummarizeCategoriesTest {

    lateinit var categoryIterable : Iterable<Category>
    lateinit var expectedSummary : Map<String, Int>

    @BeforeEach
    fun setup() {
        categoryIterable = listOf(
            Category(_title = "test1", _date = "2021-08-24", _tickValue = 1),
            Category(_title = "test2", _date = "2021-08-24", _tickValue = 1),
            Category(_title = "test3", _date = "2021-08-24", _tickValue = 0),

            Category(_title = "test1", _date = "2021-08-25", _tickValue = 1),
            Category(_title = "test2", _date = "2021-08-25", _tickValue = 2),
            Category(_title = "test3", _date = "2021-08-25", _tickValue = 0),
            Category(_title = "test4", _date = "2021-08-25", _tickValue = 4),

            Category(_title = "test1", _date = "2021-08-26", _tickValue = 1),
            Category(_title = "test2", _date = "2021-08-26", _tickValue = 3),
            Category(_title = "test3", _date = "2021-08-26", _tickValue = 0)
        )

        expectedSummary = mapOf("test1" to 3, "test2" to 6, "test3" to 0, "test4" to 4)
    }

    @Test
    @DisplayName("Summarize Category Information.")
    fun `Summarize Category Information`() {
        val summary = SummarizeCategories(categoryIterable)
        assertEquals(expectedSummary, summary)
    }


}
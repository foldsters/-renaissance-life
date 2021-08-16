package com.steamtechs.core.domain

import com.steamtechs.core.data.DayCategoryLog
import com.steamtechs.renaissancelife.platform.datasources.PDayCategoryLog
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class InitDayCatLogTest {

    // SETUP

    lateinit var sourceDayCategoryLog: DayCategoryLog
    lateinit var targetDayCategoryLog: DayCategoryLog

    @BeforeEach
    @DisplayName("Given a DayCatLog, ")
    fun `Given a DayCatLog`(){
        sourceDayCategoryLog = DayCategoryLog(PDayCategoryLog())
        targetDayCategoryLog = DayCategoryLog(PDayCategoryLog())
    }


    // TESTS

    @Test
    @DisplayName("Return Local DayCategoryLog.")
    fun `Return copied DayCategoryLog`() {
        assertInstanceOf(DayCategoryLog::class.java, InitDayCategoryLog(sourceDayCategoryLog, targetDayCategoryLog))
    }

    @Test
    @DisplayName("Show returned Instance is a Copy of given Instance.")
    fun `Show returned Instance is a Copy of given Instance`() {
        val categoryList = listOf<Category>(Category("Cat1"), Category("Cat2"))
        sourceDayCategoryLog.addCategories(categoryList)
        InitDayCategoryLog(sourceDayCategoryLog, targetDayCategoryLog)
        assertEquals(categoryList, targetDayCategoryLog.getCategories())
    }
}
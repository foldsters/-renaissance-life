package com.steamtechs.core.domain

import com.steamtechs.core.data.DayCategoryLog
import com.steamtechs.core.data.platform.PDayCategoryLog
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class InitDayCatLogTest{

    lateinit var dayCategoryLog: DayCategoryLog
    private val pDayCategoryLog = PDayCategoryLog()

    @BeforeEach
    @DisplayName("Given a DayCatLog, ")
    fun `Given a DayCatLog`(){
        dayCategoryLog = DayCategoryLog(pDayCategoryLog)
    }

    @Test
    @DisplayName("Return Local DayCategoryLog.")
    fun `Return copied DayCategoryLog`() {
        assertInstanceOf(DayCategoryLog::class.java, InitDayCategoryLog(dayCategoryLog))
    }

    @Test
    @DisplayName("Show returned Instance is a Copy of given Instance.")
    fun `Show returned Instance is a Copy of given Instance`() {
        val categoryList = listOf<Category>(Category("Cat1"), Category("Cat2"))
        dayCategoryLog.addCategories(categoryList)
        assertEquals(categoryList,InitDayCategoryLog(dayCategoryLog).getCategories())
    }

}
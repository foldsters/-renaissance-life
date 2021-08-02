package com.steamtechs.core.data


import com.steamtechs.core.domain.Category
import com.steamtechs.renaissancelife.platform.datasources.PDayCatLog
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class DayCatLogTest {

    @Nested
    @DisplayName("Given instance of DayCatLogDataSource, ")
    inner class GivenDayCatLogDataSource{
        lateinit var testDayCatLog: DayCatLog

        var testDCDS : DayCatLogDataSource = PDayCatLog()
        val testCat1 = Category("Test1")
        val testCat2 = Category("Test2")
        val testCat3 = Category("Test3")

        @BeforeEach
        fun `Given instance of DayCatLogDataSource`(){
            testDayCatLog = DayCatLog(testDCDS)

            testCat1.tickValue = 13
            testCat2.tickValue = 8
            testCat3.tickValue = 0
        }

        @Test
        @DisplayName("create instance of DayCatLog.")
        fun `create instance of DayCatLog`() {
            assertInstanceOf(DayCatLog::class.java, testDayCatLog)
        }

        @Test
        @DisplayName("getCategories returns Iterable of Catetory.")
        fun `getCategories returns Iterable of Catetory`() {
            assertInstanceOf(Iterable::class.java, testDayCatLog.getCategories())
        }

        @Test
        @DisplayName("addCategory works.")
        fun `addCategory works`() {
            testDayCatLog.addCategory(testCat1)
            assert(testDayCatLog.getCategories().contains(testCat1))
        }

        @Test
        @DisplayName("deleteCategory works.")
        fun `deleteCategory works`() {
            testDayCatLog.addCategory(testCat1)
            testDayCatLog.addCategory(testCat3)
            testDayCatLog.deleteCategory(testCat1)
            assertAll(
                { assertFalse(testDayCatLog.getCategories().contains(testCat1))},
                { assert(testDayCatLog.getCategories().contains(testCat3))}
            )
        }


    }
}
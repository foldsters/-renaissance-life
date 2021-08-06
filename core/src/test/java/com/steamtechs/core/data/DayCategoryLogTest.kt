package com.steamtechs.core.data


import com.steamtechs.core.domain.Category
import com.steamtechs.renaissancelife.platform.datasources.PDayCategoryLog
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class DayCategoryLogTest {

    @Nested
    @DisplayName("Given instance of DayCatLogDataSource, ")
    inner class GivenDayCategoryLogDataSource{
        lateinit var testDayCategoryLog: DayCategoryLog

        var testDCDS : DayCategoryLogDataSource = PDayCategoryLog()
        val testCat1 = Category("Test1")
        val testCat2 = Category("Test2")
        val testCat3 = Category("Test3")

        @BeforeEach
        fun `Given instance of DayCatLogDataSource`(){
            testDayCategoryLog = DayCategoryLog(testDCDS)

            testCat1.tickValue = 13
            testCat2.tickValue = 8
            testCat3.tickValue = 0
        }

        @Test
        @DisplayName("create instance of DayCatLog.")
        fun `create instance of DayCatLog`() {
            assertInstanceOf(DayCategoryLog::class.java, testDayCategoryLog)
        }

        @Test
        @DisplayName("getCategories returns Iterable of Catetory.")
        fun `getCategories returns Iterable of Catetory`() {
            assertInstanceOf(Iterable::class.java, testDayCategoryLog.getCategories())
        }

        @Test
        @DisplayName("addCategory works.")
        fun `addCategory works`() {
            testDayCategoryLog.addCategory(testCat1)
            assert(testDayCategoryLog.getCategories().contains(testCat1))
        }

        @Test
        @DisplayName("deleteCategory works.")
        fun `deleteCategory works`() {
            testDayCategoryLog.addCategory(testCat1)
            testDayCategoryLog.addCategory(testCat3)
            testDayCategoryLog.deleteCategory(testCat1)
            assertAll(
                { assertFalse(testDayCategoryLog.getCategories().contains(testCat1))},
                { assert(testDayCategoryLog.getCategories().contains(testCat3))}
            )
        }


    }
}
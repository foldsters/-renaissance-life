package com.steamtechs.renaissancelife.framework.datasources

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.*

internal class FRoomCategoryDataSourceTest {

    @Test
    @DisplayName("Create Instance of FRoomCategoryDataSource.")
    fun `Create Instance of FRoomCategoryDataSource`() {
        assertInstanceOf(FRoomCategoryDataSource::class.java, FRoomCategoryDataSource())
    }
}
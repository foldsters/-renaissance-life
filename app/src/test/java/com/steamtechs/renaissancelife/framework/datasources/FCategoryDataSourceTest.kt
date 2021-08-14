package com.steamtechs.renaissancelife.framework.datasources

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class FCategoryDataSourceTest{

    @Test
    @DisplayName("Create Instance of FCategoryDataSource.")
    fun `Create Instance of FCategoryDataSource`() {
        assertInstanceOf(FCategoryDataSource::class.java, FCategoryDataSource())
    }
}
package com.steamtechs.renaissancelife.framework.db

import androidx.test.platform.app.InstrumentationRegistry
import com.steamtechs.core.domain.Category
import com.steamtechs.renaissancelife.framework.datasources.RoomCategoryDataSource
import org.junit.jupiter.api.DisplayName
import org.junit.Test
import org.junit.jupiter.api.Assertions.*


internal class AppRoomDatabaseTest {

    // SETUP

    private lateinit var database: AppRoomDatabase

    // Breaks dependency rule, needed to test clearAllTablesOfDatabase
    private lateinit var roomDataSource : RoomCategoryDataSource

    fun setupDatabase() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext!!
        database = AppRoomDatabase.getInstance(appContext)
    }

    fun setupRoomDataSource() {
        roomDataSource = RoomCategoryDataSource(database.categoryDao())
    }


    // TESTS

    // Constructor/Instantiation Tests
    @Test
    @DisplayName("Verify instance of Database.")
    fun createInstanceOfDatabase() {
        setupDatabase()

        assertInstanceOf(AppRoomDatabase::class.java, database)
    }

    @Test
    @DisplayName("Get Category Dao Instance.")
    fun getCategoryDaoInstance() {
        setupDatabase()

        val categoryDao = database.categoryDao()

        assertInstanceOf(CategoryDao::class.java, categoryDao)
    }


    // Clear Tables Tests
    @Test
    @DisplayName("Clear All Tables of Database.")
    fun clearAllTablesOfDatabase() {
        setupDatabase()
        setupRoomDataSource()

        roomDataSource.addCategories(listOf(Category("TestCategory")))
        database.clearAllTables()

        assertTrue(database.categoryDao().getAllCategoryEntities().isEmpty())
    }

}
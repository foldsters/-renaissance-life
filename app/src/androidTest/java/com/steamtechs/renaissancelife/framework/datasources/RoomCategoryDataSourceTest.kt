package com.steamtechs.renaissancelife.framework.datasources

import androidx.test.platform.app.InstrumentationRegistry
import com.steamtechs.core.domain.Category
import com.steamtechs.renaissancelife.framework.db.AppRoomDatabase
import org.junit.jupiter.api.DisplayName
import org.junit.Test
import org.junit.jupiter.api.Assertions.*


class RoomCategoryDataSourceTest {

    // RCDS stands for RoomCategoryDataSource

    // SETUP

    lateinit var roomCategoryDataSource : RoomCategoryDataSource

    fun setupRoomCategoryDataSource() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val dao = AppRoomDatabase.getInstance(appContext).categoryDao()
        roomCategoryDataSource = RoomCategoryDataSource(dao)
    }


    // TESTS

    // Constructor Tests
    @Test
    @DisplayName("Verify Instance of RCDS")
    fun createInstanceOfRCDS() {
        setupRoomCategoryDataSource()

        assertInstanceOf(RoomCategoryDataSource::class.java, roomCategoryDataSource)
    }


    // Getter Tests
    @Test
    @DisplayName("Get Categories from RCDS instance.")
    fun getCategoriesFromRcdsInstance() {
        setupRoomCategoryDataSource()

        val categoriesFromDatabase = roomCategoryDataSource.getCategories()

        assertEquals(0, categoriesFromDatabase.count())
    }


    // Clear Categories Tests
    @Test
    @DisplayName("Clear All Categories from RCDS.")
    fun clearAllCategoriesFromRcds() {
        setupRoomCategoryDataSource()

        roomCategoryDataSource.clearAllCategories()
        val categoriesFromDatabase = roomCategoryDataSource.getCategories()

        assertEquals(0, categoriesFromDatabase.count())
    }


    // Add Categories Tests
    @Test
    @DisplayName("Add Categories to RCDS instance.")
    fun addCategoriesToRcdsInstance() {
        setupRoomCategoryDataSource()

        val categories = listOf<Category>(Category("Test1"), Category("Test2"))
        roomCategoryDataSource.addCategories(categories)

        assertEquals(2, roomCategoryDataSource.getCategories().count())
    }

    // Iterator Tests
    @Test
    @DisplayName("Show Iterator Method.")
    fun showIteratorMethod() {
        setupRoomCategoryDataSource()

        val dataSourceIterator = roomCategoryDataSource.iterator()

        assertInstanceOf(Iterator::class.java, dataSourceIterator)
    }


}
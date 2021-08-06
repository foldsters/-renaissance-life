package com.steamtechs.renaissancelife.framework.db

import androidx.test.platform.app.InstrumentationRegistry
import com.steamtechs.core.domain.Category
import com.steamtechs.renaissancelife.framework.datasources.RoomCategoryDataSource
import org.junit.jupiter.api.DisplayName
import org.junit.Test
import org.junit.jupiter.api.Assertions.*


internal class AppRoomDatabaseTest {

    var appContext = InstrumentationRegistry.getInstrumentation().targetContext!!

    @Test
    @DisplayName("Create instance of Database.")
    fun createInstanceOfDatabase() {
        val db = AppRoomDatabase.getInstance(appContext)
        assertInstanceOf(AppRoomDatabase::class.java, db)
    }

    @Test
    @DisplayName("Get Category Dao Instance.")
    fun getCategoryDaoInstance() {
        val db = AppRoomDatabase.getInstance(appContext)
        //val categoryDao2 = CategoryDao_Impl(AppRoomDatabase_Impl())
        val categoryDao = db.categoryDao()
        assertInstanceOf(CategoryDao::class.java, categoryDao)
    }

    @Test
    @DisplayName("Clear All Tables of Database.")
    fun clearAllTablesOfDatabase() {
        val db = AppRoomDatabase.getInstance(appContext)
        val categoryDataSourceRoom : RoomCategoryDataSource = RoomCategoryDataSource(appContext)
        categoryDataSourceRoom.addCategory(Category("TestCategory"))
        db.clearAllTables()
        assertTrue(db.categoryDao().getAllCategories().isEmpty())
    }

}
package com.steamtechs.renaissancelife.framework.db

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested

internal class CategoryDatabaseTest {

    var appContext = InstrumentationRegistry.getInstrumentation().targetContext!!

    fun beforeEach() : CategoryDao {
        val db = CategoryDatabase.getInstance(appContext)
        db.clearAllTables()
        return db.categoryDao()
    }

    @Test
    fun getAppContext() {
        assertEquals("com.steamtechs.renaissancelife", appContext.packageName)
    }

    @Test
    @DisplayName("Create Instance of Database")
    fun createInstanceOfDatabase() {
        val db = CategoryDatabase.getInstance(appContext)
        assertInstanceOf(CategoryDatabase::class.java, db)
    }

    @Test
    @DisplayName("\nEmpty database returns empty category list")
    fun emptyDatabaseReturnsEmptyCategoryList() {
        val dao = beforeEach()
        val allCatsInDao = dao.getAll()
        assertTrue(allCatsInDao.isEmpty())
    }

    @Test
    @DisplayName("Category inserted is equal matches category retrieved")
    fun categoryInsertedIsEqualMatchesCategoryRetrieved() {
        val dao = beforeEach()
        val testCat1 = CategoryEntity("2021-08-04", "Test1", 5)
        dao.insertAll(testCat1)
        val allCatsInDao = dao.getAll()

        assertTrue((allCatsInDao.size == 1) && (testCat1 in allCatsInDao))
    }
    
    @Test
    @DisplayName("Fetch categories at a specified date")
    fun fetchCategoriesAtASpecifiedDate() {
        val dao = beforeEach()
        val testCat1 = CategoryEntity("2021-08-04", "Today", 1)
        val testCat2 = CategoryEntity("2021-08-04", "Also Today", 2)
        val testCat3 = CategoryEntity("2021-08-03", "Yesterday", 3)
        dao.insertAll(testCat1, testCat2, testCat3)

        val todayCats = dao.getAtDate("2021-08-04")

        assertTrue((testCat1 in todayCats) && (testCat2 in todayCats) && (testCat3 !in todayCats))
    }



    /*
    @Query("SELECT * FROM category")
    fun getAll(): List<CategoryEntity>

    @Query("SELECT * FROM category WHERE date BETWEEN :startDate AND :endDate")
    fun getBetweenDates(startDate : String, endDate : String): List<CategoryEntity>

    @Query("SELECT * FROM category WHERE date == :date")
    fun getAtDate(date : String): List<CategoryEntity>

    @Insert
    fun insertAll(vararg categories: CategoryEntity)

    @Delete
    fun deleteCategory(category: CategoryEntity)

    @Query("DELETE FROM category")
    fun deleteAll()

    @Query("DELETE FROM category WHERE date == :date")
    fun deleteAtDate(date: String)
     */


}
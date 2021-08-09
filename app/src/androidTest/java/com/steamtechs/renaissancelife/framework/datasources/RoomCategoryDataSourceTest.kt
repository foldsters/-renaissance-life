package com.steamtechs.renaissancelife.framework.datasources

import androidx.test.platform.app.InstrumentationRegistry
import com.steamtechs.core.domain.Category
import com.steamtechs.renaissancelife.framework.datasources.RoomCategoryDataSource
import org.junit.jupiter.api.DisplayName
import org.junit.Test
import org.junit.jupiter.api.Assertions.*


internal class RoomCategoryDataSourceTest {

    // RCDS stands for RoomCategoryDataSource

    var appContext = InstrumentationRegistry.getInstrumentation().targetContext
    
    @Test
    @DisplayName("Create Instance of RCDS")
    fun createInstanceOfRCDS() {
        val rcds = RoomCategoryDataSource(appContext)
        assertInstanceOf(RoomCategoryDataSource::class.java, rcds)
    }



    @Test
    @DisplayName("Get Categories from RCDS instance.")
    fun getCategoriesFromRcdsInstance() {
        val rcds = RoomCategoryDataSource(appContext)
        val categoriesFromDatabase = rcds.getCategories()
        println(categoriesFromDatabase)
        assertEquals(0, categoriesFromDatabase.count())
    }

    @Test
    @DisplayName("Clear All Categories from RCDS.")
    fun clearAllCategoriesFromRcds() {
        val rcds = RoomCategoryDataSource(appContext)
        rcds.clearAllCategories()
        val categoriesFromDatabase = rcds.getCategories()
        assertEquals(0, categoriesFromDatabase.count())
    }


    @Test
    @DisplayName("Add Categories to RCDS instance.")
    fun addCategoriesToRcdsInstance() {
        val rcds = RoomCategoryDataSource(appContext)
        val categories = listOf<Category>(Category("Test1"), Category("Test2"))
        rcds.addCategories(categories)
        assertEquals(2, rcds.getCategories().count())
    }

    @Test
    @DisplayName("Show Iterator Method.")
    fun showIteratorMethod() {
        val rcds = RoomCategoryDataSource(appContext)
        val ircds = rcds.iterator()
        assertInstanceOf(Iterator::class.java,ircds)
    }


}
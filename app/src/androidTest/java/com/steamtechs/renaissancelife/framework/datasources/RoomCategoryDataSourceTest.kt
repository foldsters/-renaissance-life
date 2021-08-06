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



//    @Test
//    @DisplayName("Get Categories from RCDS instance.")
//    fun getCategoriesFromRcdsInstance() {
//        val rcds = RoomCategoryDataSource(appContext)
//        val categoriesFromDatabase = rcds.getCategories()
//        println("******")
//        println(categoriesFromDatabase.toString())
//        assertEquals(0, categoriesFromDatabase.count())
//    }

//    @Test
//    @DisplayName("Add Category to RCDS instance.")
//    fun addCategoryToRcdsInstance() {
//        val rcds = RoomCategoryDataSource(appContext)
//        val category1 = Category("Test1")
//        rcds.addCategory()
//    }


}
package com.steamtechs.renaissancelife.framework.datasources

import androidx.annotation.FloatRange
import com.steamtechs.renaissancelife.framework.db.CategoryEntity
import org.junit.Before
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.*
import org.junit.Test
import org.junit.experimental.runners.Enclosed
import org.junit.runner.RunWith


@RunWith(Enclosed::class)
internal class FRoomCategoryDataSourceTest {

    class CreateInstanceOfFRoomCategoryDataSource{
        @Test
        @DisplayName("Create Instance of FRoomCategoryDataSource.")
        fun `Create Instance of FRoomCategoryDataSource`() {
            assertInstanceOf(FRoomCategoryDataSource::class.java, FRoomCategoryDataSource())
        }
    }

    class GivenInstanceOfFRoomCategoryDataSource {

        private lateinit var fRoomCategoryDataSource: FRoomCategoryDataSource
        private val fRoomCategoryList = listOf(
            CategoryEntity(0, "1999-12-31", "Ye Ol' Category", 347),
            CategoryEntity(0, "2021-01-13", "Backpacking", 2),
            CategoryEntity(0, "2021-01-27", "Backpacking", 3),
            CategoryEntity(0, "2021-03-02", "Backpacking", 2),


        )

        @Before
        @DisplayName("Given Instance of FRoomCategoryDataSource, ")
        fun `Given Instance of FRoomCategoryDataSource,`(){
            fRoomCategoryDataSource = FRoomCategoryDataSource().also { it.addCategoryEntities(fRoomCategoryList) }
        }

        @Test
        @DisplayName("getAllCategoryEntities returns a List of CategoryEntities")
        fun getAllCategoryEntitiesReturnsAListOfCategoryEntities() {
            val retrievedCategoryList = fRoomCategoryDataSource.getAllCategoryEntities()
            assertAll(
                { assertInstanceOf(List::class.java, retrievedCategoryList)},
                { assertInstanceOf(CategoryEntity::class.java, retrievedCategoryList.iterator().next())}
            )
        }

    }

}
package com.steamtechs.renaissancelife.framework.db

import com.steamtechs.core.domain.Category
//import org.junit.Assert.*
import org.junit.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName

class CategoryEntityTest {

    @Test
    @DisplayName("Create Instance of Category Entity using Default Literal Constructor")
    fun createInstanceOfCategoryEntityUsingDefaultLiteralConstructor() {
        val ce = CategoryEntity(
            id = 0,
            date = "2021-08-04",
            title = "Test1",
            tickValue = 5)
        assertInstanceOf(CategoryEntity::class.java, ce)
    }

    @Test
    @DisplayName("Create instance with fromCategory")
    fun createInstanceWithSecondaryConstructor() {
        val ce = CategoryEntity.fromCategory(givenCategoryInstance1())
        assertInstanceOf(CategoryEntity::class.java, ce)
    }

    @Test
    @DisplayName("Get id, date, title, tickvalue from instance")
    fun getIdDateTitleTickvalueFromInstance() {
        val ce : CategoryEntity =givenCategoryEntityInstance1()
        assertAll(
            { assertEquals(0, ce.id)},
            { assertEquals("", ce.date)},
            { assertEquals("Cat1", ce.title)},
            { assertEquals(43, ce.tickValue)})
    }

    @Test
    @DisplayName("Create Category with toCategory")
    fun createCategoryWithToCategory() {
        val ce = givenCategoryEntityInstance1()
        val realCategory = ce.toCategory()
        assertInstanceOf(Category::class.java, realCategory)
    }

    @Test
    @DisplayName("Check Equality that ignores id")
    fun checkEqualityThatIgnoresId() {
        val ce = givenCategoryEntityInstance1()
        val equlce = CategoryEntity(42,ce.date,ce.title, ce.tickValue)
        assertEquals(ce,equlce)
    }

    @Test
    @DisplayName("Generate Hash which neglects id")
    fun generateHashWhichNeglectsId() {
        val ce = givenCategoryEntityInstance1()
        val equlce = CategoryEntity(42,ce.date,ce.title, ce.tickValue)
        assertEquals(ce.hashCode(),equlce.hashCode())
    }

    @Test
    @DisplayName("Two instances with Same Non-id information are Equal")
    fun twoInstancesWithSameNonIdInformationAreEqual() {
        val ce1 : CategoryEntity = givenCategoryEntityInstance1()
        val ce2 : CategoryEntity = CategoryEntity(843, ce1.date, ce1.title, ce1.tickValue)
        assertEquals(ce1, ce2)
    }

    @Test
    @DisplayName("Neglecting id, Dissimilar Instances Equals returns False")
    fun neglectingIdDissimilarInstancesEqualsReturnsFalse() {
        val ce1: CategoryEntity = givenCategoryEntityInstance1()
        val ce2: CategoryEntity = givenCategoryEntityInstance2()
        assertNotEquals(ce1, ce2)
    }

    private fun givenCategoryInstance1() : Category = Category("Cat1").apply { tickValue = 43 }
    private fun givenCategoryInstance2() : Category = Category("Cat2").apply { tickValue = 92 }

    private fun givenCategoryEntityInstance1() : CategoryEntity =
        CategoryEntity.fromCategory(givenCategoryInstance1())

    private fun givenCategoryEntityInstance2() : CategoryEntity =
        CategoryEntity.fromCategory(givenCategoryInstance2())

}
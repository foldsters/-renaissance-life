package com.steamtechs.renaissancelife.framework.db

import com.steamtechs.core.domain.Category
import org.junit.Before
import org.junit.jupiter.api.Assertions.*
import org.junit.Test
import org.junit.jupiter.api.DisplayName

class CategoryEntityTest {

    // SETUP

    lateinit var category1 : Category
    lateinit var category2 : Category

    lateinit var categoryEntityFromPrimaryConstructor : CategoryEntity
    lateinit var categoryEntityFromSecondaryConstructor1 : CategoryEntity
    lateinit var categoryEntityFromSecondaryConstructor2 : CategoryEntity

    @Before
    fun setupCategories() {
        category1 = Category("Cat1").apply { tickValue = 43 }
        category2 = Category("Cat2").apply { tickValue = 92 }
    }

    fun setupCategoryEntityFromPrimaryConstructor() {
        categoryEntityFromPrimaryConstructor = CategoryEntity(
            id = 0,
            date = "2021-08-04",
            title = "Test1",
            tickValue = 5
        )
    }

    fun setupCategoryEntitiesFromSecondaryConstructors() {
        categoryEntityFromSecondaryConstructor1 = CategoryEntity.fromCategory(category1)
        categoryEntityFromSecondaryConstructor2 = CategoryEntity.fromCategory(category2)
    }


    // TESTS

    // Constructor Tests
    @Test
    @DisplayName("Create Instance of Category Entity using Default Literal Constructor")
    fun createInstanceOfCategoryEntityUsingDefaultLiteralConstructor() {
        setupCategoryEntityFromPrimaryConstructor()

        assertInstanceOf(CategoryEntity::class.java, categoryEntityFromPrimaryConstructor)
    }

    @Test
    @DisplayName("Create instance with fromCategory")
    fun createInstanceWithSecondaryConstructor() {
        setupCategoryEntitiesFromSecondaryConstructors()

        assertInstanceOf(CategoryEntity::class.java, categoryEntityFromSecondaryConstructor1)
    }


    // Getter Tests
    @Test
    @DisplayName("Get id, date, title, tickValue from instance")
    fun getIdDateTitleTickValueFromInstance() {
        setupCategoryEntitiesFromSecondaryConstructors()

        assertAll(
            { assertEquals(0, categoryEntityFromSecondaryConstructor1.id)},
            { assertEquals("", categoryEntityFromSecondaryConstructor1.date)},
            { assertEquals("Cat1", categoryEntityFromSecondaryConstructor1.title)},
            { assertEquals(43, categoryEntityFromSecondaryConstructor1.tickValue)}
        )
    }


    // Core Category Instance Creation Tests
    @Test
    @DisplayName("Create Category with toCategory")
    fun createCategoryWithToCategory() {
        setupCategoryEntitiesFromSecondaryConstructors()

        val realCategory = categoryEntityFromSecondaryConstructor1.toCategory()

        assertInstanceOf(Category::class.java, realCategory)
    }


    // Equality Related Tests
    @Test
    @DisplayName("Check Equality that ignores id")
    fun checkEqualityThatIgnoresId() {
        setupCategoryEntitiesFromSecondaryConstructors()

        val equivalentCategoryEntity = CategoryEntity(
            42,
            categoryEntityFromSecondaryConstructor1.date,
            categoryEntityFromSecondaryConstructor1.title,
            categoryEntityFromSecondaryConstructor1.tickValue)

        assertEquals(categoryEntityFromSecondaryConstructor1, equivalentCategoryEntity)
    }

    @Test
    @DisplayName("Generate Hash which neglects id")
    fun generateHashWhichNeglectsId() {
        setupCategoryEntitiesFromSecondaryConstructors()

        val equivalentCategoryEntity = CategoryEntity(
            42,
            categoryEntityFromSecondaryConstructor1.date,
            categoryEntityFromSecondaryConstructor1.title,
            categoryEntityFromSecondaryConstructor1.tickValue)

        assertEquals(categoryEntityFromSecondaryConstructor1.hashCode(),
            equivalentCategoryEntity.hashCode())
    }

    @Test
    @DisplayName("Neglecting id, Dissimilar Instances Equals returns False")
    fun neglectingIdDissimilarInstancesEqualsReturnsFalse() {
        setupCategoryEntitiesFromSecondaryConstructors()

        assertNotEquals(categoryEntityFromSecondaryConstructor1,
                        categoryEntityFromSecondaryConstructor2)
    }
}
package com.steamtechs.renaissancelife.framework.db

import androidx.room.*

@Dao
interface CategoryDao {
    @Query("SELECT * FROM category")
    fun getAll(): List<CategoryEntity>

    @Query("SELECT * FROM category WHERE date BETWEEN :startDate AND :endDate")
    fun getCategoriesBetweenDates(startDate : String, endDate : String): List<CategoryEntity>

    @Query("SELECT * FROM category WHERE date == :date")
    fun getCategoriesAtDate(date : String): List<CategoryEntity>

    @Insert
    fun insertAllCategories(vararg categories: CategoryEntity)

    @Delete
    fun deleteCategory(category: CategoryEntity)

    @Query("DELETE FROM category")
    fun deleteAll()

    @Query("DELETE FROM category WHERE date == :date")
    fun deleteCategoriesAtDate(date: String)

}
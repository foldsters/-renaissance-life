package com.steamtechs.renaissancelife.framework.db

import androidx.room.*

@Dao
interface CategoryDao {
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

}
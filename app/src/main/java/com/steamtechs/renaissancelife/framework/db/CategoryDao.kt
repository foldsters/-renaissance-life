package com.steamtechs.renaissancelife.framework.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CategoryDao {

    @Query("SELECT * FROM categoryTable")
    fun getAllCategories() : List<CategoryEntity>

    @Query("DELETE FROM categoryTable")
    fun clearAllCategories()

    @Insert
    fun addCategories(categories : List<CategoryEntity>)

}
package com.steamtechs.renaissancelife.framework.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CategoryDao {

    @Query("SELECT * FROM categoryTable")
    fun getAllCategoryEntities() : List<CategoryEntity>

    @Query("DELETE FROM categoryTable")
    fun clearAllCategoryEntities()

    @Insert
    fun addCategoryEntities(categoryEntities : Iterable<CategoryEntity>)

}
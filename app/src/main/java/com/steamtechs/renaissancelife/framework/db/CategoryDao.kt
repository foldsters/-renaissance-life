package com.steamtechs.renaissancelife.framework.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CategoryDao {

    @Insert
    fun addCategory(category : CategoryEntity)

    @Query("SELECT * FROM categoryTable")
    fun getAllCategories() : List<CategoryEntity>

    @Delete
    fun removeCategory(category : CategoryEntity)




}
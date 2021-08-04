package com.steamtechs.renaissancelife.framework.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CategoryEntity::class], version = 1)
abstract class CategoryDatabase : RoomDatabase() {

    // Inflates a singleton instance
    companion object {

        private const val DATABASE_NAME = "category.db"

        private var instance: CategoryDatabase? = null

        private fun create(context: Context): CategoryDatabase =
            Room.databaseBuilder(context, CategoryDatabase::class.java, DATABASE_NAME)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()

        fun getInstance(context: Context): CategoryDatabase =
            (instance ?: create(context)).also { instance = it }
    }

    abstract fun categoryDao(): CategoryDao
}
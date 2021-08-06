package com.steamtechs.renaissancelife.framework.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities=[CategoryEntity::class], version=1)
abstract class AppRoomDatabase : RoomDatabase() {

    companion object {

        private const val DATABASE_FILENAME = "renaissanceliferoom.db"

        private var instance : AppRoomDatabase? = null

        private fun createInstance(context: Context) : AppRoomDatabase {
            return Room.databaseBuilder(context, AppRoomDatabase::class.java, DATABASE_FILENAME).build()
        }

        fun getInstance(context : Context) : AppRoomDatabase {
            return (instance ?: createInstance(context)).also { instance = it }
        }
    }

    abstract fun categoryDao() : CategoryDao
}
package com.steamtechs.renaissancelife.di

import android.content.Context
import androidx.room.Room
import com.steamtechs.renaissancelife.framework.db.AppRoomDatabase
import com.steamtechs.renaissancelife.framework.db.CategoryDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideAppRoomDatabase(@ApplicationContext context : Context) : AppRoomDatabase {
        return Room.databaseBuilder(
            context,
            AppRoomDatabase::class.java,
            "renaissanceliferoom.db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideCategoryDao(database : AppRoomDatabase) : CategoryDao {
        return database.categoryDao()
    }
}
package com.steamtechs.renaissancelife.di

import android.content.Context
import androidx.room.Room
import com.steamtechs.core.data.CategoryRepository
import com.steamtechs.core.interactors.InitLocalCategoryRepository
import com.steamtechs.platform.datasources.PCategoryRepository
import com.steamtechs.renaissancelife.framework.bluetooth.BluetoothHandler
import com.steamtechs.renaissancelife.framework.bluetooth.mocks.MockBluetoothClient
import com.steamtechs.renaissancelife.framework.bluetooth.mocks.MockBluetoothServerController
import com.steamtechs.renaissancelife.framework.bluetooth.real.RealBluetoothClient
import com.steamtechs.renaissancelife.framework.bluetooth.real.RealBluetoothServerController
import com.steamtechs.renaissancelife.framework.datasources.RoomCategoryDataSource
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
        ).allowMainThreadQueries().build()
    }

    @Singleton
    @Provides
    fun provideCategoryDao(database : AppRoomDatabase) : CategoryDao {
        return database.categoryDao()
    }

    @Singleton
    @Provides
    fun providesRoomCategoryDataSource(categoryDao: CategoryDao) : RoomCategoryDataSource {
        return RoomCategoryDataSource(categoryDao)
    }

    @Singleton
    @Provides
    fun providesInitDataSource(roomCategoryDataSource: RoomCategoryDataSource) : CategoryRepository {
        var targetCategoryRepository = CategoryRepository(PCategoryRepository())
        val sourceCategoryRepository = CategoryRepository(roomCategoryDataSource)
        targetCategoryRepository = InitLocalCategoryRepository(sourceCategoryRepository, targetCategoryRepository)
        return targetCategoryRepository
    }

    @MockBluetoothHandler
    @Singleton
    @Provides
    fun providesMockBluetoothHandler() : BluetoothHandler {
        return BluetoothHandler(::MockBluetoothServerController, ::MockBluetoothClient)
    }

    @RealBluetoothHandler
    @Singleton
    @Provides
    fun providesRealBluetoothHandler() : BluetoothHandler {
        return BluetoothHandler(::RealBluetoothServerController, ::RealBluetoothClient)
    }
}
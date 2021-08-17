package com.steamtechs.renaissancelife.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.steamtechs.core.data.CategoryRepository
import com.steamtechs.core.domain.Category
import com.steamtechs.core.interactors.GetCategoriesForDate
import com.steamtechs.core.interactors.ReplaceCategoriesByDate
import com.steamtechs.core.interactors.SaveToExternalCategoryRepository
import com.steamtechs.platform.datasources.PCategoryRepository
import com.steamtechs.renaissancelife.framework.datasources.RoomCategoryDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
    private val savedStateHandle : SavedStateHandle,
    val initCategoryRepository : CategoryRepository,
    val roomCategoryDataSource : RoomCategoryDataSource
) : ViewModel() {

    init {
        println("INIT CATEGORY REPOSITORY")
        println(initCategoryRepository.getCategories().toString())
    }


    var todayCategoryIterable = GetCategoriesForDate(initCategoryRepository, getTodayDateString())

    init {
        println("INIT CATEGORY REPOSITORY")
        println(getTodayDateString())
        println(todayCategoryIterable.toString())
    }

    var liveCategoryList : MutableLiveData<List<LiveCategory>> =
        MutableLiveData(todayCategoryIterable.map { LiveCategory.fromCategory(it) })


    private fun getTodayDateString() : String {
        val currentDateTime = LocalDateTime.now()
        return currentDateTime.format(DateTimeFormatter.ISO_DATE)
    }

    fun addNewLiveCategory() {
        val newLiveCategory = LiveCategory.defaultCategory()
        liveCategoryList.value = liveCategoryList.value?.plus(listOf(newLiveCategory))
    }

    fun saveLiveCategories() {

        val newTodayCategoryIterable : List<Category> = liveCategoryList.value!!.map {it.toCategory() }
        val newTodayCategoryRepository = CategoryRepository(PCategoryRepository().also {it.addCategories(newTodayCategoryIterable)} )
        val roomCategoryRepository = CategoryRepository(roomCategoryDataSource)

        ReplaceCategoriesByDate(getTodayDateString(), newTodayCategoryRepository, initCategoryRepository)
        SaveToExternalCategoryRepository(initCategoryRepository, roomCategoryRepository)

    }

    private var uniqueKey = 0

    private fun getUniqueLiveCategoryKey() : Int {
        return uniqueKey++
    }

}
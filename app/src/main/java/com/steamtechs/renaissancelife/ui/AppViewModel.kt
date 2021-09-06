package com.steamtechs.renaissancelife.ui


import androidx.lifecycle.*
import com.steamtechs.core.data.CategoryRepository
import com.steamtechs.core.domain.Category
import com.steamtechs.core.interactors.*
import com.steamtechs.platform.datasources.PCategoryRepository
import com.steamtechs.renaissancelife.R
import com.steamtechs.renaissancelife.di.MockBluetoothHandler
import com.steamtechs.renaissancelife.framework.bluetooth.BluetoothHandler
import com.steamtechs.renaissancelife.framework.datasources.RoomCategoryDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
    private val initCategoryRepository : CategoryRepository,
    private val roomCategoryDataSource : RoomCategoryDataSource,
    @MockBluetoothHandler private val mockBluetoothHandler : BluetoothHandler
) : ViewModel() {

    // Setup

    private var todayCategoryIterable = GetCategoriesForDate(initCategoryRepository, getTodayDateString())

    var liveCategoryList : MutableLiveData<List<LiveCategory>> =
        MutableLiveData(todayCategoryIterable.map { LiveCategory.fromCategory(it) })

    init {
        mockBluetoothHandler.messageReceiveCallback = { message : String, deviceAddress : String? ->
            println("$message -- $deviceAddress")
            mockBluetoothHandler.messageReceiveCallback = null
        }
        mockBluetoothHandler.startBluetoothServer()
        mockBluetoothHandler.onChangeMessage("Potato")
        mockBluetoothHandler.sendMessageToDevice(null)
    }


    // Navigation

    val navTitles = listOf("Logging", "Radar")
    val navIcons : List<Int> = listOf(R.drawable.ic_log, R.drawable.ic_outline_grade_24)

    val navSelected = MutableLiveData<String>()

    fun onNavSelect(navTitle : String) {
        navSelected.value = navTitle
    }


    // Logging

    fun addNewLiveCategory() {
        val newLiveCategory = LiveCategory.defaultCategory()
        liveCategoryList.value = liveCategoryList.value?.plus(listOf(newLiveCategory))

    }

    fun deleteLiveCategory(liveCategory : LiveCategory) {
        liveCategoryList.value = liveCategoryList.value?.minus(liveCategory)
    }

    fun saveLiveCategories() {

        val newTodayCategoryIterable : List<Category> = liveCategoryList.value!!.map {it.toCategory() }
        val newTodayCategoryRepository = CategoryRepository(PCategoryRepository().also {it.addCategories(newTodayCategoryIterable)} )
        val roomCategoryRepository = CategoryRepository(roomCategoryDataSource)

        ReplaceCategoriesByDate(getTodayDateString(), newTodayCategoryRepository, initCategoryRepository)
        SaveToExternalCategoryRepository(initCategoryRepository, roomCategoryRepository)

    }


    // Radar

    private fun summarizeLogCategories() : Map<String, Int> =
        SummarizeCategories(liveCategoryList.value!!.map {it.toCategory()})

    fun summarizeCategoriesByDate(dateString: String): Map<String, Int> =
        SummarizeCategories(GetCategoriesForDate(initCategoryRepository, dateString))

    fun summarizeCategoriesByDateRange(dateStart: String, dateEnd: String): Map<String, Int> =
        SummarizeCategories(GetCategoriesForDateRange(initCategoryRepository, dateStart, dateEnd))

    val radarSummaryLists : MutableLiveData<Pair<List<String>, List<Float>>> get() {

        val summaryMap = summarizeLogCategories()
        val flatSummary = summaryMap.toList()
        val summaryTitles = flatSummary.map { it.first }
        val summaryIntensities = flatSummary.map { it.second.toFloat() / summaryMap.values.maxOrNull()!! }
        return MutableLiveData(summaryTitles to summaryIntensities)

    }

    // Date Select Screen

    val startDate : MutableLiveData<String> = MutableLiveData()
    val endDate   : MutableLiveData<String> = MutableLiveData(getTodayDateString())

    fun onStartDateChange(date : String) {
        startDate.value = date
    }

    fun onEndDateChange(date : String) {
        endDate.value = date
    }


    // Helpers

    private fun getTodayDateString() : String {
        val currentDateTime = LocalDateTime.now()
        return currentDateTime.format(DateTimeFormatter.ISO_DATE)
    }

}
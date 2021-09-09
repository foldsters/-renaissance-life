package com.steamtechs.renaissancelife.ui


import androidx.lifecycle.*
import com.steamtechs.core.data.CategoryRepository
import com.steamtechs.core.domain.Category
import com.steamtechs.core.domain.businesslogic.GetDatesInDateRange
import com.steamtechs.core.domain.businesslogic.GetTodayIsoDateString
import com.steamtechs.core.domain.businesslogic.MergeCategoryRepositories
import com.steamtechs.core.domain.businesslogic.StringCategoryRepositorySerializable
import com.steamtechs.core.interactors.*
import com.steamtechs.platform.datasources.PCategoryRepository
import com.steamtechs.renaissancelife.R
import com.steamtechs.renaissancelife.di.MockBluetoothHandler
import com.steamtechs.renaissancelife.framework.bluetooth.BluetoothHandler
import com.steamtechs.renaissancelife.framework.bluetooth.BluetoothMessageResponseModel
import com.steamtechs.renaissancelife.framework.datasources.RoomCategoryDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import java.lang.Thread.sleep
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
    private var initCategoryRepository : CategoryRepository,
    private val roomCategoryDataSource : RoomCategoryDataSource,
    @MockBluetoothHandler val mockBluetoothHandler : BluetoothHandler
) : ViewModel() {

    // Setup

    private var todayCategoryIterable = GetCategoriesForDate(initCategoryRepository, GetTodayIsoDateString())

    var liveCategoryList : MutableLiveData<List<LiveCategory>> =
        MutableLiveData(todayCategoryIterable.map { LiveCategory.fromCategory(it) })


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

        ReplaceCategoriesByDate(GetTodayIsoDateString(), newTodayCategoryRepository, initCategoryRepository)
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
    val endDate   : MutableLiveData<String> = MutableLiveData(GetTodayIsoDateString())

    fun onStartDateChange(date : String) {
        startDate.value = date
    }

    fun onEndDateChange(date : String) {
        endDate.value = date
    }

    // TODO : The view model shouldn't know how to make a repository, should it?


    init {
        mockBluetoothHandler.registerBluetoothMessageResponseCallback("Update", ::serverCallbackUpdate)
        mockBluetoothHandler.registerBluetoothMessageResponseCallback("Merge", ::serverCallbackMerge)
    }

    fun exampleRepositoryBluetoothSync() {

        val dateList = GetDatesInDateRange("2021-06-01", "2021-11-01")
        val targetRepository = CategoryRepository(PCategoryRepository())

        TicklessCategoryRepositoryFromDateList(
            dateList,
            initCategoryRepository,
            targetRepository
        )

        val encodedTargetRepository =
            StringCategoryRepositorySerializable.encodeCategoryRepository(targetRepository)


        mockBluetoothHandler.sendMessageToDevice(null, encodedTargetRepository, "Update")

    }

    fun serverCallbackUpdate(bluetoothMessageResponseModel: BluetoothMessageResponseModel) {

        val targetRepository = CategoryRepository(PCategoryRepository())
        val syncString = bluetoothMessageResponseModel.message

        StringCategoryRepositorySerializable.decodeString(syncString, targetRepository)
        UpdateTargetRepositoryWithSourceRepository(targetRepository, initCategoryRepository)

        val newEncodedRepository = StringCategoryRepositorySerializable.encodeCategoryRepository(targetRepository)

        mockBluetoothHandler.sendMessageToDevice(null, newEncodedRepository, "Merge")

    }

    fun serverCallbackMerge(bluetoothMessageResponseModel: BluetoothMessageResponseModel) {

        val targetRepository = CategoryRepository(PCategoryRepository())
        val syncString = bluetoothMessageResponseModel.message

        StringCategoryRepositorySerializable.decodeString(syncString, targetRepository)
        MergeCategoryRepositories(initCategoryRepository, targetRepository)

        sleep(1000)

        println("THE RESULTS")

        initCategoryRepository.getCategories().forEach { println(it) }
    }

    // Bluetooth Example Screen

    val receivedChatMessages : MutableLiveData<List<BluetoothMessageResponseModel>> = MutableLiveData()

    init {
        mockBluetoothHandler.registerBluetoothMessageResponseCallback("Chat", ::serverCallbackChat)
    }

    fun serverCallbackChat(bluetoothMessageResponseModel: BluetoothMessageResponseModel) {
        receivedChatMessages.postValue( receivedChatMessages.value?.plus(listOf(bluetoothMessageResponseModel)) ?: listOf() )
    }



}
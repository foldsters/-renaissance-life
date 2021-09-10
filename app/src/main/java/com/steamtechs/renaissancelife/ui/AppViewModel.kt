package com.steamtechs.renaissancelife.ui


import android.bluetooth.BluetoothClass
import android.bluetooth.BluetoothDevice
import android.util.Log
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
import com.steamtechs.renaissancelife.di.RealBluetoothHandler
import com.steamtechs.renaissancelife.framework.bluetooth.core.BluetoothHandler
import com.steamtechs.renaissancelife.framework.bluetooth.util.BluetoothMessageResponseModel
import com.steamtechs.renaissancelife.framework.datasources.RoomCategoryDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import java.lang.Thread.sleep
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
    private var initCategoryRepository : CategoryRepository,
    private val roomCategoryDataSource : RoomCategoryDataSource,
    @RealBluetoothHandler val bluetoothHandler: BluetoothHandler
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


    init {
        bluetoothHandler.startBluetoothServerController()
        bluetoothHandler.registerBluetoothMessageResponseCallback("Update", ::serverCallbackUpdate)
        bluetoothHandler.registerBluetoothMessageResponseCallback("Merge", ::serverCallbackMerge)
    }

    val tag = "View Model"

    fun exampleRepositoryBluetoothSync(deviceAddress : String) {

        Log.i(tag, "Starting Sync")

        val dateList = GetDatesInDateRange("2021-06-01", "2021-08-01")
        val targetRepository = CategoryRepository(PCategoryRepository())

        initCategoryRepository.addCategories(
            listOf(
                Category("A", "2021-05-01", 1),
                Category("B", "2021-05-01", 1),
                Category("C", "2021-05-01", 1),

                Category("B", "2021-06-01", 2),
                Category("C", "2021-06-01", 2),
                Category("D", "2021-06-01", 2),

                Category("C", "2021-07-01", 3),
                Category("D", "2021-07-01", 3),
                Category("E", "2021-07-01", 3),

                Category("D", "2021-08-01", 4),
                Category("E", "2021-08-01", 4),
                Category("F", "2021-08-01", 4),

                Category("E", "2021-09-01", 5),
                Category("F", "2021-09-01", 5),
                Category("G", "2021-09-01", 5),
            )
        )

        Log.i(tag, "0. ${initCategoryRepository.getCategories()}")

        TicklessCategoryRepositoryFromDateList(
            dateList,
            initCategoryRepository,
            targetRepository
        )

        Log.i(tag, "1. Encoding ${targetRepository.getCategories()}")

        val encodedTargetRepository =
            StringCategoryRepositorySerializable.encodeCategoryRepository(targetRepository)

        Log.i(tag, "2. Sending $encodedTargetRepository")

        bluetoothHandler.sendMessageToDevice(deviceAddress, encodedTargetRepository, "Update")

    }

    private fun serverCallbackUpdate(bluetoothMessageResponseModel: BluetoothMessageResponseModel) {

        Log.i(tag, "3. Updating")

        val targetRepository = CategoryRepository(PCategoryRepository())
        val syncString = bluetoothMessageResponseModel.message

        val fakeInitRepository = CategoryRepository(PCategoryRepository())
        val deviceAddress = bluetoothMessageResponseModel.deviceAddress

        fakeInitRepository.addCategories(
            listOf(
                Category("A", "2021-05-02", 1),
                Category("B", "2021-05-02", 1),
                Category("C", "2021-05-02", 1),

                Category("B", "2021-06-02", 2),
                Category("C", "2021-06-02", 2),
                Category("D", "2021-06-02", 2),

                Category("C", "2021-07-02", 3),
                Category("D", "2021-07-02", 3),
                Category("E", "2021-07-02", 3),

                Category("D", "2021-08-02", 4),
                Category("E", "2021-08-02", 4),
                Category("F", "2021-08-02", 4),

                Category("E", "2021-09-02", 5),
                Category("F", "2021-09-02", 5),
                Category("G", "2021-09-02", 5),
            )
        )

        StringCategoryRepositorySerializable.decodeString(syncString, targetRepository)
        UpdateTargetRepositoryWithSourceRepository(targetRepository, fakeInitRepository)

        val newEncodedRepository = StringCategoryRepositorySerializable.encodeCategoryRepository(targetRepository)

        Log.i(tag, "4. Sending $newEncodedRepository")

        bluetoothHandler.sendMessageToDevice(deviceAddress, newEncodedRepository, "Merge")

    }

    private fun serverCallbackMerge(bluetoothMessageResponseModel: BluetoothMessageResponseModel) {

        Log.i(tag, "5. Merging")

        val targetRepository = CategoryRepository(PCategoryRepository())
        val syncString = bluetoothMessageResponseModel.message

        StringCategoryRepositorySerializable.decodeString(syncString, targetRepository)
        MergeCategoryRepositories(initCategoryRepository, targetRepository)

        sleep(1000)

        Log.i(tag, "6. Results")

        initCategoryRepository.getCategories().forEach { Log.i(tag, it.toString()) }
    }

    // Bluetooth Example Screen

    val receivedChatMessages : MutableLiveData<List<BluetoothMessageResponseModel>> = MutableLiveData(listOf())

    init {
        bluetoothHandler.registerBluetoothMessageResponseCallback("Chat", ::serverCallbackChat)
    }

    private fun serverCallbackChat(bluetoothMessageResponseModel: BluetoothMessageResponseModel) {
        println("Received Chat Message: $bluetoothMessageResponseModel")
        receivedChatMessages.postValue( receivedChatMessages.value?.plus(listOf(bluetoothMessageResponseModel)) ?: listOf(bluetoothMessageResponseModel) )
    }



}
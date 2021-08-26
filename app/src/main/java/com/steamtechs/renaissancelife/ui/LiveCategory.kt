package com.steamtechs.renaissancelife.ui

import androidx.lifecycle.MutableLiveData
import com.steamtechs.core.domain.Category
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class LiveCategory private constructor(
    private val _title: String,
    private val _date: String,
    private val _tickValue: Int
) {

    companion object {

        private var liveUniqueKey = 0

        private fun getLiveCategoryUniqueKey() : Int {
            return liveUniqueKey++
        }

        fun fromCategory(category : Category): LiveCategory {
            return LiveCategory(category.title, category.date, category.tickValue)
        }

        fun defaultCategory() : LiveCategory {
            return LiveCategory("New Category", getTodayDateString(), 0)
        }

        private fun getTodayDateString() : String {
            val currentDateTime = LocalDateTime.now()
            return currentDateTime.format(DateTimeFormatter.ISO_DATE)
        }



    }

    val uniqueKey : Int = getLiveCategoryUniqueKey()
    val title = MutableLiveData(_title)
    val tickValue = MutableLiveData(_tickValue)
    var menuState = MutableLiveData(false)
    var editState = MutableLiveData(false)


    fun setName(value : String) {
        title.value = value
    }

    fun setTickValue(value : Int) {
        tickValue.value = value
    }

    fun setMenuState(value : Boolean) {
        println("MENU STATE CHANGED IN VIEW MODEL TO $value")
        menuState.value = value
    }

    fun setEditState(value : Boolean) {
        editState.value = value
    }

    fun toCategory(): Category {
        return Category(title.value!!, _date, tickValue.value!!)
    }


}
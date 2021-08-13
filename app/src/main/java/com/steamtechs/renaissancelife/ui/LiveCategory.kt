package com.steamtechs.renaissancelife.ui

import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.MutableLiveData
import com.steamtechs.core.domain.Category

class LiveCategory private constructor(
    private val _title: String,
    private val _date: String,
    private val _tickValue: Int
) {

    companion object {
        fun fromCategory(category : Category): LiveCategory {
            return LiveCategory(category.title, category.date, category.tickValue)
        }
    }

    val title = MutableLiveData(_title)
    val tickValue = MutableLiveData(_tickValue)

    fun setName(value : String) {
        title.value = value
    }

    fun setTickValue(value : Int) {
        tickValue.value = value
    }

    fun toCategory(): Category {
        return Category(title.value!!, _date, tickValue.value!!)
    }



}
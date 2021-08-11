package com.steamtechs.core.domain

data class Category(private val _title: String,
                    private val _date : String = "",
                    private val _tickValue : Int = 0) {

    init { negativeCheck(_tickValue) }

    var title = _title

    var tickValue : Int = _tickValue
        set(value){
            negativeCheck(value)
            field = value
        }

    var date = _date


    private fun negativeCheck(value:Int){
        if (value < 0){
            throw IllegalArgumentException("tickValue cannot be Negative")
        }
    }

}
package com.steamtechs.core

class Category(var title: String) {
    var tickValue : Int  = 0
        set(value){
            negativeCheck(value)
            field = value
        }

    fun negativeCheck(value:Int){
        if (value < 0){
            throw IllegalArgumentException("tickValue cannot be Negative")
        }
    }

}
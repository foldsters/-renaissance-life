package com.steamtechs.core.domain.businesslogic

interface IterableEndecType<T> {

    fun encodeIterable(iterable: Iterable<T>) : ByteArray

    fun decodeBytes(bytes: ByteArray) : Iterable<T>
}
package com.steamtechs.core.domain.businesslogic

class RawStringEndec<T> : IterableEndecType<T> {
    override fun encodeIterable(iterable: Iterable<T>): ByteArray {
        return iterable.toString().encodeToByteArray()
    }

    override fun decodeBytes(bytes: ByteArray): Iterable<T> {
        TODO("Not yet implemented")
    }

}
package com.steamtechs.core.domain.businesslogic

import com.steamtechs.core.data.CategoryRepository
import com.steamtechs.core.domain.Category
import com.steamtechs.platform.datasources.PCategoryRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class RawStringEndecTest{

    val rawEndec = RawStringEndec<Category>()
    val categoryRepository = CategoryRepository(
        PCategoryRepository().also {
            it.addCategories(
                listOf(
                    Category("oldCat1","2020-08-29", 3),
                    Category("oldCat2","2021-08-29", 4),
                    Category("oldCat3","2021-08-29", 6),
                    Category("oldCat4","2020-03-09", 1),
                )
            ) })

    
    @Test
    @DisplayName("Create Instance of RawStringEndec<T>.")
    fun `Create Instance of RawStringEndec`() {
        assertInstanceOf(RawStringEndec::class.java, rawEndec)
    }

    @Test
    @DisplayName("Instance is an IterableEndecType.")
    fun `Instance is an IterableEndecType`() {
        assertInstanceOf(IterableEndecType::class.java, rawEndec)
    }

    @Test
    @DisplayName("EncodeIterable returns a ByteArray of a given Iterable<T>.")
    fun `EncodeIterable returns a ByteArray from a given Iterable of T`() {
        assertInstanceOf(ByteArray::class.java,rawEndec.encodeIterable(categoryRepository.getCategories()))
    }

    @Test
    @DisplayName("DecodeBytes returns an Iterable of T.")
    fun `DecodeBytes returns an Iterable of T`() {
        val encodedIterable = rawEndec.encodeIterable(categoryRepository.getCategories())
        val decodedBytes = rawEndec.decodeBytes(encodedIterable)
        assertInstanceOf(Iterable::class.java, decodedBytes)
    }


}
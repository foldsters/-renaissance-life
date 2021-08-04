package com.steamtechs.renaissancelife.framework.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.steamtechs.core.domain.Category
import java.util.*

@Entity(tableName = "category")
data class CategoryEntity (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "date") val name: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "tickvalue") val tickvalue: Int
) {

    constructor(date: String, title: String, tickvalue: Int) : this(0, date, title, tickvalue)

    override fun equals(other: Any?): Boolean {
        return hashCode() == other.hashCode()
    }

    override fun hashCode(): Int {
        return Objects.hash(name, title)
    }
}
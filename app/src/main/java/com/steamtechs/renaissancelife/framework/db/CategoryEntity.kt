package com.steamtechs.renaissancelife.framework.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.steamtechs.core.domain.Category
import java.util.*

@Entity(tableName = "categoryTable")
data class CategoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "date") val date : String,
    @ColumnInfo(name = "title") val title : String,
    @ColumnInfo(name = "tickvalue") val tickValue : Int) {


    companion object {
        fun fromCategory(category : Category) : CategoryEntity =
            CategoryEntity(0, category.date, category.title, category.tickValue)
   }


    fun toCategory() : Category = Category(title).apply { tickValue = tickValue }

    override fun equals(other: Any?) : Boolean {
        return hashCode() == other.hashCode()
    }

    override fun hashCode(): Int {
        return Objects.hash(date, title, tickValue)
    }

}
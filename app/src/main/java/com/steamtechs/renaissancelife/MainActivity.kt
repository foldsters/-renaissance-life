package com.steamtechs.renaissancelife

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.room.Room
import com.steamtechs.renaissancelife.framework.db.CategoryDatabase
import com.steamtechs.renaissancelife.framework.db.CategoryEntity

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = CategoryDatabase.getInstance(this)

        val categoryDao = db.categoryDao()

        categoryDao.deleteAll()

        val lastWeek = CategoryEntity("2021-07-25", "goodbye", 1)
        val yesterday = CategoryEntity("2021-08-02", "hello", 2)
        val today = CategoryEntity("2021-08-03", "hello", 3)
        val nextWeek = CategoryEntity("2021-08-10", "hello", 4)

        categoryDao.insertAllCategories(today, yesterday, lastWeek, nextWeek)

        val categories : List<CategoryEntity> = categoryDao.getCategoriesBetweenDates("2021-08-01", "2021-08-05")

        Toast.makeText(this, categories.toString(), Toast.LENGTH_LONG).show()

    }
}
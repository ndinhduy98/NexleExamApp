package com.freezer.nexle_examapp.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.freezer.nexle_examapp.data.model.Category

@Database(
  entities = [Category::class],
  version = 1
)
abstract class Database : RoomDatabase() {
  abstract fun getCategoryDao(): CategoryDao
}
package com.freezer.nexle_examapp.room

import androidx.room.*
import com.freezer.nexle_examapp.data.model.Category

@Dao
interface CategoryDao {
  @Query("SELECT * FROM categories WHERE isSelected=1")
  suspend fun getSelectedCategories() : List<Category>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertSelectedCategory(category: Category)

  @Update
  suspend fun updateSelectedCategory(category: Category)
}
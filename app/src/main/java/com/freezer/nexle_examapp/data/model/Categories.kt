package com.freezer.nexle_examapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Categories(
  val categories: ArrayList<Category>,
  val totalCount: Int
)

@Entity(tableName = "categories")
data class Category(
  @field:SerializedName("_id")
  @Expose
  @PrimaryKey
  var id: String,
  @Expose
  var name: String,
  var isSelected: Boolean = false
)
package com.freezer.nexle_examapp.data.repository

import com.freezer.nexle_examapp.data.api.ApiHelper
import com.freezer.nexle_examapp.data.model.Category
import com.freezer.nexle_examapp.room.CategoryDao
import javax.inject.Inject

class MainRepository @Inject constructor(
  private val apiHelper: ApiHelper,
  private val categoryDao: CategoryDao
){
  suspend fun signUp(email: String, password: String) = apiHelper.signUp(email, password)
  suspend fun getCategories() = apiHelper.getCategories()
  suspend fun insertSelectedCategory(category: Category) = categoryDao.insertSelectedCategory(category)
  suspend fun getSelectedCategories() = categoryDao.getSelectedCategories()
  suspend fun updateSelectedCategory(category: Category) = categoryDao.updateSelectedCategory(category)
}
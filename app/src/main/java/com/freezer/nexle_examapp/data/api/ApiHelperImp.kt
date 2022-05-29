package com.freezer.nexle_examapp.data.api

import javax.inject.Inject

class ApiHelperImp @Inject constructor(private val apiService: ApiService) : ApiHelper {
  override suspend fun signUp(email: String, password: String) = apiService.signUp(email = email, password = password)
  override suspend fun getCategories() = apiService.getCategories()
}
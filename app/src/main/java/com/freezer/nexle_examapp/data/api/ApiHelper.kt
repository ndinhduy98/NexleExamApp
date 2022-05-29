package com.freezer.nexle_examapp.data.api

import com.freezer.nexle_examapp.data.model.Categories
import com.freezer.nexle_examapp.data.model.SignUpResponse
import retrofit2.Response

interface ApiHelper {
  suspend fun signUp(email: String, password: String): Response<SignUpResponse>
  suspend fun getCategories(): Response<Categories>
}
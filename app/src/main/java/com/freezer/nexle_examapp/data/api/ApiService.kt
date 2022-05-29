package com.freezer.nexle_examapp.data.api

import com.freezer.nexle_examapp.data.model.Categories
import com.freezer.nexle_examapp.data.model.SignUpResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
  @POST("auth/signup")
  @FormUrlEncoded
  suspend fun signUp(
    @Field("firstName") firstName: String = "Nguyen",
    @Field("lastName") lastName: String = "Dinh Duy",
    @Field("email") email: String,
    @Field("password") password: String
  ): Response<SignUpResponse>

  @GET("categories?pageSize=100&pageNumber=0")
  suspend fun getCategories(): Response<Categories>
}
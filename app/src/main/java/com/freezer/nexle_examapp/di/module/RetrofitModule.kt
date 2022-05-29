package com.freezer.nexle_examapp.di.module

import com.freezer.nexle_examapp.BuildConfig
import com.freezer.nexle_examapp.data.api.ApiHelper
import com.freezer.nexle_examapp.data.api.ApiHelperImp
import com.freezer.nexle_examapp.data.api.ApiService
import com.freezer.nexle_examapp.data.api.TokenInterceptor
import com.freezer.nexle_examapp.data.model.Categories
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
  @Provides
  fun providesBaseUrl() = BuildConfig.BASE_URL

  @Provides
  @Singleton
  fun providesOkHttpClient(tokenInterceptor: TokenInterceptor): OkHttpClient =
    OkHttpClient.Builder()
      .addInterceptor(tokenInterceptor)
      .build()

  @Provides
  @Singleton
  fun providesRetrofit(okHttpClient: OkHttpClient, BASE_URL: String): Retrofit =
    Retrofit.Builder()
      .addConverterFactory(GsonConverterFactory.create())
      .baseUrl(BASE_URL)
      .client(okHttpClient)
      .build()

  @Provides
  @Singleton
  fun providesApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

  @Provides
  @Singleton
  fun providesApiHelper(apiHelper: ApiHelperImp): ApiHelper = apiHelper
}
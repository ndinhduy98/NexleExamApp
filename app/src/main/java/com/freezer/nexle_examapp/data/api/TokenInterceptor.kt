package com.freezer.nexle_examapp.data.api

import com.freezer.nexle_examapp.di.module.AppPreference
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class TokenInterceptor @Inject constructor(
  private val appPreference: AppPreference
): Interceptor {
  override fun intercept(chain: Interceptor.Chain): Response {
    val newRequest = chain.request().newBuilder()
      .addHeader("Authorization", "Bearer ${appPreference.getToken()}")
      .build()
    return chain.proceed(newRequest)
  }
}
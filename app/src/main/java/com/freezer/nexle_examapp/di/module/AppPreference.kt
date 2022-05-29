package com.freezer.nexle_examapp.di.module

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

@Module
@InstallIn(SingletonComponent::class)
class AppPreference @Inject constructor(
  @ApplicationContext context: Context
) {
  companion object {
    const val ACCESS_TOKEN = "ACCESS_TOKEN"
    const val REFRESH_TOKEN = "REFRESH_TOKEN"
  }

  private val prefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

  fun getToken(): String = prefs.getString(ACCESS_TOKEN, "")!!

  fun setToken(token: String) {
    prefs.edit().putString(ACCESS_TOKEN, token).apply()
  }

  fun getRefreshToken(): String = prefs.getString(REFRESH_TOKEN, "")!!

  fun setRefreshToken(refreshToken: String) {
    prefs.edit().putString(REFRESH_TOKEN, refreshToken).apply()
  }
}
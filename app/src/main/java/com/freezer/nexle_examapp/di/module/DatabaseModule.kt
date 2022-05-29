package com.freezer.nexle_examapp.di.module

import android.content.Context
import androidx.room.Room
import com.freezer.nexle_examapp.room.Database
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
  @Provides
  @Singleton
  fun providesDatabase(@ApplicationContext context: Context) =
    Room.databaseBuilder(context, Database::class.java, "app_db")
      .build()

  @Provides
  @Singleton
  fun providesDao(database: Database) = database.getCategoryDao()
}
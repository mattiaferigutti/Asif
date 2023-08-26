package com.mattiaferigutti.core.di

import android.content.Context
import androidx.room.Room
import com.mattiaferigutti.core.data.database.AppDatabase
import com.mattiaferigutti.core.data.database.Converters
import com.mattiaferigutti.core.data.repo.TaskRepoImpl
import com.mattiaferigutti.core.domain.repo.ITaskRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

  @Singleton
  @Provides
  fun provideDatabase(
    @ApplicationContext app: Context
  ) : AppDatabase =
    Room.databaseBuilder(
      context = app,
      klass = AppDatabase::class.java,
      name = "tasks-database"
    )
      .addTypeConverter(Converters())
      .build()

  @Singleton
  @Provides
  fun provideTaskDao(
    database: AppDatabase
  ) : ITaskRepo = TaskRepoImpl(database.taskDao())

}
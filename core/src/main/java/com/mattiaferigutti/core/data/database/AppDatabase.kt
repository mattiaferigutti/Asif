package com.mattiaferigutti.core.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mattiaferigutti.core.data.dao.TaskDao
import com.mattiaferigutti.core.data.entities.LocalTask

@Database(entities = [LocalTask::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
  abstract fun taskDao(): TaskDao
}
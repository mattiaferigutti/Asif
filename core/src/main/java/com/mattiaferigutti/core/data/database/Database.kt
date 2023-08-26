package com.mattiaferigutti.core.data.database

import android.content.Context
import androidx.room.Room

object Database {

  operator fun invoke(context: Context) : AppDatabase =
    Room.databaseBuilder(
      context = context,
      klass = AppDatabase::class.java,
      name = "tasks-database"
    ).build()
}
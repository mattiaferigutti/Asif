package com.mattiaferigutti.core.data.database

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime

@ProvidedTypeConverter
class Converters {

  @TypeConverter
  fun fromDate(date: ZonedDateTime) : Long {
    return date.toInstant().toEpochMilli()
  }

  @TypeConverter
  fun toDate(millis: Long) : ZonedDateTime {
    return Instant.ofEpochMilli(millis).atZone(ZoneId.systemDefault())
  }
}
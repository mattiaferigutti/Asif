package com.mattiaferigutti.core.data.database

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime

@ProvidedTypeConverter
class Converters {

  @TypeConverter
  fun fromDate(date: ZonedDateTime?) : Long {
    return date?.toInstant()?.toEpochMilli() ?: -1L
  }

  @TypeConverter
  fun toDate(millis: Long) : ZonedDateTime? {
    if (millis == -1L) return null

    return Instant.ofEpochMilli(millis).atZone(ZoneId.systemDefault())
  }
}
package com.mattiaferigutti.core.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mattiaferigutti.core.domain.entities.Task
import java.time.ZonedDateTime

@Entity(tableName = "tasks")
data class LocalTask(

  @PrimaryKey(autoGenerate = true)
  val id: Long,

  @ColumnInfo(name = "title")
  val title: String,

  @ColumnInfo(name = "description")
  val description: String,

  @ColumnInfo(name = "completed_date")
  val completedDate: ZonedDateTime? = null,

  @ColumnInfo(name = "creation_date")
  val creationDate: ZonedDateTime
)

fun Task.toLocal() : LocalTask =
  LocalTask(
    id = this.id,
    title = this.title,
    description = this.description,
    completedDate = this.completedDate,
    creationDate = this.creationDate
  )


fun LocalTask.fromLocal() : Task =
  Task(
    id = this.id,
    title = this.title,
    description = this.description,
    completedDate = this.completedDate,
    creationDate = this.creationDate
  )

package com.mattiaferigutti.core.domain.entities

import java.time.ZonedDateTime

data class Task(
  val id: Long,
  val title: String,
  val description: String,
  val completedDate: ZonedDateTime? = null,
  val creationDate: ZonedDateTime
)
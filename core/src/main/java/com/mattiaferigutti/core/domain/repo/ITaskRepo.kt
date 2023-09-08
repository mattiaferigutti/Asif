package com.mattiaferigutti.core.domain.repo

import com.mattiaferigutti.core.data.entities.LocalTask
import com.mattiaferigutti.core.domain.entities.Task
import kotlinx.coroutines.flow.Flow

interface ITaskRepo {

  fun getTasks(): Flow<List<Task>>

  fun getUncompletedTasks(): Flow<List<Task>>

  fun getTaskById(id: Long): Flow<Task>

  suspend fun storeTask(task: Task)

  suspend fun completeTask(task: Task)

  suspend fun updateTask(task: Task)

}
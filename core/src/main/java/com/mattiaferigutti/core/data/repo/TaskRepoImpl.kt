package com.mattiaferigutti.core.data.repo

import com.mattiaferigutti.core.data.dao.TaskDao
import com.mattiaferigutti.core.data.entities.LocalTask
import com.mattiaferigutti.core.data.entities.toLocal
import com.mattiaferigutti.core.data.entities.fromLocal
import com.mattiaferigutti.core.domain.entities.Task
import com.mattiaferigutti.core.domain.repo.ITaskRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TaskRepoImpl(
  private val taskDao: TaskDao
) : ITaskRepo {

  override fun getTasks(): Flow<List<Task>> {
    return taskDao.getAllTasks().map { it.map(LocalTask::fromLocal) }
  }

  override fun getUncompletedTasks(): Flow<List<Task>> {
    return taskDao.getUncompletedTasks().map { it.map(LocalTask::fromLocal) }
  }

  override suspend fun storeTask(task: Task) {
    return taskDao.insertTask(task.toLocal())
  }

}
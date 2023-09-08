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

  override fun getTaskById(id: Long): Flow<Task> {
    return taskDao.getTaskById(id).map { it.fromLocal() }
  }

  override suspend fun storeTask(task: Task) {
    return taskDao.insertTask(task.toLocal())
  }

  override suspend fun completeTask(task: Task) {
    return taskDao.updateTask(task.toLocal())
  }

  override suspend fun updateTask(task: Task) {
    taskDao.updateTask(task.toLocal())
  }

}
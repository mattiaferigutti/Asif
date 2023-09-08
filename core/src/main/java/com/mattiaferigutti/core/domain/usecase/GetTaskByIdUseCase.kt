package com.mattiaferigutti.core.domain.usecase

import com.mattiaferigutti.core.domain.entities.Task
import com.mattiaferigutti.core.domain.repo.ITaskRepo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class GetTaskByIdUseCase(
  private val taskRepo: ITaskRepo,
  private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
) {

    operator fun invoke(id: Long) : Flow<Task> =
      taskRepo.getTaskById(id).flowOn(defaultDispatcher)
}
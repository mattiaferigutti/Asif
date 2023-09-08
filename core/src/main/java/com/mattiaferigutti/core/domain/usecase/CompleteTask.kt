package com.mattiaferigutti.core.domain.usecase

import com.mattiaferigutti.core.domain.entities.Task
import com.mattiaferigutti.core.domain.repo.ITaskRepo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.ZonedDateTime

class CompleteTask(
  private val taskRepo: ITaskRepo,
  private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
) {

  suspend operator fun invoke(task: Task) =
    withContext(defaultDispatcher) {
      taskRepo.completeTask(
        task.copy(
          completedDate = ZonedDateTime.now()
        )
      )
    }

}
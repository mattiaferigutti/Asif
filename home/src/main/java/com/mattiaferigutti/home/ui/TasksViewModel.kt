package com.mattiaferigutti.home.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.mattiaferigutti.core.data.database.Database
import com.mattiaferigutti.core.data.repo.TaskRepoImpl
import com.mattiaferigutti.core.domain.entities.Task
import com.mattiaferigutti.core.domain.repo.ITaskRepo
import com.mattiaferigutti.core.domain.usecase.StoreTaskUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TasksViewModel(
  application: Application
) : AndroidViewModel(application) {

  // todo will be replaced with dependency injection
  val repo: ITaskRepo = TaskRepoImpl(Database(application.applicationContext).taskDao())

  private val _uiState = MutableStateFlow(TasksUIState())
  val uiState = _uiState.asStateFlow()

  fun onEvent(tasksUIEvent: TasksUIEvent) {
    when(tasksUIEvent) {
      is TasksUIEvent.AddTask -> {
        viewModelScope.launch {
          StoreTaskUseCase(repo)(Task(title = tasksUIEvent.title))
        }
      }
      is TasksUIEvent.CompletedTask -> {
        // todo
      }
    }
  }

}

sealed interface TasksUIEvent {
  data class AddTask(val title: String) : TasksUIEvent
  data class CompletedTask(val task: Task) : TasksUIEvent
}

data class TasksUIState(
  val tasks: List<Task> = emptyList(),
)
package com.mattiaferigutti.home.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mattiaferigutti.core.domain.entities.Task
import com.mattiaferigutti.core.domain.repo.ITaskRepo
import com.mattiaferigutti.core.domain.usecase.CompleteTask
import com.mattiaferigutti.core.domain.usecase.GetTaskUseCase
import com.mattiaferigutti.core.domain.usecase.GetUncompletedTasksUseCase
import com.mattiaferigutti.core.domain.usecase.StoreTaskUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(
  private val repo: ITaskRepo
) : ViewModel() {

  private val _uiState = MutableStateFlow(TasksUIState())
  val uiState = _uiState.asStateFlow()

  init {
    viewModelScope.launch {
      GetUncompletedTasksUseCase(repo)()
        .collect { tasks ->
          _uiState.value = TasksUIState(tasks = tasks)
        }
    }
  }

  fun onEvent(tasksUIEvent: TasksUIEvent) {
    when(tasksUIEvent) {
      is TasksUIEvent.AddTask -> {
        viewModelScope.launch {
          StoreTaskUseCase(repo)(
            Task(
              id = 0,
              title = tasksUIEvent.title,
              description = "",
              creationDate = java.time.ZonedDateTime.now()
            )
          )
        }
      }
      is TasksUIEvent.CompletedTask -> {
        viewModelScope.launch {
          CompleteTask(repo)(tasksUIEvent.task)
        }
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
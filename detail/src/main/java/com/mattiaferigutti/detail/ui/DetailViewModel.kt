package com.mattiaferigutti.detail.ui

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mattiaferigutti.core.domain.entities.Task
import com.mattiaferigutti.core.domain.repo.ITaskRepo
import com.mattiaferigutti.core.domain.usecase.GetTaskByIdUseCase
import com.mattiaferigutti.core.domain.usecase.UpdateTaskUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
  private val repo: ITaskRepo
) : ViewModel() {

  private val _uiState = MutableStateFlow(DetailUIState())
  val uiState = _uiState.asStateFlow()

  // todo don't have a single source of truth
  //  save this code inside viewmodel
  var title by mutableStateOf(TextFieldValue(""))
  var description by mutableStateOf(TextFieldValue(""))

  fun onEvent(event: DetailUIEvent) {
    when(event) {
      is DetailUIEvent.SaveEdit -> {
        viewModelScope.launch {
          if (uiState.value.task == null) return@launch
          UpdateTaskUseCase(repo)(
            uiState.value.task!!.copy(
              title = title.text,
              description = description.text
            )
          )
        }
      }
      is DetailUIEvent.TaskIdReceived -> {
        viewModelScope.launch {
          val task = GetTaskByIdUseCase(repo)(event.id).first()
          _uiState.value = DetailUIState(task = task)
          title = TextFieldValue(task.title)
          description = TextFieldValue(task.description)
        }
      }
      is DetailUIEvent.TitleChanged -> {
        _uiState.value = _uiState.value.copy(
          task = _uiState.value.task?.copy(title = event.title)
        )
      }
    }
  }

  // todo can i use this instead of disposable effect?
  @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
  fun saveNote() {
    viewModelScope.launch {
      Log.d("DetailViewModel", "saveNote: ${uiState.value.task}")
    }
  }

}

sealed interface DetailUIEvent {
  data class TaskIdReceived(val id: Long) : DetailUIEvent
  object SaveEdit : DetailUIEvent
  data class TitleChanged(val title: String) : DetailUIEvent
}

data class DetailUIState(
  val task: Task? = null
)
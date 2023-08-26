package com.mattiaferigutti.home.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun TasksScreen(
  modifier: Modifier = Modifier,
  tasksViewModel: TasksViewModel = viewModel()
) {

  val tasks by tasksViewModel.uiState.collectAsState()

  var addTask by remember { mutableStateOf(false) }
  var text by remember(TextFieldValue.Saver) {
    mutableStateOf(TextFieldValue(""))
  }

  if (addTask) {
    AddTaskDialogContent(
      modifier = Modifier,
      text = text,
      onTextChanged = { text = it },
      onCreate = {
        tasksViewModel.onEvent(TasksUIEvent.AddTask(it.text))
        addTask = false
      }
    )
  }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AddTaskDialogContent(
  modifier: Modifier,
  onCreate: (TextFieldValue) -> Unit,
  onTextChanged: (TextFieldValue) -> Unit,
  text: TextFieldValue
) {

  Column(
    modifier = modifier
      .defaultMinSize(minHeight = 250.dp)
      .padding(24.dp),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.End
  ) {

    TextField(
      value = text,
      onValueChange = { onTextChanged(it) },
      placeholder = { Text("Ex. Write monthly newsletter") },
    )

    Spacer(modifier = Modifier.padding(16.dp))

    FilledIconButton(
      modifier = Modifier
        .clip(MaterialTheme.shapes.small),
      onClick = { onCreate(text) },
      colors = IconButtonDefaults.iconButtonColors(
        containerColor = MaterialTheme.colorScheme.primary,
      ),
      shape = MaterialTheme.shapes.medium,
    ) {
      Icon(
        Icons.Filled.Add,
        contentDescription = "Localized description",
        tint = MaterialTheme.colorScheme.onPrimary
      )
    }
  }
}
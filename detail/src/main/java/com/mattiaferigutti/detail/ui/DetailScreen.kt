package com.mattiaferigutti.detail.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mattiaferigutti.core.domain.entities.Task
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
  modifier: Modifier,
  lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
  viewModel: DetailViewModel = hiltViewModel(),
  taskId: Long
) {

  LaunchedEffect(key1 = Unit) {
    viewModel.onEvent(DetailUIEvent.TaskIdReceived(taskId))
  }

  val uiState by viewModel
    .uiState
    .collectAsState()

  Column(
    modifier = modifier
  ) {
    TextField(
      modifier = Modifier
        .fillMaxWidth(),
      value = viewModel.title,
      onValueChange = { viewModel.title = it },
      textStyle = TextStyle(
        fontSize = 18.sp,
        fontWeight = FontWeight.SemiBold
        ),
      placeholder = {
        Text(
          text = "Title",
          fontSize = 18.sp,
          fontWeight = FontWeight.SemiBold,
        )
      },
      colors = TextFieldDefaults.textFieldColors(
        containerColor = MaterialTheme.colorScheme.background,
        focusedIndicatorColor = MaterialTheme.colorScheme.background.copy(alpha = 1f),
        unfocusedIndicatorColor = MaterialTheme.colorScheme.background.copy(alpha = 1f),
        cursorColor = MaterialTheme.colorScheme.primary,
        textColor = MaterialTheme.colorScheme.onSurface,
      ),
    )

    TextField(
      modifier = Modifier
        .fillMaxWidth(),
      value = viewModel.description,
      onValueChange = { viewModel.description = it },
      placeholder = { Text("Description") },
      colors = TextFieldDefaults.textFieldColors(
        containerColor = MaterialTheme.colorScheme.background,
        focusedIndicatorColor = MaterialTheme.colorScheme.background.copy(alpha = 1f),
        unfocusedIndicatorColor = MaterialTheme.colorScheme.background.copy(alpha = 1f),
        cursorColor = MaterialTheme.colorScheme.primary,
        textColor = MaterialTheme.colorScheme.onSurface,
      ),
    )

  }

  // todo can we avoid doing it from here?
  DisposableEffect(lifecycleOwner) {
    // Create an observer that triggers our remembered callbacks
    // for sending analytics events
    val observer = LifecycleEventObserver { _, event ->
      if (event == Lifecycle.Event.ON_PAUSE) {
        viewModel.onEvent(DetailUIEvent.SaveEdit)
      }
    }

    // Add the observer to the lifecycle
    lifecycleOwner.lifecycle.addObserver(observer)

    // When the effect leaves the Composition, remove the observer
    onDispose {
      lifecycleOwner.lifecycle.removeObserver(observer)
    }
  }

}
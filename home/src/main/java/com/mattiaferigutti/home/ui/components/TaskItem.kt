package com.mattiaferigutti.home.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskItem(
  modifier: Modifier,
  title: String,
  checked: Boolean,
  onCheck: (Boolean) -> Unit,
) {

  Surface(
    modifier = modifier
      .fillMaxWidth(),
    onClick = { onCheck(!checked) },
    tonalElevation = 8.dp
  ) {

    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Text(
        text = title,
        fontSize = 18.sp,
        fontWeight = FontWeight.SemiBold,
      )

      Checkbox(
        checked = checked,
        onCheckedChange = { onCheck(it) }
      )
    }
  }
}

@Composable
@Preview
fun TaskItemPreview() {
  TaskItem(
    modifier = Modifier,
    title = "Task 1",
    checked = false,
    onCheck = {}
  )
}
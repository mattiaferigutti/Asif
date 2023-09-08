package com.example.myapplication.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mattiaferigutti.detail.ui.DetailScreen
import com.mattiaferigutti.home.ui.TasksScreen

@Composable
fun Route(modifier: Modifier) {

  val navController = rememberNavController()

  NavHost(
    navController = navController,
    startDestination = Screen.Home.route
  ) {

    composable(Screen.Home.route) {
      TasksScreen(
        modifier = modifier,
        goToDetailScreen = { id ->
          navController.navigate(Screen.Detail.route + "/${id}")
        }
      )
    }

    composable(
      route = Screen.Detail.route + "/{$TASK_ID_KEY}",
      arguments = listOf(
        navArgument(TASK_ID_KEY) {
          type = NavType.LongType
        }
      )
    ) { navBackStackEntry ->
      val taskId = navBackStackEntry.arguments?.getLong(TASK_ID_KEY) ?: -1
      Log.d("Route", "taskId: $taskId")

      if (taskId == -1L) {
        throw IllegalArgumentException("taskId not found")
      }

      DetailScreen(
        modifier = modifier,
        taskId = taskId
      )
    }
  }
}
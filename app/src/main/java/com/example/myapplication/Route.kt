package com.example.myapplication

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mattiaferigutti.home.ui.TasksScreen

@Composable
fun Route(modifier: Modifier) {

  val navController = rememberNavController()

  NavHost(navController = navController, startDestination = "profile") {
    composable(Screen.Home.route) {
      TasksScreen(modifier)
    }
    composable(Screen.Detail.route) {

    }

  }
}
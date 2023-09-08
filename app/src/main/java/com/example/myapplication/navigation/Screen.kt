package com.example.myapplication.navigation

sealed class Screen(val route: String) {
  object Home : Screen("home")
  object Detail : Screen("detail")
}

const val TASK_ID_KEY = "task_id_key"
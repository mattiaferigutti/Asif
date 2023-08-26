package com.example.myapplication

sealed class Screen(val route: String) {
  object Home : Screen("home")
  object Detail : Screen("detail")
}

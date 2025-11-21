package com.example.baitaptuan7_database.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.baitaptuan7_database.ui.screens.DetailScreen
import com.example.baitaptuan7_database.ui.screens.ListScreen
import com.example.baitaptuan7_database.viewmodel.TaskViewModel

sealed class Screen(val route: String) {
    object List : Screen("list_screen")
    object Detail : Screen("detail_screen/{taskId}") {
        fun createRoute(taskId: Int) = "detail_screen/$taskId"
    }
}

@Composable
fun AppNavigation(vm: TaskViewModel) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = Screen.List.route) {
        composable(Screen.List.route) {
            ListScreen(vm) { taskId ->
                navController.navigate(Screen.Detail.createRoute(taskId))
            }
        }

        composable(
            route = Screen.Detail.route,
            arguments = listOf()
        ) { backStackEntry ->
            val taskId = backStackEntry.arguments?.getString("taskId")?.toInt() ?: 0
            DetailScreen(vm, taskId) {
                navController.popBackStack()
            }
        }
    }
}

package com.example.baitaptuan4_otpemail1.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.baitaptuan4_otpemail1.ui.screen.Screen_Confirm
import com.example.baitaptuan4_otpemail1.ui.screen.Screen_Forget
import com.example.baitaptuan4_otpemail1.ui.screen.Screen_ResetPassword
import com.example.baitaptuan4_otpemail1.ui.screen.Screen_Verify
import com.example.baitaptuan4_otpemail1.ui.viewmodel.UserViewModel

@Composable
fun Navigation(vm: UserViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "forget") {
        composable("forget") { Screen_Forget(navController, vm) }
        composable("verify") { Screen_Verify(navController, vm) }
        composable("reset") { Screen_ResetPassword(navController, vm) }
        composable("confirm") { Screen_Confirm(navController, vm) }
    }
}

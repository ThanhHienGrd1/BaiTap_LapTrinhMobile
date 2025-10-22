package com.example.baitaptuan4_emailotp.ui.screen

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.baitaptuan4_emailotp.ui.viewmodel.UserViewModel

@Composable
fun Navigation() {
    val navController: NavHostController = rememberNavController()
    val vm: UserViewModel = viewModel()


    NavHost(
        navController, startDestination = "forget"
    ) {
        composable("forget") {
            Screen_ForgetPassword(navController, vm)
        }

        composable("verify") {
            VerifyOTP(navController, vm)
        }

        composable("reset") {
            Screen_ResetPassword(navController, vm)
        }

        composable("confirm") {
            Screen_Confirm(navController, vm)
        }
    }


}
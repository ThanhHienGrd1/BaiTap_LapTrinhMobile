package com.example.baitaptuan5_firebaselogin.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.baitaptuan5_firebaselogin.ui.screen.Login_Screen
import com.example.baitaptuan5_firebaselogin.ui.screen.Profile_Screen
import com.example.baitaptuan5_firebaselogin.viewmodel.UserViewModel
import com.google.android.gms.auth.api.signin.GoogleSignInClient

@Composable
fun Navigation(vm: UserViewModel, googleSignInClient: GoogleSignInClient) {
    val navController = rememberNavController()
    val isLogined by vm.isLogined.collectAsState()

    // Nếu đã login thì navigate sang profile
    if (isLogined) {
        navController.navigate("profile") {
            popUpTo("login") { inclusive = true }
        }
    }

    NavHost(navController = navController, startDestination = "login") {
        composable("login") { Login_Screen(vm = vm, googleSignInClient = googleSignInClient) }
        composable("profile") { Profile_Screen(vm = vm) }
    }

//    LaunchedEffect(isLogined) {
//        if (isLogined) {
//            navController.navigate("profile") {
//                popUpTo("login") { inclusive = true }
//            }
//        }
//    }
}
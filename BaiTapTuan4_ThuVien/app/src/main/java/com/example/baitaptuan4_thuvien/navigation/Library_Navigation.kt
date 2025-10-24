package com.example.baitaptuan4_thuvien.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.baitaptuan4_thuvien.ui.screen.Screen_DanhSachSach
import com.example.baitaptuan4_thuvien.ui.screen.Screen_DanhSachSinhVien
import com.example.baitaptuan4_thuvien.ui.screen.Screen_QuanLy
import com.example.baitaptuan4_thuvien.viewmodel.LibraryViewModel

@Composable
fun Library_Navigation(vm: LibraryViewModel){

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "quanly"){
        composable("quanly"){Screen_QuanLy(navController,vm)}
        composable("danhsachsach"){Screen_DanhSachSach(navController,vm)}
       composable("danhsachsinhvien"){Screen_DanhSachSinhVien(navController,vm)}


    }

}
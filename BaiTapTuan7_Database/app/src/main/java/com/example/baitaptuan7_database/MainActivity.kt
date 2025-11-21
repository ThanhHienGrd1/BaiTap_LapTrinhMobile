package com.example.baitaptuan7_database

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.baitaptuan7_database.navigation.AppNavigation
import com.example.baitaptuan7_database.ui.theme.BaiTapTuan7_DatabaseTheme
import com.example.baitaptuan7_database.ui.theme.BaiTapTuan7_DatabaseTheme
import com.example.baitaptuan7_database.viewmodel.TaskViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BaiTapTuan7_DatabaseTheme {
                val vm: TaskViewModel = viewModel()
                AppNavigation(vm)
            }
        }
    }
}

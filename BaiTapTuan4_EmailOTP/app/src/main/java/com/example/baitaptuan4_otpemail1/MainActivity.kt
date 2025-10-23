package com.example.baitaptuan4_otpemail1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.baitaptuan4_otpemail1.ui.navigation.Navigation
import com.example.baitaptuan4_otpemail1.ui.theme.BaiTapTuan4_OTPEmail1Theme
import com.example.baitaptuan4_otpemail1.ui.viewmodel.UserViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BaiTapTuan4_OTPEmail1Theme {
                Scaffold(modifier = Modifier.windowInsetsPadding(WindowInsets.safeDrawing)) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        val vm: UserViewModel = viewModel()
                        Navigation(vm)

                    }
                }
            }
        }
    }
}


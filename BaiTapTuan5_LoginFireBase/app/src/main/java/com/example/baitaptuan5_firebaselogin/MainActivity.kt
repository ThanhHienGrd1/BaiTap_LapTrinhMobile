package com.example.baitaptuan5_firebaselogin

// ✅ THÊM: Firebase + Google Sign-In imports
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
import com.example.baitaptuan5_firebaselogin.navigation.Navigation
import com.example.baitaptuan5_firebaselogin.ui.theme.BaiTapTuan5_FireBaseLoginTheme
import com.example.baitaptuan5_firebaselogin.viewmodel.UserViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.FirebaseApp

class MainActivity : ComponentActivity() {

    // ✅ THÊM: giữ GoogleSignInClient để truyền xuống Navigation/LoginScreen
    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ✅ THÊM: Khởi tạo Firebase
        FirebaseApp.initializeApp(this)

        // ✅ THÊM: Cấu hình Google Sign-In (sử dụng default_web_client_id trong strings.xml)
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        enableEdgeToEdge()
        setContent {
            BaiTapTuan5_FireBaseLoginTheme {
                Scaffold(modifier = Modifier.windowInsetsPadding(WindowInsets.safeDrawing)) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        val vm: UserViewModel = viewModel()
                        // ✅ THÊM: truyền googleSignInClient xuống Navigation

                        Navigation(vm, googleSignInClient)
                    }
                }
            }
        }
    }
}

//@Composable
//fun AppStart(vm: UserViewModel, googleSignInClient: GoogleSignInClient) {
//    // LaunchedEffect chỉ chạy 1 lần khi Composable được tạo
//    LaunchedEffect(Unit) {
//        vm.checkLoginStatus()
//    }
//
//    Navigation(vm, googleSignInClient)
//}
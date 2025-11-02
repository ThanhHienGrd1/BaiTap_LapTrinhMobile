package com.example.baitaptuan5_firebaselogin.ui.screen

import android.app.Activity
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.baitaptuan5_firebaselogin.R
import com.example.baitaptuan5_firebaselogin.viewmodel.UserViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.api.ApiException
import kotlinx.coroutines.delay


@Composable
fun Login_Screen(vm: UserViewModel = viewModel(), googleSignInClient: GoogleSignInClient? = null) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val mess by vm.message.collectAsState()
    val context = LocalContext.current
    val activity = context as? Activity

    // ✅ THÊM: launcher để nhận kết quả từ Google Sign-In intent
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        try {
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            val account = task.getResult(ApiException::class.java)
            // account.idToken có thể null trong một số cấu hình; kiểm tra trước khi gửi lên ViewModel
            account?.idToken?.let { idToken ->
                // gọi viewmodel xử lý login với idToken (Firebase)
                vm.loginWithGoogle(idToken)
            } ?: run {
                vm.clearmessage()
                vm.setMessage("Đăng nhập Google thất bại: idToken null")
            }
        } catch (e: Exception) {
            Log.e("Login_Screen", "Google sign-in failed", e)
            vm.clearmessage()
            vm.setMessage("Đăng nhập Google thất bại")
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFC9F0FF)),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(Modifier.height(20.dp))
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(R.drawable.logo_login),
                contentDescription = "logo_login",
                modifier = Modifier.size(170.dp)
            )
        } // box 1

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .padding(bottom = 20.dp)
                .background(Color(0xFF9DCFE3), shape = RoundedCornerShape(20.dp))
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .padding(top = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "Đăng Nhập",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(Modifier.height(28.dp))
                        TextField_Custom("Email", email, onChange = { email = it })
                        Spacer(Modifier.height(16.dp))
                        TextField_Custom("Mật khẩu", password, onChange = { password = it })
                    }
                }

                Spacer(Modifier.height(28.dp))
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {

                        if (mess != null) {
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(text = mess!!, color = Color.Red, fontSize = 16.sp)
                            LaunchedEffect(mess) {
                                delay(3000L)
                                vm.clearmessage()
                            }
                            Spacer(modifier = Modifier.height(12.dp))
                        }

                        Button(
                            onClick = {
                                vm.login(email, password)
                            },
                            modifier = Modifier
                                .fillMaxWidth(0.9f)
                                .height(56.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFF36435)
                            ),
                            shape = RoundedCornerShape(15.dp)
                        ) {
                            Text(
                                text = "Đăng Nhập",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        Spacer(Modifier.height(20.dp))
                        HorizontalDivider(
                            color = Color.Gray,
                            thickness = 2.dp,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )
                        Text(text = "Hoặc", fontSize = 16.sp, fontWeight = FontWeight.Medium)
                        Spacer(Modifier.height(4.dp))

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(52.dp)
                        ) {
                            // 🔹 CHỈ THÊM clickable ở đây — giữ nguyên kích thước, hình ảnh
                            Image(
                                painter = painterResource(R.drawable.logo_google),
                                contentDescription = "logo_google",
                                modifier = Modifier
                                    .size(40.dp)
                                    .clip(CircleShape)
                                    .border(1.dp, Color.Gray, CircleShape)
                                    .clickable {
                                        // chỉ bật khi googleSignInClient != null (khi chạy thật)
                                        googleSignInClient?.signOut()?.addOnCompleteListener { client ->
                                            val signInIntent = googleSignInClient.signInIntent
                                            launcher.launch(signInIntent)
                                        } ?: run {
                                            // preview / dev: show message
                                            vm.setMessage("GoogleSignInClient chưa được cấu hình.")
                                        }
                                    }
                            )

                            Image(
                                painter = painterResource(R.drawable.logo_facebook),
                                contentDescription = "logo_facebook",
                                modifier = Modifier.size(47.dp)
                            )
                        }

                        Spacer(Modifier.height(16.dp))
                        Row {
                            Text("Bạn chưa có tài khoản? ")
                            Text(
                                "Đăng ký",
                                color = Color(0xFF21817B),
                                textDecoration = TextDecoration.Underline
                            )
                        }
                    } // column box 3
                } // box 3
            }
        } // main box
    } // main column
}


@Composable
fun TextField_Custom(label: String, value: String, onChange: (String) -> Unit) {
    val checkIconPass = label.contains("Mật khẩu", ignoreCase = true)
    var passwordVisible by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = value,
        onValueChange = onChange,
        shape = RoundedCornerShape(15.dp),
        placeholder = {
            Text(
                text = label,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth(0.9f)
            )
        },
        leadingIcon = {
            if (label.contains("Email", ignoreCase = true)) {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = "Icon_Email",
                    modifier = Modifier.size(23.dp)
                )
            } else {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = "Icon_Lock",
                    modifier = Modifier.size(23.dp)

                )
            }
        },
        trailingIcon = {

            if (checkIconPass) {
                val iconPainter = if (passwordVisible) {
                    painterResource(id = R.drawable.visibility_on)
                } else {
                    painterResource(id = R.drawable.visibility_off)
                }

                IconButton(onClick = { passwordVisible = !passwordVisible }
                ) {
                    Icon(
                        painter = iconPainter,
                        contentDescription = "Icon_Visible",
                        Modifier.size(20.dp)
                    )
                    if (passwordVisible == true)
                        LaunchedEffect(passwordVisible) {
                            delay(3000)
                            passwordVisible = false
                        }
                }

            }
        },
        modifier = Modifier
            .height(56.dp),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.White,
            focusedContainerColor = Color.LightGray,
        ),
        singleLine = true,
        visualTransformation = if (checkIconPass && !passwordVisible) {
            PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        }

    )
}
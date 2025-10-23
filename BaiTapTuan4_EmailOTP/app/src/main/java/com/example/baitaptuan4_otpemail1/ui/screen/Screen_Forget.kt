package com.example.baitaptuan4_otpemail1.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.baitaptuan4_otpemail1.R
import com.example.baitaptuan4_otpemail1.ui.viewmodel.UserViewModel

@Composable
fun Screen_Forget(navController: NavController,vm : UserViewModel){
    var email by remember { mutableStateOf(TextFieldValue(vm.getEmail())) }
    val user by vm.user.collectAsState()
    var thong_bao by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(40.dp))

        // 🖼️ Logo
        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = "Logo_UTH",
            modifier = Modifier.size(120.dp)
        )

        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "SmartTasks",
            fontSize = 24.sp,
            color = Color.Blue,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(28.dp))
        Text(
            text = "Forget Password?",
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Enter your Email, we will send you a verification code.",
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.LightGray,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(24.dp))

        //  nhap mail
        OutlinedTextField(
            value = email,
            onValueChange = {
                thong_bao = null
                email = it
            },
            placeholder = { Text("Your Email...", color = Color.LightGray) },
            shape = RoundedCornerShape(10.dp),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = "icon_email",
                    tint = Color.LightGray
                )
            },
            modifier = Modifier.fillMaxWidth(0.9f),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(8.dp))

        // mess loi
        if (thong_bao != null) {
            Text(
                text = thong_bao!!,
                fontSize = 16.sp,
                color = Color.Red
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // next
        Button(
            onClick = {
                if (email.text.isNotEmpty()) {
                    vm.setEmail(email.text)
                    navController.navigate("verify")
                } else {
                    thong_bao = "Vui lòng nhập Email!"
                }
            },
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .height(45.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Blue),
            shape = RoundedCornerShape(25.dp)
        ) {
            Text("Next", fontSize = 16.sp, color = Color.White)
        }

        // show user
        Spacer(modifier = Modifier.height(30.dp))

        Spacer(modifier = Modifier.height(30.dp))
        if(user.email.isNotEmpty()&&user.password.isNotEmpty()){
            Row (
                horizontalArrangement = Arrangement.spacedBy( 8.dp, Alignment.CenterHorizontally)
            ){
                Text("Email:", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Text( "${user.email}", fontSize = 20.sp)
            }

            Spacer(modifier = Modifier.height(8.dp))
            Row (
                horizontalArrangement = Arrangement.spacedBy( 8.dp, Alignment.CenterHorizontally)
            ){
                Text("Password:", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Text( "${user.password}", fontSize = 20.sp)
            }

            Spacer(modifier = Modifier.height(8.dp))
            Row (
                horizontalArrangement = Arrangement.spacedBy( 8.dp, Alignment.CenterHorizontally)
            ){
                Text("OTP:", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Text( "${user.otp}", fontSize = 20.sp)
            }
        }
    }

}
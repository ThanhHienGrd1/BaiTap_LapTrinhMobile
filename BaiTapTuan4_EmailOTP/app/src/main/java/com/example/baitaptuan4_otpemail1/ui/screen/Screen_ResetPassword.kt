package com.example.baitaptuan4_otpemail1.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.baitaptuan4_otpemail1.R
import com.example.baitaptuan4_otpemail1.ui.viewmodel.UserViewModel

@Composable
fun Screen_ResetPassword(navController: NavController, vm : UserViewModel){

    var password by remember { mutableStateOf(TextFieldValue("")) }
    var confirmPassword by remember { mutableStateOf(TextFieldValue("")) }
    var icon_passwordcheck by remember { mutableStateOf(false) }
    var thong_bao by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, top = 10.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            Image(
                painter = painterResource(R.drawable.back_icon),
                contentDescription = "Icon",
                modifier = Modifier
                    .size(25.dp)
                    .clickable { navController.popBackStack() }
            )
        }

        Spacer(modifier = Modifier.height(40.dp))
        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = "Logo_UTH", modifier = Modifier.size(120.dp)
        ) // image

        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "SmartTasks", fontSize = 24.sp, color = Color.Blue, fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(28.dp))
        Text(
            text = "Create new password", fontSize = 20.sp, fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Your new password must be different form previously used password",
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.LightGray,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(24.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            placeholder = { Text("Password...", color = Color.LightGray) },
            shape = RoundedCornerShape(10.dp),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = "icon_lock",
                    modifier = Modifier
                        .size(20.dp)
                )
            },

            visualTransformation = if (icon_passwordcheck) VisualTransformation.None
            else PasswordVisualTransformation(),
            trailingIcon = {
                val icon_check = if (icon_passwordcheck) {
                    painterResource(id = R.drawable.visibility_on)
                } else {
                    painterResource(id = R.drawable.visibility_off)
                }
                IconButton(onClick = { icon_passwordcheck = !icon_passwordcheck }
                ) {
                    Icon(
                        painter = icon_check,
                        contentDescription = "icon_vis",
                        modifier = Modifier
                            .size(20.dp)
                    )
                }
            }, modifier = Modifier
                .fillMaxWidth(0.9f)
        )

        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = confirmPassword,
            onValueChange = {
                confirmPassword = it; thong_bao = null
            },
            placeholder = { Text("Confirm Password...", color = Color.LightGray) },
            shape = RoundedCornerShape(10.dp),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = "icon_lock",
                    modifier = Modifier
                        .size(20.dp)
                )
            },

            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth(0.9f)
        )
        Spacer(modifier = Modifier.height(8.dp))
        if (thong_bao != null) {
            Text(
                text = thong_bao!!,
                fontSize = 16.sp,
                color = Color.Red
            )
        }

        Spacer(modifier = Modifier.height(28.dp))

        Button(
            onClick = {
                if (password.text == confirmPassword.text) {
                    vm.setPassword(confirmPassword.text)
                    navController.navigate("confirm")
                } else {
                    thong_bao = "Sai mật khẩu, vui lòng nhập lại!"
                }

            }, modifier = Modifier
                .fillMaxWidth(0.9f)
                .height(45.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Blue
            ),
            shape = RoundedCornerShape(25.dp)
        )
        {
            Text("Next", fontSize = 16.sp, color = Color.White)
        }

    } // main column




}
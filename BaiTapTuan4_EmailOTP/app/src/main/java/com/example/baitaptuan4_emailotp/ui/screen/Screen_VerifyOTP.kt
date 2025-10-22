package com.example.baitaptuan4_emailotp.ui.screen

import android.R.attr.singleLine
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.baitaptuan4_emailotp.R
import com.example.baitaptuan4_emailotp.ui.viewmodel.UserViewModel

@Composable
fun VerifyOTP(navController: NavController, vm: UserViewModel) {
    val OTP = "12345"
    val otpList_text = remember { List(5) { mutableStateOf(TextFieldValue("")) } }
    val focusReques = List(5) { FocusRequester() }
    val focusManager = LocalFocusManager.current
    var thong_bao by remember { mutableStateOf<String?>(null) }
    LaunchedEffect(Unit) {
        focusReques[0].requestFocus()
    }

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
        Spacer(modifier = Modifier.height(50.dp))
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
            text = "Verify Code", fontSize = 20.sp, fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Enter the code, we just sent you on your registerd Email", fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold, color = Color.LightGray, textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(24.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            otpList_text.forEachIndexed { index, value ->
                OutlinedTextField(
                    value = value.value,
                    onValueChange = { newValue ->

                        thong_bao = null;
                        if (newValue.text.length <= 1 && newValue.text.all { it.isDigit() }) {
                            value.value = newValue.copy(
                                text = newValue.text,
                                selection = TextRange(newValue.text.length)
                            )
                            if (newValue.text.isNotEmpty()) {
                                if (index < 4) {
                                    focusReques[index + 1].requestFocus()
                                } else {
                                    focusManager.clearFocus()
                                }
                            }
                        }
                    },
                    modifier = Modifier
                        .width(50.dp)
                        .height(50.dp)
                        .focusRequester(focusReques[index]),
                    textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
                    singleLine = true,
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.LightGray
                    ),
                    shape = RoundedCornerShape(10.dp),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    )

                )

            }
        }
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
                val OTP_input = otpList_text.joinToString("") { it.value.text }
                if (OTP == OTP_input) {
                    vm.setOTP(OTP)
                    navController.navigate("reset")
                } else {
                    thong_bao = "Sai mã OTP !"
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

    }
}
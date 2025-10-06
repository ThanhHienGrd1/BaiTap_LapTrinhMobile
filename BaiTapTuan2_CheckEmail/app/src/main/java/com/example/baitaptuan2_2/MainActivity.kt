package com.example.baitaptuan2_2

import android.R
import android.R.attr.height
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.baitaptuan2_2.ui.theme.BaiTapTuan22Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BaiTapTuan22Theme {
                Scaffold(modifier = Modifier.windowInsetsPadding(WindowInsets.safeDrawing)) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        //
                        BaiTapTuan2_2()
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun BaiTapTuan2_2(){
    var input_email by remember { mutableStateOf("") }
    var thongbao by remember {mutableStateOf<String?>(null)}
Column(
    modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center
) {
    Text(
        text = "Thực Hành 02",
        fontSize = 18.sp,
        color = Color.Black,
        fontWeight = FontWeight.Bold
    )
    Spacer( modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = input_email,
            onValueChange = {input_email = it},
            placeholder = {
                Text(
                    text = "Nhập email ",
                    color=Color.Gray.copy(alpha=0.6f)
                )
            },
            singleLine = true,
            shape = RoundedCornerShape(10.dp),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White

            ),
            modifier = Modifier
                .fillMaxWidth()
                .border(1.6.dp,Color.Black, shape = RoundedCornerShape(10.dp))
        )//textfield
        Spacer (modifier = Modifier.height(10.dp))

    if (thongbao!=null){
        Text(
            text = thongbao!!,
            color = Color.Red,
            fontSize = 14.sp
        )
    }
    Spacer(modifier = Modifier.height(6.dp))
    Button(onClick =  {
        val email = input_email
        val isInputEmail = input_email.contains("@")
        if (isInputEmail){
                thongbao =null

        }else{
            thongbao="Email không đúng định dạng!"
        }
    }, modifier = Modifier
        .fillMaxWidth()
        .height(50.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF1E88E5),
            contentColor = Color.White
        ),
        shape = RoundedCornerShape(20.dp)
    ){
        Text(
            text = "Kiểm tra",
            color=Color.White,

        )
    }




}// column

}//funtion



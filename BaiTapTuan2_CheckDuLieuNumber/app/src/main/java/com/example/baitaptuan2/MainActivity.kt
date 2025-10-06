package com.example.baitaptuan2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.baitaptuan2.ui.theme.BaiTapTuan2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BaiTapTuan2Theme {
                Scaffold(modifier = Modifier.windowInsetsPadding(WindowInsets.safeDrawing)) {innerPadding->
                Column(modifier = Modifier.padding(innerPadding)) {
                    ThucHanh02()
                    }

                }

            }
        }
    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ThucHanh02(){
    var input by remember { mutableStateOf("") }
    var number by remember { mutableStateOf(0) }
    var thongbao by remember {mutableStateOf<String?>(null)}
    val scrollState = rememberScrollState()
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center


    ){
        // text thuc hanh
        Text(
            text = "Thực Hành 02 ",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
        Spacer(modifier=Modifier.height(20.dp))
Row(
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.spacedBy(8.dp)
){
    OutlinedTextField(
        value = input,
        onValueChange = { input = it },
        placeholder = {
            Text(
                text = "Nhập vào số lượng",
                color = Color.Gray.copy(alpha = 0.6f)
            )
        },
        singleLine = true,
        shape = RoundedCornerShape(15.dp), // bo góc
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,

            ),
        modifier = Modifier
            .width(230.dp)
            .border(1.8.dp, Color.Black, RoundedCornerShape(20.dp))

    )

    Button(onClick = {
        val num = input.toIntOrNull()
        if (num == null) {
            number = 0
            thongbao = "Sai dữ liệu, vui lòng nhập lại"

        }else if(num<=0){
            number = 0
            thongbao="Hãy nhập số nguyên dương (>0)"
        }
        else {
            thongbao = null
            number = num
        }
    },
        modifier = Modifier
            .height(48.dp)
            .width(150.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF1E88E5),
            contentColor = Color.White
        ),
        shape = RoundedCornerShape(12.dp)

    ) {
        Text(
            text="Tạo",
            fontSize = 14.sp,
            color = Color.Black,
            )
    }
}

if (thongbao !=null ){
    Spacer(modifier = Modifier.padding(top=10.dp))
    Text(
        text= thongbao !!,
        color = Color.Red,
        fontWeight = FontWeight.Bold
    )
}

   Spacer(modifier = Modifier.height(16.dp))

for (i in 1..number){
    Box(

        modifier = Modifier

            .padding(vertical = 10.dp)
            .height(40.dp)
            .width(300.dp)
            .background(Color.Red, shape = RoundedCornerShape(20.dp)),
        contentAlignment = Alignment.Center



    ){
        Text(
            text = "$i",
            fontSize = 16.sp,
            color= Color.White,
            fontWeight = FontWeight.Bold,

            )
    }

        }


    }// cua column





} // cua funtion





@Preview (showBackground = true, showSystemUi = true)
@Composable
fun Ovongfor(){
    Column(
        modifier = Modifier
            .fillMaxSize()
    ,
      verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Box(

            modifier = Modifier
                .padding(vertical = 14.dp)
                .height(40.dp)
                .width(300.dp)
                .background(Color.Red, shape = RoundedCornerShape(20.dp)),
            contentAlignment = Alignment.Center


        ) {
            Text(
                text = "1",
                fontSize = 16.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,

                )
        }

    }
}
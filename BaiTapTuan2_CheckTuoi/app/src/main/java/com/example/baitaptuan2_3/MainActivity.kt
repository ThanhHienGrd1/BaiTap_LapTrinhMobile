package com.example.baitaptuan2_3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.baitaptuan2_3.ui.theme.BaiTapTuan2_3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BaiTapTuan2_3Theme {
                Scaffold(modifier = Modifier.windowInsetsPadding(WindowInsets.safeDrawing)) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        //
                        BaiTapTuan2_3()
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun BaiTapTuan2_3(){
    var HovaTen by remember { mutableStateOf("") }
    var Tuoi by remember {mutableStateOf("")}
    var thongbao by remember {mutableStateOf<String?>(null)}

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(6.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "THỰC HÀNH 01",
            color = Color.Black,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .border(1.6.dp, Color.Black, shape = RoundedCornerShape(4.dp))
                .background(Color(0xFFDDDDDD))
                .padding(30.dp)


        ){
            FormRow("Họ và tên", HovaTen, { HovaTen = it })
            Spacer(modifier = Modifier.height(12.dp))
            FormRow("Tuổi", Tuoi, { Tuoi = it })

        }
        Spacer(modifier = Modifier.height(16.dp))
        //thong bao
        if (thongbao!=null){
            Text(
                text = thongbao!!,
                color = Color.Red,
                fontSize = 14.sp
            )
        }
//button kiem tra
        Button(onClick = {
            val hoten = HovaTen
            val tuoi = Tuoi.toIntOrNull()
            if (hoten.isBlank() ){
                thongbao= "Vui lòng nhập Họ và Tên!"
            }else if (tuoi==null || tuoi<0){
                thongbao = "Nhập lại tuổi !"
            }else {
                thongbao = null
                if (tuoi >65) {
                        thongbao = "Người già !"
                } else if(tuoi in 7..65){
                        thongbao = "Người lớn !"
                }else if(tuoi in 2..6)
                {
                        thongbao = "Trẻ em !"
                } else if (tuoi<2){
                        thongbao = "Em bé !"
                }
            }
        }, modifier = Modifier
            .width(250.dp)
            .height(60.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF1E90FF)
            ),
            shape=RoundedCornerShape(15.dp)
        ){
            Text (
                text = "Kiểm tra",
                fontSize = 20.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }



    }
}
@Composable
fun FormRow(label: String, value: String, onChange: (String) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = label,
            modifier = Modifier.width(80.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
        )
        OutlinedTextField(
            value = value,
            onValueChange = onChange,
            shape = RoundedCornerShape(14.dp),
            modifier = Modifier
                .height(50.dp),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.White,
                unfocusedIndicatorColor = Color.Blue,
            )
        )
    }
}


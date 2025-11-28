package com.example.whitespace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.overscroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.whitespace.ui.theme.WhiteSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WhiteSpaceTheme {
                Scaffold(modifier = Modifier.windowInsetsPadding(WindowInsets.safeDrawing)) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        examp1()
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun examp1() {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp)
            .verticalScroll(scrollState)
        ,
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {


        Spacer(modifier = Modifier.height(4.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.icon1), contentDescription = "icon",
                modifier = Modifier
                    .size(35.dp)
            )
            Text("Tasking", fontSize = 35.sp, fontWeight = FontWeight.Bold, color = Color.Red, )
        } //rowTasking


        HorizontalDivider(
            color = Color.LightGray,
            thickness = 1.dp,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 32.dp)
        )
//--------------------------------------------------------------------------------------------------------------------------------------
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFF1F1FB), shape = RoundedCornerShape(15.dp))
                .padding(horizontal = 20.dp, vertical = 24.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start)
        ) {
            Icon(
                painter = painterResource(R.drawable.icon6), contentDescription = "icon",
                modifier = Modifier
                    .size(24.dp),
                tint = Color(0xFF5D5DD3)
            )
            Text(
                "Dashboard",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF5D5DD3),
            )
        }// rowDashBoard

//--------------------------------------------------------------------------------------------------------------------------------------
        Text(
            "ANALYTICS",
            color = Color.LightGray,
            fontSize = 16.sp,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier
                .padding(vertical = 28.dp, horizontal = 16.dp)
                .align(Alignment.Start)
        )

        Icon_row("Performance", R.drawable.icon2, 24, 24)
        Spacer(modifier = Modifier.height(20.dp))
        Icon_row("Mixpanel", R.drawable.icon3, 24, 24)

        HorizontalDivider(
            color = Color.LightGray,
            thickness = 2.dp,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 32.dp)
        )
//--------------------------------------------------------------------------------------------------------------------------------------
        Text(
            "SUPPORT", color = Color.LightGray, fontSize = 16.sp, fontWeight = FontWeight.ExtraBold,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .align(Alignment.Start)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Icon_row("Tickets", R.drawable.icon4, 24, 24)
        Spacer(modifier = Modifier.height(20.dp))
        Icon_row("Angents", R.drawable.icon5, 24, 24)
        Spacer(modifier = Modifier.height(20.dp))
        Icon_row("Customers", R.drawable.icon8, 24, 24)
        HorizontalDivider(
            color = Color.LightGray,
            thickness = 1.dp,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 32.dp)
        )
        //--------------------------------------------------------------------------------------------------------------------------------------
        Text(
            "SHOP", color = Color.LightGray, fontSize = 16.sp, fontWeight = FontWeight.ExtraBold,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .align(Alignment.Start)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Icon_row("Products", R.drawable.icon9, 24, 24)
        Spacer(modifier = Modifier.height(20.dp))
        Icon_row("Order", R.drawable.icon7, 24, 24)
//////////////////////////////////
        HorizontalDivider(
            color = Color.LightGray,
            thickness = 2.dp,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 32.dp)
        )
        Text(
            "SHOP", color = Color.LightGray, fontSize = 16.sp, fontWeight = FontWeight.ExtraBold,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .align(Alignment.Start)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Icon_row("Products", R.drawable.icon9, 24, 24)
        Spacer(modifier = Modifier.height(20.dp))
        Icon_row("Order", R.drawable.icon7, 24, 24)
    }
}

@Composable
fun Icon_row(text: String, idPaineter: Int, sizeIcon: Int, sizeText: Int) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Icon(
                painter = painterResource(idPaineter), contentDescription = "icon",
                modifier = Modifier.size(sizeIcon.dp)
            )
            Text(
                text = text,
                fontSize = sizeText.sp
            )
        }


}
package com.example.baitaptuan3.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.baitaptuan3.R


@Composable
fun Screen_Box(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {

            Image(
                painter = painterResource(R.drawable.back_icon),
                contentDescription = "Icon",
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .size(20.dp)
                    .clickable { navController.popBackStack() }
            )
            Text(
                "Box layout",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF87C9E1),
                modifier = Modifier
                    .align(Alignment.Center)

            )

        }





        Spacer(modifier = Modifier.height(100.dp))
        Box(

        ) {
            Image(
                painter = painterResource(id = R.drawable.avt1),
                contentDescription = "avt1",
                modifier = Modifier
                    .clip(CircleShape)
                    .size(200.dp)
            )
            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(end = 38.dp, bottom = 33.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.icon_check),
                    contentDescription = "icon",
                    modifier = Modifier
                        .size(20.dp)
                )
            }

        } // box1
        Spacer(modifier = Modifier.height(50.dp))
        Box(

        ) {
            Image(
                painter = painterResource(id = R.drawable.avt2),
                contentDescription = "avt1",
                modifier = Modifier
                    .clip(CircleShape)
                    .size(200.dp)
            )
            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(end = 38.dp, bottom = 30.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.icon_check),
                    contentDescription = "icon",
                    modifier = Modifier
                        .size(20.dp)
                )
            }
        }// box 2
    }
}
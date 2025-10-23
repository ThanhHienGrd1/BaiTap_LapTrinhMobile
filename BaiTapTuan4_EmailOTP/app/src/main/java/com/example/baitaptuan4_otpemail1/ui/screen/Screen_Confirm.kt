package com.example.baitaptuan4_otpemail1.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.baitaptuan4_otpemail1.R
import com.example.baitaptuan4_otpemail1.ui.viewmodel.UserViewModel

@Composable
fun Screen_Confirm(navController: NavController, vm: UserViewModel){
    val user by vm.user.collectAsState()

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
        Spacer(modifier = Modifier.height(16.dp))
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
            text = "Confirm", fontSize = 20.sp, fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "We are here to help you!", fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold, color = Color.LightGray, textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .background(Color(0xFFECE9E9), shape= RoundedCornerShape(10.dp))
                .padding(15.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)

        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "icon",
                modifier = Modifier
                    .size(35.dp)

            )
            Text("${user.email}", fontSize = 16.sp)

        }

        Spacer(modifier = Modifier.height(12.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .background(Color(0xFFECE9E9), shape= RoundedCornerShape(10.dp))
                .padding(15.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Icon(
                imageVector = Icons.Default.Email,
                contentDescription = "icon",
                modifier = Modifier
                    .size(35.dp)

            )
            Text("${user.otp}", fontSize = 16.sp)

        }

        Spacer(modifier = Modifier.height(12.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .background(Color(0xFFECE9E9),shape= RoundedCornerShape(10.dp))
                .padding(15.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)

        ) {
            Icon(
                imageVector = Icons.Default.Lock,
                contentDescription = "icon",
                modifier = Modifier
                    .size(35.dp)

            )
            Text("${user.password}", fontSize = 16.sp)

        }


        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                navController.navigate("forget")

            }, modifier = Modifier
                .fillMaxWidth(0.9f)
                .height(45.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Blue
            ),
            shape = RoundedCornerShape(25.dp)
        )
        {
            Text("Summit", fontSize = 16.sp, color = Color.White)
        }


    } // main column

}
package com.example.tuan1_baitap
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tuan1_baitap.ui.theme.Tuan1_baitapTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Tuan1_baitapTheme {
                Scaffold(
                    modifier = Modifier.windowInsetsPadding(WindowInsets.safeDrawing) // tự động chừa chỗ cho status bar và navigation bar
                ) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        ProfileItem()
                        ProfileScreen()
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}
@Composable
fun ProfileScreen(){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ){
        Column(horizontalAlignment = Alignment.CenterHorizontally){
            // avatar
            Image(
                painter = painterResource(id = R.drawable.pic2),
                contentDescription = "Profile picture",
                modifier = Modifier
                    .size(180.dp)
                    .clip(CircleShape)
                    .border(2.dp, Color.Blue, CircleShape)
            )
            Spacer(modifier = Modifier.height(16.dp))
            // ten hien thi
            Text (
                text = "Thanh Hien",
                fontSize =25.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            // dia chi cu tru
            Text (
                text = "TP.HCM, VN",
                fontSize = 16.sp,
                color = Color.Gray
            )
        }
    }
}

@Composable
fun ProfileItem(modifier: Modifier = Modifier) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // arrow ben trai
        Box(
            modifier = Modifier
                .size(40.dp) // Kích thước ô vuông
                .border(1.dp, Color.Gray, shape = RoundedCornerShape(8.dp)) // Viền vuông (bo nhẹ 8dp)
                .padding(8.dp), // Padding để icon không sát mép
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack, // Mũi tên quay về
                contentDescription = "Back",
                tint = Color.Black
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        // icon ben phai
        Box(
            modifier = Modifier
                .size(40.dp)
                .border(1.dp, Color.Gray, shape = RoundedCornerShape(8.dp))
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Profile",
                tint = Color.Black
            )
        }
    }
}







@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Tuan1_baitapTheme {
        Greeting("Android")
    }
}
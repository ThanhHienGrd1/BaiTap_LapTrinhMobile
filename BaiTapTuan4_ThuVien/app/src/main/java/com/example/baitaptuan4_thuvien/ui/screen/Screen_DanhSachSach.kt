package com.example.baitaptuan4_thuvien.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.baitaptuan4_thuvien.R
import com.example.baitaptuan4_thuvien.model.Sach
import com.example.baitaptuan4_thuvien.viewmodel.LibraryViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun Screen_DanhSachSach(navController: NavController, vm: LibraryViewModel) {
    val dsSach by vm.danhSachSach.collectAsState()
    var tenSachMoi by remember { mutableStateOf("") }
    var thong_bao by remember { mutableStateOf<String?>(null) }
    var sachDangChon by remember { mutableStateOf<Sach?>(null) } // sách đang chọn để xóa
    var showDialog by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val scrollState = rememberScrollState()
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp),
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {

            Text(
                text = "Hệ Thống\nQuản Lý Thư Viện",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(70.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = tenSachMoi,
                    onValueChange = { tenSachMoi = it },
                    placeholder = { Text("Nhập tên sách...", color = Color.LightGray) },
                    shape = RoundedCornerShape(15.dp),
                    modifier = Modifier.weight(1f),
                    singleLine = true
                )
                Button(
                    onClick = {
                        if (tenSachMoi.isNotBlank()) {
                            val newSach = Sach(1, tenSachMoi)
                            vm.themSach(newSach)
                            tenSachMoi = ""
                            thong_bao = null
                        } else {
                            thong_bao = "Vui lòng nhập tên sách !"
                            scope.launch {
                                delay(2000) // 2 giây
                                thong_bao = "" // tự động ẩn thông báo
                            }
                        }
                    },
                    modifier = Modifier.height(56.dp),
                    shape = RoundedCornerShape(15.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1E88E5))
                ) {
                    Text("Thêm Sách")
                }
            }
            Spacer(modifier = Modifier.height(8.dp))

            if (thong_bao != null) {
                Text(
                    text = thong_bao!!,
                    color = Color.Red,
                    fontSize = 16.sp
                )
            }

            Spacer(modifier = Modifier.height(30.dp))
            Text(
                "Danh sách sách:",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Start)
            )
            Spacer(modifier = Modifier.height(20.dp))

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(6.dp),
                modifier = Modifier.heightIn(max = 350.dp)
            ) {
                items(dsSach.size) { index ->
                    val sach = dsSach[index]
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                sachDangChon = if (sachDangChon == sach) null else sach
                            },
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = if (sachDangChon == sach) Color(0xFFFFCDD2) else Color(
                                0xFFB2D2D0
                            )
                        )
                    ) {
                        Text(
                            sach.tenSach,
                            modifier = Modifier.padding(12.dp),
                            fontSize = 20.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            if (sachDangChon != null) {
                Button(
                    onClick = { showDialog = true },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Xóa sách đã chọn", color = Color.White)
                }
            }


            if (showDialog && sachDangChon != null) {
                androidx.compose.material3.AlertDialog(
                    onDismissRequest = { showDialog = false },
                    title = { Text("Xác nhận xóa") },
                    text = { Text("Bạn có chắc muốn xóa sách \"${sachDangChon!!.tenSach}\" không?") },
                    confirmButton = {
                        Button(onClick = {
                            vm.xoaSach(sachDangChon!!)
                            sachDangChon = null
                            showDialog = false
                            thong_bao = "✅ Xóa sách thành công!"
                            scope.launch {
                                delay(2000)
                                thong_bao = null
                            }
                        }) { Text("Xóa") }
                    },
                    dismissButton = {
                        Button(onClick = { showDialog = false }) { Text("Không") }
                    }
                )
            }
        }
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 20.dp)
        ) {

            Divider(
                thickness = 2.dp,
                color = Color.LightGray,
                modifier = Modifier.padding(bottom = 10.dp)

            )
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Icon(
                    painter = painterResource(R.drawable.quanly_icon),
                    contentDescription = "icon",
                    modifier = Modifier
                        .size(40.dp)
                        .clickable { navController.navigate("quanly") },
                )
                Icon(
                    painter = painterResource(R.drawable.listsach_icon),
                    contentDescription = "icon",
                    modifier = Modifier
                        .size(40.dp),
                    tint = Color.Blue
                )
                Icon(
                    painter = painterResource(R.drawable.sinhvien_icon),
                    contentDescription = "icon",
                    modifier = Modifier
                        .size(40.dp)
                        .clickable { navController.navigate("danhsachsinhvien") },

                    )
            }

        }

    }
}

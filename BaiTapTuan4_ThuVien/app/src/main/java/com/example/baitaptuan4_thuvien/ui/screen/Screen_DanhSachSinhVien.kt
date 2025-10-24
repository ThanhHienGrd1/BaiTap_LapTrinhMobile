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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
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
import com.example.baitaptuan4_thuvien.model.SinhVien
import com.example.baitaptuan4_thuvien.viewmodel.LibraryViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun Screen_DanhSachSinhVien(navController: NavController, vm: LibraryViewModel) {
    val dsSinhVien by vm.danhSachSinhVien.collectAsState()
    var tenSinhVienMoi by remember { mutableStateOf("") }
    var thongBao by remember { mutableStateOf<String?>(null) }
    var sinhVienDangChon by remember { mutableStateOf<SinhVien?>(null) }
    var showDialog by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val scrollState = rememberScrollState()

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Hệ Thống\nQuản Lý Thư Viện",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(70.dp))

            // Row nhập sinh viên mới
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = tenSinhVienMoi,
                    onValueChange = { tenSinhVienMoi = it },
                    placeholder = { Text("Nhập tên sinh viên...", color = Color.LightGray) },
                    shape = RoundedCornerShape(15.dp),
                    modifier = Modifier.width(200.dp),
                    singleLine = true
                )
                Button(
                    onClick = {
                        if (tenSinhVienMoi.isNotBlank()) {
                            val newSV = SinhVien(1, tenSinhVienMoi)
                            vm.themSinhVien(newSV)
                            tenSinhVienMoi = ""
                            thongBao = "✅ Thêm sinh viên thành công!"
                            scope.launch {
                                delay(2000)
                                thongBao = null
                            }
                        } else {
                            thongBao = "Vui lòng nhập tên sinh viên!"
                            scope.launch {
                                delay(2000)
                                thongBao = null
                            }
                        }
                    },
                    modifier = Modifier.height(56.dp),
                    shape = RoundedCornerShape(15.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1E88E5))
                ) {
                    Text("Thêm Sinh Viên",
                        textAlign = TextAlign.Center)
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Thông báo
            if (thongBao != null) {
                Text(
                    text = thongBao!!,
                    color = if (thongBao!!.startsWith("✅")) Color(0xFF2E7D32) else Color.Red,
                    fontSize = 16.sp
                )
            }

            Spacer(modifier = Modifier.height(30.dp))

            Text(
                "Danh sách sinh viên:",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Start)
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Danh sách sinh viên scrollable
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(6.dp)
                , modifier = Modifier.heightIn(max = 350.dp)
            ) {
                items(dsSinhVien.size) { index ->
                    val sv = dsSinhVien[index]
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                sinhVienDangChon = if (sinhVienDangChon == sv) null else sv
                            },
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = if (sinhVienDangChon == sv) Color(0xFFFFCDD2) else Color(
                                0xFFB2D2D0
                            )
                        )
                    ) {
                        Text(
                            sv.ten,
                            modifier = Modifier.padding(12.dp),
                            fontSize = 20.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Nút xóa
            if (sinhVienDangChon != null) {
                Button(
                    onClick = { showDialog = true },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Xóa sinh viên đã chọn", color = Color.White)
                }
            }

            // Dialog xác nhận xóa
            if (showDialog && sinhVienDangChon != null) {
                AlertDialog(
                    onDismissRequest = { showDialog = false },
                    title = { Text("Xác nhận xóa") },
                    text = { Text("Bạn có chắc muốn xóa sinh viên \"${sinhVienDangChon!!.ten}\" không?") },
                    confirmButton = {
                        Button(onClick = {
                            vm.xoaSinhVien(sinhVienDangChon!!)
                            sinhVienDangChon = null
                            showDialog = false
                            thongBao = "✅ Xóa sinh viên thành công!"
                            scope.launch {
                                delay(2000)
                                thongBao = null
                            }
                        }) { Text("Xóa") }
                    },
                    dismissButton = {
                        Button(onClick = { showDialog = false }) { Text("Không") }
                    }
                )
            }
        }

        // Footer icon
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 20.dp)
        ) {
            Divider(thickness = 2.dp, color = Color.LightGray, modifier = Modifier.fillMaxWidth())
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Icon(
                    painter = painterResource(R.drawable.quanly_icon),
                    contentDescription = "quanly",
                    modifier = Modifier
                        .size(40.dp)
                        .clickable { navController.navigate("quanly") }
                )
                Icon(
                    painter = painterResource(R.drawable.listsach_icon),
                    contentDescription = "listsach",
                    modifier = Modifier
                        .size(40.dp)
                        .clickable { navController.navigate("danhsachsach") },

                    )
                Icon(
                    painter = painterResource(R.drawable.sinhvien_icon),
                    contentDescription = "sinhvien",
                    modifier = Modifier.size(40.dp),
                    tint = Color.Blue
                )
            }
        }
    }
}

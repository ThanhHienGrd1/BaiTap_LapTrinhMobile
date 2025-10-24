package com.example.baitaptuan4_thuvien.ui.screen

import androidx.compose.foundation.background
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import com.example.baitaptuan4_thuvien.viewmodel.LibraryViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun Screen_QuanLy(navController: NavController, vm: LibraryViewModel) {
    var tenSV by remember { mutableStateOf("") }
    val svHienTai by vm.SinhVienHienTai.collectAsState()
    val dsSach by vm.danhSachSach.collectAsState()
    val scrollState = rememberScrollState()


    //
    var chonSachMode by remember { mutableStateOf(false) }
    var sachDaChon by remember { mutableStateOf<String?>(null) }
    var thongBao by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()
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

            Spacer(modifier = Modifier.height(50.dp))

            //
            Text("Sinh Viên", fontSize = 18.sp, modifier = Modifier.align(Alignment.Start))
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = tenSV,
                    onValueChange = { tenSV = it },
                    placeholder = { Text("Nhập tên sinh viên...", color = Color.LightGray) },
                    shape = RoundedCornerShape(15.dp),
                    modifier = Modifier.weight(1f),
                    singleLine = true
                )
                Button(
                    onClick = { vm.chonSinhVienTheoTen(tenSV) },
                    modifier = Modifier.height(56.dp),
                    shape = RoundedCornerShape(15.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1E88E5))
                ) {
                    Text("Thay đổi")
                }
            }

            Spacer(modifier = Modifier.height(30.dp))


            Text(
                "Danh sách sách",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Start)
            )
            Spacer(modifier = Modifier.height(8.dp))

            when {
                svHienTai == null -> {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp)
                            .background(Color(0xFFECECEC), RoundedCornerShape(10.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Sinh viên chưa có trong danh sách!", fontSize = 16.sp)
                    }
                }

                svHienTai!!.dsSachMuon.isEmpty() -> {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp)
                            .background(Color(0xFFECECEC), RoundedCornerShape(10.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            "Bạn chưa mượn quyển sách nào\nNhấn 'Thêm' để bắt đầu hành trình đọc sách!",
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                }

                else -> {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        svHienTai!!.dsSachMuon.forEach { sach ->
                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(15.dp),
                                colors = CardDefaults.cardColors(containerColor = Color(0xFFD7BFBF))
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(12.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.icon_tick),
                                        contentDescription = null,
                                        modifier = Modifier.size(28.dp)
                                    )
                                    Text(sach.tenSach, fontSize = 16.sp)
                                }
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(30.dp))


            if (chonSachMode) {
                Text(
                    "Chọn sách muốn thêm:",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.Start)
                )
                Spacer(Modifier.height(10.dp))
                Column(verticalArrangement = Arrangement.spacedBy(6.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = 300.dp)
                        .verticalScroll(scrollState)) {
                    dsSach.forEach { sach ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    if (sachDaChon == sach.tenSach) {
                                        sachDaChon = null
                                    } else {
                                        sachDaChon = sach.tenSach
                                    }
                                },
                            shape = RoundedCornerShape(12.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = if (sachDaChon == sach.tenSach)
                                    Color(0xFFD0E8FF)
                                else Color(0xFFF8F8F8)
                            )
                        ) {
                            Text(
                                sach.tenSach,
                                modifier = Modifier.padding(12.dp),
                                fontSize = 16.sp,
                                color = if (sachDaChon == sach.tenSach) Color.Blue else Color.Black
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    when {
                        !chonSachMode -> {
                            chonSachMode = true
                        }

                        chonSachMode && sachDaChon == null -> {
                            chonSachMode = false
                        }

                        chonSachMode && sachDaChon != null -> {
                            val sach = dsSach.find { it.tenSach == sachDaChon }
                            if (sach != null && svHienTai != null) {
                                vm.muonSach(sach)
                                chonSachMode = false
                                sachDaChon = null
                                thongBao = "✅ Thêm sách thành công!"
                                scope.launch {
                                    delay(2000)
                                    thongBao = ""
                                }
                            }
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(15.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1E88E5))
            ) {
                Text(
                    text = when {
                        !chonSachMode -> "Thêm sách"
                        chonSachMode && sachDaChon == null -> "Thoát chọn"
                        else -> "Xác nhận thêm"
                    },
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                )
            }


            if (thongBao.isNotEmpty()) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(thongBao, color = Color(0xFF2E7D32), fontWeight = FontWeight.Bold)
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
                        .size(40.dp),
                    tint = Color.Blue

                )
                Icon(
                    painter = painterResource(R.drawable.listsach_icon),
                    contentDescription = "icon",
                    modifier = Modifier
                        .size(40.dp)
                        .clickable { navController.navigate("danhsachsach") },

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

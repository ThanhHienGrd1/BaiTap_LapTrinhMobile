package com.example.baitaptuan7_database.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.baitaptuan7_database.R
import com.example.baitaptuan7_database.viewmodel.TaskViewModel

@Composable
fun ListScreen(
    vm: TaskViewModel,
    onTaskClick: (Int) -> Unit
) {
    // Load dữ liệu từ Room khi màn hình xuất hiện
    LaunchedEffect(Unit) {
        vm.loadTasks()
    }

    val tasks by vm.tasks.collectAsState()
    val isLoading by vm.isLoading.collectAsState()
    val error by vm.errorMessage.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // logo + header
        Spacer(Modifier.height(12.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .background(Color(0xFFE9F6FC), RoundedCornerShape(15.dp))
                    .padding(8.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.logo_uth),
                    contentDescription = "logo",
                    modifier = Modifier.size(60.dp)
                )
            }

            Column {
                Text(
                    "SmartTasks",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Blue
                )
                Text("A simple and efficient to-do app", fontSize = 12.sp)
            }

            Image(
                painter = painterResource(R.drawable.icon_bell),
                contentDescription = "icon-bell",
                modifier = Modifier
                    .size(75.dp)
                    .padding(start = 44.dp)
            )
        }

        Spacer(Modifier.height(40.dp))

        // Hiển thị dữ liệu
        when {
            isLoading -> CircularProgressIndicator()
            error != null -> Text("Error: $error")
            tasks.isEmpty() -> {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp)
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.LightGray, RoundedCornerShape(20.dp))
                            .padding(50.dp)
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Image(
                                painter = painterResource(R.drawable.task_empty),
                                contentDescription = "Task_Empty",
                                modifier = Modifier.size(160.dp)
                            )

                            Text("No Task Yet!", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                            Text(
                                "Stay productive - add something to do",
                                fontSize = 14.sp,
                                modifier = Modifier.padding(top = 8.dp)
                            )
                        }
                    }
                }
            }
            else -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(tasks) { task ->
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color(0xFF79CCC3), RoundedCornerShape(20.dp))
                                .padding(15.dp)
                                .clickable { onTaskClick(task.id) },
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Text(task.title, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                            Text(
                                task.description,
                                fontSize = 15.sp,
                                textAlign = TextAlign.Start
                            )

                            Row(
                                modifier = Modifier.padding(top = 4.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text("Status: ", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                                Text(task.status, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                                Text(
                                    task.createdAt,
                                    fontSize = 16.sp,
                                    modifier = Modifier.padding(start = 20.dp)
                                )
                            }
                        }

                        Spacer(Modifier.height(12.dp))
                    }
                }
            }
        }
    }
}

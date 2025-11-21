package com.example.baitaptuan7_database.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.baitaptuan7_database.R
import com.example.baitaptuan7_database.viewmodel.TaskViewModel
import kotlinx.coroutines.launch

@Composable
fun DetailScreen(
    vm: TaskViewModel,
    taskId: Int,
    onBack: () -> Unit
) {
    val scope = rememberCoroutineScope()

    // Load task detail từ Room
    LaunchedEffect(taskId) {
        vm.loadTaskDetail(taskId)
    }

    val task by vm.taskDetail.collectAsState()
    val isLoading by vm.isLoading.collectAsState()
    val error by vm.errorMessage.collectAsState()

    val selectedIndexes = remember { mutableStateListOf<Int>() }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        // ─── HEADER ───
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(R.drawable.icon_back),
                contentDescription = "Icon_back",
                modifier = Modifier
                    .size(35.dp)
                    .clickable { onBack() }
            )

            Text("Detail", fontSize = 24.sp, fontWeight = FontWeight.Bold)

            Image(
                painter = painterResource(R.drawable.icon_detele),
                contentDescription = "icon_delete",
                modifier = Modifier
                    .size(35.dp)
                    .clickable {
                        if (selectedIndexes.isNotEmpty() && task != null) {
                            val toDelete = selectedIndexes.toList()
                            scope.launch {
                                vm.deleteSelectedSubtasks(taskId, toDelete)
                                selectedIndexes.clear()
                            }
                        }
                    }
            )
        }

        Spacer(Modifier.height(30.dp))

        when {
            isLoading -> CircularProgressIndicator()
            error != null -> Text("Lỗi: $error")
            task == null -> Text("Không tìm thấy task")
            else -> {

                // ─── DETAIL TASK ───
                Column {

                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                    ) {
                        Text(task!!.title, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                        Text(task!!.description, fontSize = 16.sp)
                    }

                    Spacer(Modifier.height(30.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFFD38DA4), RoundedCornerShape(10.dp))
                            .padding(20.dp)
                    ) {
                        iconCustom(R.drawable.icon_category, "Category", task!!.category)
                        iconCustom(R.drawable.icon_status, "Status", task!!.status)
                        iconCustom(R.drawable.icon_prority, "Priority", task!!.priority)
                    }

                    Spacer(Modifier.height(16.dp))

                    Text(
                        "Subtasks",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 20.dp)
                    )

                    Spacer(Modifier.height(10.dp))

                    task!!.subtasks.forEachIndexed { index, s ->
                        val isSelected = selectedIndexes.contains(index)
                        val iconRes = if (isSelected) R.drawable.icon_tick else R.drawable.icon_untick

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp, vertical = 6.dp)
                                .background(Color(0xFFF2F2F2), RoundedCornerShape(10.dp))
                                .padding(12.dp)
                                .clickable {
                                    if (isSelected) selectedIndexes.remove(index)
                                    else selectedIndexes.add(index)
                                }
                        ) {
                            Image(
                                painter = painterResource(iconRes),
                                contentDescription = "checkbox",
                                modifier = Modifier.size(28.dp)
                            )

                            Spacer(Modifier.width(12.dp))
                            Text(s.title, fontSize = 14.sp)
                        }
                    }

                    Spacer(Modifier.height(20.dp))

                    Text(
                        "Attachments",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 20.dp)
                    )

                    Spacer(Modifier.height(10.dp))

                    task!!.attachments.forEach { file ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp, vertical = 6.dp)
                                .background(Color(0xFFEFEFEF), RoundedCornerShape(10.dp))
                                .padding(14.dp)
                        ) {
                            Image(
                                painter = painterResource(R.drawable.icon_file),
                                contentDescription = "file",
                                modifier = Modifier.size(30.dp)
                            )

                            Spacer(Modifier.width(12.dp))
                            Text(file.fileName, fontSize = 14.sp)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun iconCustom(iconRes: Int, label: String, value: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Image(
            painter = painterResource(iconRes),
            contentDescription = "icon",
            modifier = Modifier.size(30.dp)
        )
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(label, fontSize = 14.sp)
            Text(value, fontSize = 14.sp, fontWeight = FontWeight.Bold)
        }
    }
}

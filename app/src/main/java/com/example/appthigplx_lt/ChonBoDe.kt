package com.example.appthigplx_lt

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChonBoDe(navController: NavController) {
    // Hiển thị nhãn có chữ, nhưng tách số khi điều hướng
    val danhSachDe = listOf("Đề 1", "Đề 2", "Đề 3", "Đề 4", "Đề 5", "Đề 6")

    Scaffold(topBar = { TopAppBar(title = { Text("Chọn Bộ Đề Thi") }) }) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(danhSachDe) { boDeLabel ->
                Button(
                    onClick = {
                        // Lấy phần số từ "Đề 1" -> "1"
                        val boDeNumber = boDeLabel.filter { it.isDigit() }.ifEmpty { "1" }
                        navController.navigate("quyCheThi/$boDeNumber")
                        // hoặc đi thẳng thi:
                        // navController.navigate("thiSatHach/$boDeNumber")
                    },
                    modifier = Modifier.fillMaxWidth().height(56.dp)
                ) {
                    Text(boDeLabel, fontSize = 18.sp, fontWeight = FontWeight.Medium)
                }
            }
        }
    }
}
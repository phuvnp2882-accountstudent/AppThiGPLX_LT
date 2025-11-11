package com.example.appthigplx_lt

import androidx.compose.foundation.layout.*
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
fun ThiSatHach(navController: NavController, boDe: String) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Thi Sát Hạch - Bộ $boDe") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Câu 1/25",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Nội dung câu hỏi sẽ hiển thị ở đây.",
                fontSize = 18.sp
            )

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedButton(onClick = { /* chọn đáp án 1 */ }, modifier = Modifier.fillMaxWidth()) {
                    Text("Đáp án 1", fontSize = 16.sp)
                }
                OutlinedButton(onClick = { /* chọn đáp án 2 */ }, modifier = Modifier.fillMaxWidth()) {
                    Text("Đáp án 2", fontSize = 16.sp)
                }
                OutlinedButton(onClick = { /* chọn đáp án 3 */ }, modifier = Modifier.fillMaxWidth()) {
                    Text("Đáp án 3", fontSize = 16.sp)
                }
                OutlinedButton(onClick = { /* chọn đáp án 4 */ }, modifier = Modifier.fillMaxWidth()) {
                    Text("Đáp án 4", fontSize = 16.sp)
                }
            }

            Button(
                onClick = { /* sang câu tiếp theo */ },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("CÂU TIẾP THEO", fontSize = 18.sp)
            }
        }
    }
}

package com.example.appthigplx_lt

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
fun QuyCheThi(navController: NavController, boDe: String) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Quy chế thi - $boDe") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Quay lại"
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Quy chế thi GPLX ($boDe)",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = """
                    Thí sinh cần đạt tối thiểu 21/25 câu hỏi để đạt yêu cầu.
                    Không được vi phạm quy định an toàn giao thông.
                    Khi làm bài, hãy bình tĩnh và đọc kỹ từng câu hỏi.
                """.trimIndent(),
                fontSize = 16.sp,
                lineHeight = 22.sp
            )

            Spacer(modifier = Modifier.height(40.dp))

            Button(
                onClick = {
                    navController.navigate("thiSatHach/$boDe")
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("BẮT ĐẦU THI", fontSize = 18.sp)
            }
        }

}
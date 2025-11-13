package com.example.appthigplx_lt

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChonBoDe(navController: NavController) {
    val mintColor = Color(0xFF00C4A7)
    val blueButton = Color(0xFF1976D2)

    val danhSachDe = listOf("Bộ đề 1", "Bộ đề 2", "Bộ đề 3", "Bộ đề 4", "Bộ đề 5", "Bộ đề 6")

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "THI THỬ",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Quay lại",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = mintColor)
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(Color(0xFFF6F8F7))
                .padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "CHỌN BỘ ĐỀ THI",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            danhSachDe.forEach { boDe ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(70.dp),
                    shape = RoundedCornerShape(14.dp),
                    elevation = CardDefaults.cardElevation(6.dp), // 🌟 Đổ bóng nhẹ
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = boDe.uppercase(),
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 18.sp,
                            color = Color.Black
                        )

                        Button(
                            onClick = {
                                val boDeNumber = boDe.filter { it.isDigit() }.ifEmpty { "1" }
                                navController.navigate("quyCheThi/$boDeNumber")
                                // hoặc đi thẳng thi: navController.navigate("thiSatHach/$boDeNumber")
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = blueButton),
                            shape = RoundedCornerShape(10.dp),
                            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 8.dp)
                        ) {
                            Text(
                                "LÀM BÀI",
                                color = Color.White,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))
            }
        }
    }
}

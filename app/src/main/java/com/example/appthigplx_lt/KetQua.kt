package com.example.appthigplx_lt

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.HorizontalDivider


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KetQua(
    navController: NavController,
    tongDiem: Int,
    boDe: String,
    coSaiDiemLiet: Boolean
) {
    val mintColor = Color(0xFF00C4A7)
    val green = Color(0xFF4CAF50)
    val red = Color(0xFFF44336)

    // ✅ Xác định kết quả
    val isPass = tongDiem >= 21 && !coSaiDiemLiet
    val textResult = when {
        coSaiDiemLiet -> "❌ RỚT DO SAI CÂU ĐIỂM LIỆT"
        isPass -> "🎉 CHÚC MỪNG! BẠN ĐÃ ĐẠT"
        else -> "❌ KHÔNG ĐẠT YÊU CẦU"
    }
    val colorResult = when {
        coSaiDiemLiet -> red
        isPass -> green
        else -> red
    }

    // 🌈 Gradient nền
    val gradient = Brush.verticalGradient(
        colors = listOf(Color(0xFFB2F7EF), Color(0xFFE0F2F1), Color.White)
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "KẾT QUẢ THI GPLX",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = mintColor),
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Quay lại",
                            tint = Color.White
                        )
                    }
                }
            )
        }
    ) { padding ->

        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(gradient),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .width(360.dp)
                    .clip(RoundedCornerShape(24.dp))
                    .background(Color.White)
                    .padding(28.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                // 🎯 Vòng tròn điểm
                Box(
                    modifier = Modifier
                        .size(140.dp)
                        .clip(CircleShape)
                        .background(
                            if (isPass) green.copy(alpha = 0.1f)
                            else red.copy(alpha = 0.1f)
                        )
                        .border(
                            6.dp,
                            if (isPass) green else red,
                            CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "$tongDiem",
                        fontSize = 48.sp,
                        fontWeight = FontWeight.Bold,
                        color = colorResult
                    )
                }

                // 🏁 Trạng thái kết quả
                Text(
                    text = textResult,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorResult,
                    textAlign = TextAlign.Center
                )

                // 📋 Gợi ý / chú thích
                if (coSaiDiemLiet) {
                    Text(
                        text = "Bạn đã sai ít nhất một câu điểm liệt.\nVì vậy kết quả không đạt.",
                        fontSize = 15.sp,
                        color = Color.DarkGray,
                        textAlign = TextAlign.Center
                    )
                } else if (isPass) {
                    Text(
                        text = "Bạn đã hoàn thành bài thi xuất sắc!\nHãy tiếp tục duy trì phong độ nhé 🎉",
                        fontSize = 15.sp,
                        color = Color.Gray,
                        textAlign = TextAlign.Center
                    )
                } else {
                    Text(
                        text = "Hãy luyện tập thêm để đạt kết quả tốt hơn lần sau 💪",
                        fontSize = 15.sp,
                        color = Color.Gray,
                        textAlign = TextAlign.Center
                    )
                }

                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 8.dp),
                    thickness = 1.dp,
                    color = mintColor.copy(alpha = 0.4f)
                )

                // 🔘 Bộ đề
                Text(
                    text = "Bộ đề: $boDe",
                    fontSize = 16.sp,
                    color = Color.DarkGray,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(10.dp))

                // 🏠 Nút về trang chủ
                Button(
                    onClick = {
                        navController.navigate("home") {
                            popUpTo("home") { inclusive = true }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = mintColor)
                ) {
                    Text("🏠 VỀ TRANG CHỦ", fontSize = 17.sp, color = Color.White)
                }

                // 🔁 Nút thi lại
                OutlinedButton(
                    onClick = {
                        navController.navigate("thiSatHach/$boDe") {
                            popUpTo("home") { inclusive = false }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    shape = RoundedCornerShape(14.dp),
                    border = BorderStroke(2.dp, mintColor),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = Color.White,
                        contentColor = mintColor
                    )
                ) {
                    Text("🔁 THI LẠI", fontSize = 17.sp, fontWeight = FontWeight.Medium)
                }
            }
        }
    }
}

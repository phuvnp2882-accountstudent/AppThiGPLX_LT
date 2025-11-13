package com.example.appthigplx_lt

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

data class ChuDe(
    val ten: String,
    val daLam: Int,
    val tong: Int
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnTheoChuDe(navController: NavController) {
    val context = LocalContext.current
    val db = remember { MyDbHelper(context) }

    val danhSachChuDeCoDinh = listOf(
        "Khái niệm và Quy tắc",
        "Văn hóa và đạo đức lái xe",
        "Kỹ thuật lái xe",
        "Sa hình",
        "Biển báo đường bộ",
        "Câu điểm liệt"
    )

    var danhSachChuDe by remember { mutableStateOf(listOf<ChuDe>()) }
    var showConfirmDialog by remember { mutableStateOf(false) }

    // Load tiến độ từ DB
    LaunchedEffect(Unit) {
        val list = danhSachChuDeCoDinh.map { ten ->
            val daLam = db.getCorrectCount(ten)
            val tong = db.getLyThuyetTheoChuDe(ten).size
            ChuDe(ten, daLam, tong)
        }
        danhSachChuDe = list
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Quay lại",
                            tint = Color.White
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { showConfirmDialog = true }) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Xóa tiến độ",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF00C2A0)
                )
            )
        }
    ) { padding ->

        // === Hộp thoại xác nhận ===
        if (showConfirmDialog) {
            AlertDialog(
                onDismissRequest = { showConfirmDialog = false },
                title = { Text("Xác nhận xoá tiến độ") },
                text = { Text("Bạn có chắc muốn xoá toàn bộ tiến độ hiện tại không?") },
                confirmButton = {
                    TextButton(
                        onClick = {
                            db.clearAllProgress() // 🔥 Hàm này bạn sẽ thêm trong MyDbHelper
                            showConfirmDialog = false

                            // Làm mới danh sách sau khi xoá
                            val refreshed = danhSachChuDeCoDinh.map { ten ->
                                val daLam = db.getCorrectCount(ten)
                                val tong = db.getLyThuyetTheoChuDe(ten).size
                                ChuDe(ten, daLam, tong)
                            }
                            danhSachChuDe = refreshed
                        }
                    ) {
                        Text("Có", color = Color.Red, fontWeight = FontWeight.Bold)
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showConfirmDialog = false }) {
                        Text("Không", color = Color.Gray)
                    }
                }
            )
        }

        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            Text(
                text = "ÔN TẬP THEO CHỦ ĐỀ",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.Black
            )

            LazyColumn(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(danhSachChuDe) { chuDe ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        elevation = CardDefaults.cardElevation(4.dp),
                        onClick = {
                            navController.navigate("onLyThuyet/${chuDe.ten}")
                        }
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth()
                        ) {
                            Text(
                                text = chuDe.ten.uppercase(),
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            LinearProgressIndicator(
                                progress = {
                                    if (chuDe.tong > 0)
                                        chuDe.daLam.toFloat() / chuDe.tong.toFloat()
                                    else 0f
                                },
                                modifier = Modifier.fillMaxWidth(),
                                color = Color(0xFF00C2A0),
                                trackColor = ProgressIndicatorDefaults.linearTrackColor,
                                strokeCap = ProgressIndicatorDefaults.LinearStrokeCap,
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "${chuDe.daLam}/${chuDe.tong} câu",
                                fontSize = 14.sp,
                                color = Color.Gray
                            )
                        }
                    }
                }
            }
        }
    }
}

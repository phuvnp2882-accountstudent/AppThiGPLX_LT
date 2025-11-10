package com.example.appthigplx_lt

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
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
        "Câu điểm liệt",
        "Sa hình",
        "Khái niệm và Quy tắc",
        "Văn hóa và đạo đức lái xe",
        "Kỹ thuật lái xe",
        "Biển báo đường bộ"
    )

    var danhSachChuDe by remember { mutableStateOf(listOf<ChuDe>()) }

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
        topBar = { TopAppBar(title = { Text("Ôn tập theo chủ đề") }) }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp),
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
                            progress = if (chuDe.tong > 0) chuDe.daLam.toFloat() / chuDe.tong.toFloat() else 0f,
                            modifier = Modifier.fillMaxWidth(),
                            color = Color(0xFF00C2A0)
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

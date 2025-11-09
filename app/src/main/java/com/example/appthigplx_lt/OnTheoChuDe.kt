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

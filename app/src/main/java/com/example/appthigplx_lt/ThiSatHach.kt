package com.example.appthigplx_lt

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.delay

@SuppressLint("MutableCollectionMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThiSatHach(navController: NavController, boDe: String) {
    val context = LocalContext.current
    val db = remember { MyDbHelper(context) }

    var listCauHoi by remember { mutableStateOf(listOf<LyThuyet>()) }
    var currentIndex by remember { mutableIntStateOf(0) }
    var tongDiem by remember { mutableIntStateOf(0) }
    var luaChonList by remember { mutableStateOf(mutableListOf<String>()) }
    var daLamList by remember { mutableStateOf(MutableList(25) { false }) }
    var isExpanded by remember { mutableStateOf(false) }
    var coSaiDiemLiet by remember { mutableStateOf(false) }

    var timeLeft by remember { mutableIntStateOf(19 * 60) }
    val mintColor = Color(0xFF00C4A7)

    // 🕐 Format thời gian
    fun formatTime(seconds: Int): String {
        val m = seconds / 60
        val s = seconds % 60
        return "%02d:%02d".format(m, s)
    }

    // ⏳ Đếm ngược thời gian
    LaunchedEffect(Unit) {
        while (timeLeft > 0) {
            delay(1000)
            timeLeft--
        }
        navController.navigate("ketQua/$tongDiem/$boDe/$coSaiDiemLiet")
    }

    // 📘 Lấy dữ liệu câu hỏi
    LaunchedEffect(boDe) {
        db.createDefaultLyThuyet()
        val all = db.getLyThuyetTheoBoDe(boDe)
        listCauHoi = if (all.size > 25) all.shuffled().take(25) else all
        daLamList = MutableList(listCauHoi.size) { false }
        luaChonList = MutableList(listCauHoi.size) { "" }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "BỘ ĐỀ $boDe",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(50))
                                    .background(Color.White)
                                    .border(2.dp, mintColor, RoundedCornerShape(50))
                                    .padding(horizontal = 10.dp, vertical = 4.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "⏱ ${formatTime(timeLeft)}",
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = if (timeLeft <= 60) Color(0xFFD32F2F) else Color.Black
                                )
                            }

                            Button(
                                onClick = {
                                    navController.navigate("ketQua/$tongDiem/$boDe/$coSaiDiemLiet")
                                },
                                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD32F2F)),
                                contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp),
                                modifier = Modifier.height(32.dp)
                            ) {
                                Text("NỘP BÀI", color = Color.White, fontSize = 14.sp)
                            }
                        }
                    }
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
        },

        bottomBar = {
            if (listCauHoi.isNotEmpty()) {
                BottomBarWithExpandableNumbers(
                    currentIndex = currentIndex,
                    total = listCauHoi.size,
                    isExpanded = isExpanded,
                    daLamList = daLamList,
                    onToggleExpand = { isExpanded = !isExpanded },
                    onPrevious = { if (currentIndex > 0) currentIndex-- },
                    onNext = { if (currentIndex < listCauHoi.size - 1) currentIndex++ },
                    onJumpTo = { index -> currentIndex = index }
                )
            }
        }
    ) { padding ->
        if (listCauHoi.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Không có câu hỏi trong bộ $boDe", fontSize = 18.sp)
            }
        } else {
            val cauHoi = listCauHoi[currentIndex]
            var luaChonTamThoi by remember { mutableStateOf("") }

            // Cập nhật đáp án đang chọn khi đổi câu
            LaunchedEffect(currentIndex) {
                luaChonTamThoi = luaChonList[currentIndex]
            }

            LazyColumn(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // --- Câu hỏi ---
                item {
                    Box(
                        modifier = Modifier
                            .width(350.dp)
                            .clip(RoundedCornerShape(15.dp))
                            .border(2.dp, mintColor, RoundedCornerShape(15.dp))
                            .background(Color(0xFFF5F5F5))
                            .padding(12.dp)
                    ) {
                        Column {
                            Text(
                                text = "CÂU ${currentIndex + 1}/${listCauHoi.size}" +
                                        if (cauHoi.chuDe == "Câu điểm liệt") " ⚠️ (Câu điểm liệt)" else "",
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp,
                                color = if (cauHoi.chuDe == "Câu điểm liệt") Color(0xFFD32F2F) else mintColor
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = cauHoi.cauHoi,
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color.Black
                            )
                        }
                    }
                }

                // --- Hình minh họa ---
                if (cauHoi.hinhAnh != null && cauHoi.hinhAnh != 0) {
                    item {
                        Image(
                            painter = painterResource(id = cauHoi.hinhAnh),
                            contentDescription = null,
                            modifier = Modifier
                                .width(350.dp)
                                .height(160.dp)
                                .clip(RoundedCornerShape(12.dp))
                        )
                    }
                }

                // --- Danh sách đáp án ---
                items(
                    items = listOf(
                        "1" to cauHoi.dapAn1,
                        "2" to cauHoi.dapAn2,
                        "3" to cauHoi.dapAn3,
                        "4" to cauHoi.dapAn4
                    ).filter { it.second.isNotBlank() }
                ) { (key, text) ->
                    val isSelected = luaChonTamThoi == key

                    OutlinedButton(
                        onClick = {
                            luaChonTamThoi = key
                            luaChonList[currentIndex] = key
                            daLamList[currentIndex] = true

                            // Tính lại điểm và lỗi câu điểm liệt
                            tongDiem = 0
                            coSaiDiemLiet = false
                            for (i in listCauHoi.indices) {
                                val chon = luaChonList[i]
                                val cau = listCauHoi[i]
                                if (chon.isNotEmpty()) {
                                    if (chon == cau.dapAnDung) tongDiem++
                                    else if (cau.chuDe == "Câu điểm liệt") coSaiDiemLiet = true
                                }
                            }
                        },
                        modifier = Modifier
                            .width(350.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .padding(vertical = 4.dp),
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = if (isSelected)
                                Color(0xFFE0F7FA) else Color.White,
                            contentColor = Color.Black
                        ),
                        border = ButtonDefaults.outlinedButtonBorder.copy(
                            width = 2.dp,
                            brush = SolidColor(if (isSelected) mintColor else Color.White)
                        )
                    ) {
                        Text(
                            text = text,
                            fontSize = 16.sp,
                            textAlign = TextAlign.Start,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }
    }
}

// 🧭 Thanh điều hướng câu hỏi
@Composable
fun BottomBarWithExpandableNumbers(
    currentIndex: Int,
    total: Int,
    isExpanded: Boolean,
    daLamList: List<Boolean>,
    onToggleExpand: () -> Unit,
    onPrevious: () -> Unit,
    onNext: () -> Unit,
    onJumpTo: (Int) -> Unit
) {
    val mintColor = Color(0xFF00C4A7)
    val green = Color(0xFF4CAF50)
    val gray = Color(0xFFD3D3D3)
    val orange = Color(0xFFFF9800)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {
        if (isExpanded) {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFF6F6F6))
                    .padding(vertical = 6.dp),
                horizontalArrangement = Arrangement.spacedBy(3.dp),
                contentPadding = PaddingValues(horizontal = 6.dp)
            ) {
                items(total) { i ->
                    val backgroundColor = when {
                        i == currentIndex -> orange
                        daLamList[i] -> green
                        else -> gray
                    }
                    Box(
                        modifier = Modifier
                            .size(34.dp)
                            .clip(CircleShape)
                            .background(backgroundColor)
                            .clickable { onJumpTo(i) },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "${i + 1}",
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(mintColor)
                .padding(vertical = 8.dp, horizontal = 24.dp),
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Câu trước",
                    color = Color.Black,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.clickable { onPrevious() }
                )

                IconButton(onClick = { onToggleExpand() }) {
                    Icon(
                        imageVector = if (isExpanded) Icons.Default.KeyboardArrowDown
                        else Icons.Default.KeyboardArrowUp,
                        contentDescription = null,
                        tint = Color.Black
                    )
                }

                Text(
                    "Câu sau",
                    color = Color.Black,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.clickable { onNext() }
                )
            }
        }
    }
}

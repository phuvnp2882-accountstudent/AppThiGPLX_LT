package com.example.appthigplx_lt

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.ui.tooling.preview.Preview


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnLyThuyet(navController: NavController, chuDe: String) {
    val context = LocalContext.current
    val db = remember { MyDbHelper(context) }

    var listLyThuyet by remember { mutableStateOf(listOf<LyThuyet>()) }
    var currentIndex by remember { mutableStateOf(0) }
    var selectedAnswer by remember { mutableStateOf<String?>(null) }
    var showResult by remember { mutableStateOf(false) }
    var tongCau by remember { mutableStateOf(0) }
    var soCauDung by remember { mutableStateOf(0) }
    // 🔹 Nạp dữ liệu câu hỏi chưa đúng
    LaunchedEffect(Unit) {
        db.createDefaultLyThuyet()
        val all = db.getLyThuyetTheoChuDe(chuDe)
        val chuaDung = all.filter { !db.isAnsweredCorrectly(chuDe, it.cauHoi) }
        listLyThuyet = chuaDung
        tongCau = all.size
        soCauDung = db.getCorrectCount(chuDe)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Ôn tập: $chuDe") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Quay lại")
                    }
                }
            )
        }
    ) { padding ->
        if (listLyThuyet.isEmpty()) {
            // ✅ Nếu đã làm đúng hết
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(
                    text = "🎉 Bạn đã hoàn thành toàn bộ câu hỏi của chủ đề này!",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(24.dp)
                )
            }
        } else {
            val currentQuestion = listLyThuyet[currentIndex]
            val progress = soCauDung / tongCau.toFloat()

            LazyColumn(
                modifier = Modifier
                    .padding(padding)
                    .padding(16.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // 🔹 Thanh tiến độ
                item {
                    LinearProgressIndicator(
                        progress = progress,
                        modifier = Modifier.fillMaxWidth(),
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = "Tiến độ: $soCauDung/$tongCau câu đúng",
                        fontSize = 15.sp,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(top = 6.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Câu ${currentIndex + 1}/${listLyThuyet.size}",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = currentQuestion.cauHoi,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium
                    )

                    // 🔹 Hiển thị hình ảnh nếu có
                    if (currentQuestion.hinhAnh != null && currentQuestion.hinhAnh != 0) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Image(
                            painter = painterResource(id = currentQuestion.hinhAnh),
                            contentDescription = "Hình minh họa",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(180.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                }

                // 🔹 Danh sách đáp án
                items(
                    listOf(
                        "1" to currentQuestion.dapAn1,
                        "2" to currentQuestion.dapAn2,
                        "3" to currentQuestion.dapAn3,
                        "4" to currentQuestion.dapAn4
                    ).filter { it.second.isNotBlank() }
                ) { (key, text) ->
                    val isSelected = selectedAnswer == key
                    val isCorrect = currentQuestion.dapAnDung == key

                    val color = when {
                        !showResult -> MaterialTheme.colorScheme.surfaceVariant
                        isSelected && isCorrect -> MaterialTheme.colorScheme.primaryContainer
                        isSelected && !isCorrect -> MaterialTheme.colorScheme.errorContainer
                        isCorrect -> MaterialTheme.colorScheme.primaryContainer
                        else -> MaterialTheme.colorScheme.surfaceVariant
                    }

                    Button(
                        onClick = {
                            if (!showResult) {
                                selectedAnswer = key
                                showResult = true
                                if (isCorrect) {
                                    db.saveCorrectAnswer(chuDe, currentQuestion.cauHoi)
                                    soCauDung++
                                }
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = color),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = text, fontSize = 16.sp)
                    }


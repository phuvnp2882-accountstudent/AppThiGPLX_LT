package com.example.appthigplx_lt

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.foundation.BorderStroke
import androidx.compose.runtime.mutableIntStateOf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnLyThuyet(navController: NavController, chuDe: String) {
    val context = LocalContext.current
    val db = remember { MyDbHelper(context) }

    var listLyThuyet by remember { mutableStateOf(listOf<LyThuyet>()) }
    var currentIndex by remember { mutableIntStateOf(0) }
    var selectedAnswer by remember { mutableStateOf<String?>(null) }
    var showResult by remember { mutableStateOf(false) }

    val mintColor = Color(0xFF00C4A7)

    fun getAnswerText(q: LyThuyet, key: String?): String {
        return when (key) {
            "1" -> q.dapAn1
            "2" -> q.dapAn2
            "3" -> q.dapAn3
            "4" -> q.dapAn4
            else -> ""
        }
    }

    // 📘 Lấy câu hỏi và lọc những câu chưa đúng
    LaunchedEffect(Unit) {
        db.createDefaultLyThuyet()

        // Lấy tất cả câu hỏi của chủ đề
        val allQuestions = db.getLyThuyetTheoChuDe(chuDe)

        // Lấy danh sách các câu đã làm đúng
        val cauDaLamDung = db.getCorrectQuestions(chuDe)

        // Lọc bỏ các câu đã đúng
        val filtered = allQuestions.filter { it.cauHoi !in cauDaLamDung }

        // Nếu đã làm đúng hết, load lại toàn bộ để ôn tập
        listLyThuyet = filtered.ifEmpty { allQuestions }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "ÔN TẬP: ${chuDe.uppercase()}",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
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
        if (listLyThuyet.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Không có dữ liệu câu hỏi", fontSize = 18.sp)
            }
        } else {
            val current = listLyThuyet[currentIndex]

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
                            .border(
                                width = 2.dp,
                                color = mintColor,
                                shape = RoundedCornerShape(15.dp)
                            )
                            .background(Color(0xFFF5F5F5))
                            .padding(12.dp)
                    ) {
                        Column {
                            Text(
                                text = "CÂU ${currentIndex + 1}/${listLyThuyet.size}",
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp,
                                color = mintColor
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = current.cauHoi,
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color.Black
                            )
                        }
                    }
                }

                // --- Hình minh họa ---
                if (current.hinhAnh != null && current.hinhAnh != 0) {
                    item {
                        Image(
                            painter = painterResource(id = current.hinhAnh),
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
                    listOf(
                        "1" to current.dapAn1,
                        "2" to current.dapAn2,
                        "3" to current.dapAn3,
                        "4" to current.dapAn4
                    ).filter { it.second.isNotBlank() }
                ) { (key, text) ->
                    val isSelected = selectedAnswer == key
                    val isCorrect = current.dapAnDung == key

                    val backgroundColor = when {
                        !showResult && isSelected -> Color(0xFFE0F7FA)
                        showResult && isCorrect -> Color(0xFFC8E6C9)
                        showResult && isSelected && !isCorrect -> Color(0xFFFFCDD2)
                        else -> Color.White
                    }

                    OutlinedButton(
                        onClick = {
                            if (!showResult) {
                                selectedAnswer = key
                                showResult = true
                                if (key == current.dapAnDung) {
                                    db.saveCorrectAnswer(chuDe, current.cauHoi) // 🟢 Lưu câu đúng
                                }
                            }
                        },
                        modifier = Modifier
                            .width(350.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .padding(vertical = 3.dp),
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = backgroundColor,
                            contentColor = Color.Black
                        ),
                        border = BorderStroke(2.dp, Color.Transparent)
                    ) {
                        Text(
                            text = text,
                            fontSize = 16.sp,
                            textAlign = TextAlign.Start,
                            color = Color.Black,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }

                // --- Giải thích + nút Câu sau ---
                if (showResult) {
                    item {
                        val isCorrect = selectedAnswer == current.dapAnDung
                        val color = if (isCorrect) Color(0xFF00C853) else Color(0xFFD32F2F)
                        val correctText = getAnswerText(current, current.dapAnDung)

                        Box(
                            modifier = Modifier
                                .width(350.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(Color(0xFFE8F5E9))
                                .padding(12.dp)
                        ) {
                            Text(
                                text = if (isCorrect)
                                    "✅ Đáp án chính xác!"
                                else
                                    "❌ Sai rồi. Đáp án đúng là: $correctText",
                                fontWeight = FontWeight.Bold,
                                color = color,
                                fontSize = 16.sp
                            )
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        Button(
                            onClick = {
                                if (currentIndex < listLyThuyet.size - 1) {
                                    currentIndex++
                                } else {
                                    currentIndex = 0
                                }
                                showResult = false
                                selectedAnswer = null
                            },
                            modifier = Modifier
                                .width(350.dp)
                                .clip(RoundedCornerShape(10.dp)),
                            colors = ButtonDefaults.buttonColors(containerColor = mintColor)
                        ) {
                            Text("Câu sau", color = Color.White, fontSize = 16.sp)
                        }
                    }
                }
            }
        }
    }
}

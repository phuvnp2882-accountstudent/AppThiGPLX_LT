package com.example.appthigplx_lt

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(navController: NavController) {

    // 🔹 Lấy dữ liệu tiến độ từ SQLite
    val context = LocalContext.current
    val db = remember { MyDbHelper(context) }

    var soCauDung by remember { mutableIntStateOf(0) }
    var tongCau by remember { mutableIntStateOf(250) }

    LaunchedEffect(Unit) {
        soCauDung = db.getTotalCorrectCount()
        // tongCau = db.getTotalQuestionCount() // nếu có
    }

    val tiLe = if (tongCau > 0) soCauDung.toFloat() / tongCau else 0f

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "HẠNG THI A1",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                },
                actions = {
                    IconButton(onClick = { /* TODO: mở cài đặt */ }) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "Cài đặt",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF00BFA6)
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(Color(0xFFF8F8F8)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(12.dp))

            // Ảnh bìa
            Card(
                shape = RoundedCornerShape(8.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                modifier = Modifier
                    .width(200.dp)
                    .height(260.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.bia_250_cauhoi),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 🔹 Tiến độ ôn tập
            Card(
                shape = RoundedCornerShape(10.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .padding(12.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("TIẾN ĐỘ ÔN TẬP 🔥", fontWeight = FontWeight.Medium, fontSize = 16.sp)
                    Spacer(modifier = Modifier.height(6.dp))
                    LinearProgressIndicator(
                        progress = { tiLe },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(8.dp)
                            .clip(RoundedCornerShape(4.dp)),
                        color = Color(0xFF00BFA6),
                        trackColor = ProgressIndicatorDefaults.linearTrackColor,
                        strokeCap = ProgressIndicatorDefaults.LinearStrokeCap,
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text("$soCauDung/$tongCau câu", fontSize = 14.sp, color = Color.Gray)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // 🔹 Lưới 2x2 các nút có hình
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    HomeImageButton(
                        text = "THI THỬ",
                        imageRes = R.drawable.ic_thi_thu,
                        background = Color(0xFF00BFA6),
                        modifier = Modifier.weight(1f)
                    ) { navController.navigate("chonBoDe") }

                    HomeImageButton(
                        text = "ÔN LÝ THUYẾT",
                        imageRes = R.drawable.ic_ly_thuyet,
                        background = Color(0xFF0288D1),
                        modifier = Modifier.weight(1f)
                    ) { navController.navigate("onTheoChuDe") }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    HomeImageButton(
                        text = "TRA CỨU BIỂN BÁO",
                        imageRes = R.drawable.ic_bien_bao,
                        background = Color(0xFFD32F2F),
                        modifier = Modifier.weight(1f)
                    ) { navController.navigate("traCuuBienBao") }

                    HomeImageButton(
                        text = "MẸO ÔN THI",
                        imageRes = R.drawable.ic_meo,
                        background = Color(0xFFFFC107),
                        textColor = Color.Black,
                        modifier = Modifier.weight(1f)
                    ) { navController.navigate("meoOnThi") }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Composable
fun HomeImageButton(
    text: String,
    imageRes: Int,
    background: Color,
    textColor: Color = Color.White,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = background),
        shape = RoundedCornerShape(16.dp),
        modifier = modifier.height(100.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = text,
                modifier = Modifier
                    .size(50.dp)
                    .padding(bottom = 6.dp),
                contentScale = ContentScale.Fit
            )
            Text(
                text = text,
                color = textColor,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomePreview() {
    val navController = rememberNavController()
    Home(navController)
}


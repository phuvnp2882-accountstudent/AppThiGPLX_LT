package com.example.appthigplx_lt

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TraCuuBienBao(navController: NavController) {
    val context = LocalContext.current
    val db = remember { MyDbHelper(context) }

    var listBienBao by remember { mutableStateOf(listOf<BienBao>()) }
    var keyword by remember { mutableStateOf("") }
    var selectedTabIndex by remember { mutableIntStateOf(0) }

    val mintColor = Color(0xFF00C4A7)

    // 📘 Danh sách nhóm biển báo
    val tabs = listOf("Biển báo cấm", "Biển báo nguy hiểm", "Biển báo hiệu lênh", "Biển chỉ dẫn", "Biển phụ")

    // Nạp dữ liệu
    LaunchedEffect(Unit) {
        db.createDefaultBienBao()
        listBienBao = db.getAllBienBao()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "TRA CỨU BIỂN BÁO",
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
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(Color(0xFFF6F8F7))
        ) {
            // --- Tabs phân loại ---
            ScrollableTabRow(
                selectedTabIndex = selectedTabIndex,
                containerColor = Color.White,
                contentColor = mintColor,
                edgePadding = 0.dp,
                divider = {},
                indicator = { tabPositions ->
                    TabRowDefaults.SecondaryIndicator(
                        modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                        height = 3.dp,
                        color = mintColor
                    )
                }
            ) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTabIndex == index,
                        onClick = { selectedTabIndex = index },
                        text = {
                            Text(
                                text = title.uppercase(),
                                fontSize = 14.sp,
                                fontWeight = if (selectedTabIndex == index) FontWeight.Bold else FontWeight.Normal,
                                color = if (selectedTabIndex == index) mintColor else Color.Gray
                            )
                        }
                    )
                }
            }

            // --- Ô tìm kiếm ---
            OutlinedTextField(
                value = keyword,
                onValueChange = { keyword = it },
                label = { Text("Nhập tên hoặc số hiệu biển báo") },
                trailingIcon = {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "Tìm kiếm")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
            )

            // --- Lọc dữ liệu theo loại + từ khóa ---
            val currentType = tabs[selectedTabIndex]
            val filtered = listBienBao.filter {
                (it.loai.equals(currentType, ignoreCase = true)) &&
                        (keyword.isBlank() || it.tenBienBao.contains(keyword, true) || it.soHieu.contains(keyword, true))
            }

            if (filtered.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Không tìm thấy biển báo nào", color = Color.Gray)
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 12.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(filtered) { bienBao ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth(),
                            elevation = CardDefaults.cardElevation(4.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White)
                        ) {
                            Row(
                                modifier = Modifier
                                    .padding(12.dp)
                                    .fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(
                                    painter = painterResource(id = bienBao.hinhAnh),
                                    contentDescription = bienBao.tenBienBao,
                                    modifier = Modifier
                                        .size(70.dp)
                                        .padding(end = 12.dp)
                                )

                                Column(
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Text(
                                        text = "${bienBao.soHieu} – ${bienBao.tenBienBao}",
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color(0xFF222222),
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = bienBao.noiDung,
                                        fontSize = 14.sp,
                                        color = Color(0xFF444444),
                                        lineHeight = 18.sp
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}


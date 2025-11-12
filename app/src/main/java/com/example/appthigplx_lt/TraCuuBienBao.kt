package com.example.appthigplx_lt

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TraCuuBienBao(navController: NavController) {
    val context = LocalContext.current
    val db = remember { MyDbHelper(context) }

    var listBienBao by remember { mutableStateOf(listOf<BienBao>()) }
    var keyword by remember { mutableStateOf("") }

    // Nạp dữ liệu biển báo
    LaunchedEffect(Unit) {
        db.createDefaultBienBao()
        listBienBao = db.getAllBienBao()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Tra Cứu Biển Báo") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.Search, contentDescription = "Quay lại")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            OutlinedTextField(
                value = keyword,
                onValueChange = { keyword = it },
                label = { Text("Nhập tên hoặc số hiệu biển báo") },
                trailingIcon = {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "Tìm kiếm")
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            val filtered = if (keyword.isBlank()) listBienBao
            else listBienBao.filter {
                it.tenBienBao.contains(keyword, ignoreCase = true) ||
                        it.soHieu.contains(keyword, ignoreCase = true)
            }

            LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                items(filtered) { bienBao ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFE8F5E9))
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(12.dp)
                        ) {
                            Image(
                                painter = painterResource(id = bienBao.hinhAnh),
                                contentDescription = bienBao.tenBienBao,
                                modifier = Modifier
                                    .size(80.dp)
                                    .padding(end = 12.dp)
                            )

                            Column {
                                Text(
                                    text = "${bienBao.soHieu} - ${bienBao.tenBienBao}",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = bienBao.noiDung,
                                    fontSize = 15.sp
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

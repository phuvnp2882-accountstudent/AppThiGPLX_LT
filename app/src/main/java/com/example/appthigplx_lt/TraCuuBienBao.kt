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
    )

package com.example.appthigplx_lt

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

data class MeoGroup(
    val title: String,
    val items: List<String>
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MeoOnThi(navController: NavController) {
    val mintColor = Color(0xFF00C4A7)

    val danhSachMeo = listOf(
        MeoGroup(
            "⚠️ Quy tắc giao thông & nhường đường", listOf(
                "Tránh xe ngược chiều thì nhường đường qua đường hẹp và nhường xe lên dốc.",
                "Đứng cách ray đường sắt 5m.",
                "Xe thiết kế nhỏ hơn 70km/h không được vào cao tốc.",
                "Trên cao tốc và trong hầm chỉ được dừng, đỗ ở nơi quy định.",
                "Nhường đường cho xe ưu tiên có tín hiệu còi, cờ, đèn.",
                "Không vượt xe khác trên đường vòng, khuất tầm nhìn.",
                "Giảm tốc độ, đi sát bên phải khi xe sau xin vượt.",
                "Dừng, đỗ xe cách lề đường không quá 0,25m.",
                "Xe buýt đang dừng đón trả khách thì giảm tốc độ và từ từ vượt qua."
            )
        ),
        MeoGroup(
            "🧳 Nghiệp vụ vận tải", listOf(
                "Không lái xe liên tục quá 4 giờ.",
                "Không làm việc 1 ngày của lái xe quá 10 giờ.",
                "Người kinh doanh vận tải không được tự ý thay đổi vị trí đón trả khách.",
                "Vận chuyển hàng nguy hiểm phải có giấy phép."
            )
        ),
        MeoGroup(
            "🏁 Kỹ thuật lái xe", listOf(
                "Xuống dốc dài nên dùng cả phanh trước và phanh sau để giảm tốc độ.",
                "Khởi hành xe số tự động cần đạp phanh chân hết hành trình.",
                "Khởi hành ô tô số sàn cần đạp côn hết hành trình.",
                "Qua đường sắt không rào chắn: hạ kính, tắt âm thanh, quan sát hai bên."
            )
        ),
        MeoGroup(
            "⚙️ Cấu tạo & sửa chữa", listOf(
                "Âm lượng của còi: 90dB đến 115dB.",
                "Hệ thống bôi trơn giúp giảm ma sát.",
                "Niên hạn ô tô trên 9 chỗ: 20 năm; ô tô tải: 25 năm.",
                "Ắc quy dùng để tích trữ điện năng."
            )
        ),
        MeoGroup(
            "🚦 Quy tắc & sa hình khác", listOf(
                "Không có vòng xuyến: xe vào trước – xe ưu tiên – đường ưu tiên – bên phải trống – rẽ phải – đi thẳng – rẽ trái.",
                "Có vòng xuyến: chưa vào thì ưu tiên bên phải; đã vào thì ưu tiên bên trái.",
                "Xe xuống dốc phải nhường xe đang lên dốc."
            )
        )
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "MẸO ÔN THI GPLX",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
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
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(Color(0xFFF6F8F7))
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(danhSachMeo) { group ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .border(1.dp, Color(0xFFE0E0E0), RoundedCornerShape(16.dp))
                        .background(Color.White)
                        .padding(16.dp)
                ) {
                    // Tiêu đề nhóm
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .width(5.dp)
                                .height(24.dp)
                                .clip(RoundedCornerShape(2.dp))
                                .background(mintColor)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = group.title,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = mintColor
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    // Danh sách mẹo
                    group.items.forEach { meo ->
                        Text(
                            text = "• $meo",
                            fontSize = 16.sp,
                            lineHeight = 22.sp,
                            color = Color.Black,
                            textAlign = TextAlign.Start,
                            modifier = Modifier.padding(bottom = 4.dp)
                        )
                    }
                }
            }
        }
    }
}

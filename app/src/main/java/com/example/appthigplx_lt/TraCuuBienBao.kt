package com.example.appthigplx_lt

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
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
    val danhSachMeo = listOf(
        MeoGroup(
            "⚠️ Quy tắc giao thông & nhường đường", listOf(
                "Tránh xe ngược chiều thì nhường đường qua đường hẹp và nhường xe lên dốc.",
                "Đứng cách ray đường sắt 5m.",
                "Vào cao tốc phải nhường đường cho xe đang chạy trên đường.",
                "Xe thiết kế nhỏ hơn 70km/h không được vào cao tốc.",
                "Trên cao tốc và trong hầm chỉ được dừng, đỗ ở nơi quy định.",
                "Xe quá tải trọng phải do cơ quan quản lý đường bộ cấp phép.",
                "Trọng lượng xe kéo rơ moóc phải lớn hơn rơ moóc.",
                "Kéo xe không có hệ thống hãm phải dùng thanh nối cứng.",
                "Xe gắn máy tối đa 40km/h.",
                "Xe cơ giới không bao gồm xe gắn máy.",
                "Đường có giải phân cách được xem là đường đôi.",
                "Giảm tốc độ, chú ý quan sát khi gặp biển báo nguy hiểm.",
                "Giảm tốc độ, đi sát bên phải khi xe sau xin vượt.",
                "Điểm giao cắt đường sắt thì ưu tiên đường sắt.",
                "Nhường đường cho xe ưu tiên có tín hiệu còi, cờ, đèn.",
                "Không vượt xe khác trên đường vòng, khuất tầm nhìn.",
                "Nơi có vạch kẻ đường dành cho người đi bộ thì nhường đường.",
                "Dừng, đỗ xe cách lề đường, hè phố không quá 0,25m.",
                "Dừng, đỗ xe trên đường hẹp cách xe khác 20m.",
                "Giảm tốc độ trên đường ướt, đường hẹp và đèo dốc.",
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
                "Xe mô tô xuống dốc dài cần sử dụng cả phanh trước và phanh sau để giảm tốc độ.",
                "Khởi hành xe ô tô số tự động cần đạp phanh chân hết hành trình.",
                "Thực hiện phanh tay cần bóp khóa hãm, đẩy cần phanh tay về phía trước.",
                "Khởi hành ô tô số sàn cần đạp côn hết hành trình.",
                "Thực hiện quay đầu xe với tốc độ thấp.",
                "Lái xe ô tô qua đường sắt không rào chắn thì cách 5m, hạ kính cửa, tắt âm thanh, quan sát.",
                "Mở cửa xe thì quan sát rồi mới mở hé cánh cửa."
            )
        ),
        MeoGroup(
            "⚙️ Cấu tạo & sửa chữa", listOf(
                "Yêu cầu của kính chắn gió, chọn loại kính an toàn.",
                "Âm lượng của còi là từ 90dB đến 115 dB.",
                "Động cơ diesel không nổ do nhiên liệu lẫn tạp chất.",
                "Dây đai an toàn có cơ cấu hãm giữ chặt dây khi giật dây đột ngột.",
                "Động cơ 4 kỳ thì pít-tông thực hiện 4 hành trình.",
                "Hệ thống bôi trơn giảm ma sát.",
                "Niên hạn ô tô trên 9 chỗ ngồi là 20 năm.",
                "Niên hạn ô tô tải là 25 năm.",
                "Động cơ ô tô biến nhiệt năng thành cơ năng.",
                "Hệ thống truyền lực truyền mô-men quay từ động cơ tới bánh xe.",
                "Ly hợp (côn) truyền hoặc ngắt truyền động từ động cơ đến hộp số.",
                "Hộp số ô tô đảm bảo chuyển động lùi.",
                "Hệ thống lái dùng để thay đổi hướng.",
                "Hệ thống phanh giúp giảm tốc độ.",
                "Ắc quy để tích trữ điện năng.",
                "Khởi động xe tự động phải đạp phanh."
            )
        ),
        MeoGroup(
            "🚦 Quy tắc & sa hình khác", listOf(
                "Không có vòng xuyến: xe vào ngã ba/ngã tư trước – xe ưu tiên – đường ưu tiên – đường cùng cấp theo thứ tự bên phải trống – rẽ phải – đi thẳng – rẽ trái.",
                "Có vòng xuyến: chưa vào vòng xuyến thì ưu tiên xe bên phải; đã vào vòng xuyến thì ưu tiên xe từ bên trái tới.",
                "Xe xuống dốc phải nhường đường cho xe đang lên dốc."
            )
        )
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mẹo Ôn Thi GPLX") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Quay lại"
                        )
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(danhSachMeo) { group ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(4.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant
                    )
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = group.title,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        group.items.forEach { meo ->
                            Text(
                                text = "• $meo",
                                fontSize = 16.sp,
                                lineHeight = 22.sp
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                        }
                    }
                }
            }
        }
    }
}

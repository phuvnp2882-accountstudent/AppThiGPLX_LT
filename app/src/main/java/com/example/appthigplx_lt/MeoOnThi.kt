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
            "📜 Cấp phép & Quy định", listOf(
                "Đường cấm dừng, cấm đỗ, cấm đi do UBND cấp tỉnh cấp",
                "Xe quá khổ, quá tải do cơ quan quản lý đường bộ có thẩm quyền cấp phép",
                "Người điều khiển xe mô tô, ô tô, máy kéo trên đường mà trong máu hoặc hơi thở có nồng độ cồn: Bị nghiêm cấm",
                "05 năm không cấp lại nếu sử dụng bằng lái đã khai báo mất",
                "Chỉ sử dụng còi từ 5 giờ sáng đến 22 giờ tối",
                "Trong đô thị sử dụng đèn chiếu gần",
                "Không được phép lắp đặt còi đèn không đúng thiết kế, trừ phi được chấp thuận của cơ quan có thẩm quyền",
                "Xe mô tô không được kéo xe khác",
                "Chuyển làn đường phải có tín hiệu báo trước",
                "Xe thô sơ phải đi làn đường bên phải trong cùng"
            )
        ),
        MeoGroup(
            "🚗 Tốc độ & Khoảng cách", listOf(
                "Khoảng cách an toàn: 35m (v=60km/h), 55m (60<v≤80), 70m (80<v≤100), 100m (100<v≤120)",
                "Dưới 60km/h: Chủ động và đảm bảo khoảng cách",
                "Tốc độ trong khu đông dân cư: 60km/h (đường đôi), 50km/h (đường 2 chiều)",
                "Tốc độ ngoài khu đông dân cư (đường đôi): 90km/h (xe con), 80km/h (xe >30 chỗ), 70km/h (xe buýt), 60km/h (xe téc)",
                "Tốc độ ngoài khu đông dân cư (đường 2 chiều): 80km/h (xe con), 70km/h (xe >30 chỗ), 60km/h (xe buýt), 50km/h (xe téc)",
                "Xe máy chuyên dùng, xe gắn máy: 40km/h",
                "Cao tốc tối đa: 120km/h",
                "Tốc độ chậm đi về bên phải"
            )
        ),
        MeoGroup(
            "👥 Quy tắc nhường đường", listOf(
                "Nhất chớm: Xe nào chớm tới vạch trước thì được đi trước",
                "Nhì ưu: Xe ưu tiên được đi trước. Thứ tự: Hỏa-Sự-An-Thương (Cứu hỏa - Quân sự - Công an - Cứu thương - Hộ đê - Đoàn xe tang)",
                "Tam đường: Xe ở đường chính, đường ưu tiên",
                "Tứ hướng: Thứ tự hướng: Bên phải trống - Rẽ phải - Đi thẳng - Rẽ trái",
                "Có vòng xuyến: Nhường đường bên trái",
                "Không có vòng xuyến: Nhường đường bên phải",
                "Xe xuống dốc phải nhường đường cho xe đang lên dốc",
                "Tránh xe ngược chiều: nhường đường qua đường hẹp và nhường xe lên dốc",
                "Nhường đường cho xe ưu tiên có tín hiệu còi, cờ, đèn"
            )
        ),
        MeoGroup(
            "🚫 Khu vực cấm & Hạn chế", listOf(
                "Trên cao tốc, trong hầm, đường vòng, đầu dốc, nơi tầm nhìn hạn chế: Không quay đầu, không lùi, không vượt",
                "Không được phép quay đầu xe ở phần đường dành cho người đi bộ qua đường",
                "Cấm lùi xe ở khu vực cấm dừng và nơi đường bộ giao nhau",
                "Không được vượt trên cầu hẹp có một làn xe",
                "Xe thiết kế nhỏ hơn 70km/h không được vào cao tốc",
                "Trên cao tốc và trong hầm chỉ được dừng, đỗ ở nơi quy định",
                "Đứng cách ray đường sắt 5m"
            )
        ),
        MeoGroup(
            "👨‍✈️ Tuổi & Hạng bằng lái", listOf(
                "Độ tuổi tối đa người lái xe ô tô trên 29 chỗ: Nam 57 tuổi và nữ 55 tuổi",
                "16 tuổi: Xe dưới 50cm³",
                "18 tuổi: Hạng A1, A, B1, B, C1",
                "21 tuổi: Hạng C, BE",
                "24 tuổi: Hạng D1, D2, C1E, CE",
                "27 tuổi: Hạng D, D1E, D2E, DE",
                "Hạng A1: mô tô hai bánh đến 125 cm³ hoặc đến 11 kW",
                "Hạng A: mô tô hai bánh trên 125 cm³",
                "Hạng B1: mô tô ba bánh",
                "Hạng B: đến 08 chỗ; ô tô tải đến 3.500 kg",
                "Hạng C1: ô tô tải 3.500 kg đến 7.500 kg",
                "Hạng C: ô tô tải trên 7.500 kg",
                "Hạng D1: 08-16 chỗ; Hạng D2: 16-29 chỗ; Hạng D: trên 29 chỗ"
            )
        ),
        MeoGroup(
            "⚙️ Kỹ thuật lái xe", listOf(
                "Tăng 1 Giảm 2 (giảm số chọn ý có từ 'vù ga')",
                "Xuống dốc dài dùng cả phanh trước và phanh sau",
                "Khởi hành xe số tự động: đạp phanh chân hết hành trình",
                "Khởi hành ô tô số sàn: đạp côn hết hành trình",
                "Thực hiện quay đầu xe với tốc độ thấp",
                "Qua đường sắt không rào chắn: cách 5m hạ kính, tắt âm thanh, quan sát",
                "Mở cửa xe: quan sát rồi mới mở hé cánh cửa",
                "Giảm tốc độ, đi sát về bên phải khi xe sau xin vượt",
                "Giảm tốc độ trên đường ướt, đường hẹp và đèo dốc"
            )
        ),
        MeoGroup(
            "🔧 Cấu tạo & Sửa chữa", listOf(
                "Âm lượng còi: 90dB đến 115dB",
                "Hệ thống bôi trơn giúp giảm ma sát",
                "Ắc quy dùng để tích trữ điện năng",
                "Yêu cầu kính chắn gió: Loại kính an toàn",
                "Động cơ diesel không nổ do nhiên liệu lẫn tạp chất",
                "Dây đai an toàn có cơ cấu hãm giữ chặt khi giật đột ngột",
                "Động cơ 4 kỳ: pít tông thực hiện 4 hành trình",
                "Động cơ ô tô biến nhiệt năng thành cơ năng",
                "Hệ thống truyền lực truyền mô men từ động cơ tới bánh xe",
                "Ly hợp (côn) truyền hoặc ngắt truyền động từ động cơ đến hộp số",
                "Hộp số ô tô đảm bảo chuyển động lùi",
                "Hệ thống lái dùng để thay đổi hướng",
                "Hệ thống phanh giúp giảm tốc độ",
                "Khởi động xe tự động phải đạp phanh"
            )
        ),
        MeoGroup(
            "⏳ Niên hạn & Thời gian", listOf(
                "Niên hạn ô tô trên 9 chỗ: 20 năm",
                "Niên hạn ô tô tải: 25 năm",
                "Không lái xe liên tục quá 4 giờ",
                "Không làm việc 1 ngày của lái xe quá 10 giờ"
            )
        ),
        MeoGroup(
            "🚦 Biển báo & Hiệu lệnh", listOf(
                "Biển nguy hiểm: hình tam giác vàng",
                "Biển cấm: vòng tròn đỏ",
                "Biển hiệu lệnh: vòng tròn xanh",
                "Biển chỉ dẫn: vuông/chữ nhật xanh",
                "Biển phụ: vuông/chữ nhật trắng đen - Hiệu lực nằm ở biển phụ khi có đặt biển phụ",
                "Giơ tay thẳng đứng: Tất cả dừng, trừ xe đã ở trong ngã tư được phép đi",
                "Giang ngang tay: Trái phải đi; Trước sau dừng",
                "Tay phải giơ trước: Sau, phải dừng; trước rẽ phải; trái đi các hướng; người đi bộ qua đường đi sau người điều khiển"
            )
        ),
        MeoGroup(
            "📦 Nghiệp vụ vận tải", listOf(
                "Người kinh doanh vận tải không được tự ý thay đổi vị trí đón trả khách",
                "Vận chuyển hàng nguy hiểm phải có giấy phép",
                "Xe buýt đang dừng đón trả khách: giảm tốc độ và từ từ vượt qua"
            )
        ),
        MeoGroup(
            "🛣️ Khái niệm & Quy tắc chung", listOf(
                "Tất cả các câu có đáp án 'bị nghiêm cấm', 'không cho phép' hoặc 'không được phép' thì chọn đáp án đó",
                "Phương tiện giao thông đường bộ gồm cơ giới và thô sơ",
                "Phương tiện tham gia giao thông đường bộ gồm phương tiện giao thông đường bộ và xe máy chuyên dùng",
                "Xe máy chuyên dùng gồm xe máy thi công, xe máy nông nghiệp, lâm nghiệp và các loại xe đặc chủng",
                "Đường có giải phân cách được xem là đường đôi",
                "Giảm tốc độ, chú ý quan sát khi gặp biển báo nguy hiểm",
                "Điểm giao cắt đường sắt thì ưu tiên đường sắt",
                "Nơi có vạch kẻ đường dành cho người đi bộ thì nhường đường",
                "Dừng xe, đỗ xe cách lề đường không quá 0,25 mét",
                "Dừng xe, đỗ xe trên đường hẹp cách xe khác 20 mét",
                "Vào cao tốc phải nhường đường cho xe đang chạy trên đường",
                "Xe quá tải trọng phải do cơ quan quản lý đường bộ cấp phép",
                "Trọng lượng xe kéo rơ moóc phải lớn hơn rơ moóc",
                "Kéo xe không hệ thống hãm phải dùng thanh nối cứng",
                "Xe cơ giới không bao gồm xe gắn máy"
            )
        ),
        MeoGroup(
            "🎯 Mẹo sa hình", listOf(
                "Thứ tự ưu tiên không vòng xuyến: Xe vào trước - Xe ưu tiên - Đường ưu tiên - Bên phải trống - Rẽ phải - Đi thẳng - Rẽ trái",
                "Giao nhau cùng cấp có vòng xuyến: Chưa vào thì ưu tiên xe bên phải; đã vào thì ưu tiên xe từ bên trái tới",
                "Xe xuống dốc phải nhường đường cho xe đang lên dốc",
                "Xe nào đã vào ngã tư thì được đi trước",
                "Xe trên đường ưu tiên được đi trước",
                "Hướng không có xe được đi trước"
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
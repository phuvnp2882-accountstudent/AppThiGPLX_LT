package com.example.appthigplx

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDbHelper(context: Context) :
    SQLiteOpenHelper(context, "DB_OnThiGPLX", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        // === BẢNG LÝ THUYẾT ===
        db.execSQL("""
            CREATE TABLE LyThuyet(
                CauHoi TEXT PRIMARY KEY,
                DapAn1 TEXT,
                DapAn2 TEXT,
                DapAn3 TEXT,
                DapAn4 TEXT,
                DapAnDung TEXT,
                ChuDe TEXT
            )
        """.trimIndent())

        // === BẢNG BIỂN BÁO ===
        db.execSQL("""
            CREATE TABLE BienBao(
                SoHieu TEXT PRIMARY KEY,
                TenBienBao TEXT,
                NoiDung TEXT,
                HinhAnh INTEGER
            )
        """.trimIndent())

        // === BẢNG LUẬT GIAO THÔNG ===
        db.execSQL("""
            CREATE TABLE Luat(
                MaLuat TEXT PRIMARY KEY,
                NoiDung TEXT
            )
        """.trimIndent())
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS LyThuyet")
        db.execSQL("DROP TABLE IF EXISTS BienBao")
        db.execSQL("DROP TABLE IF EXISTS Luat")
        onCreate(db)
    }

    // =====================================================
    // =============== LÝ THUYẾT GIAO THÔNG =================
    // =====================================================

    fun createDefaultLyThuyet() {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM LyThuyet", null)
        if (cursor.count == 0) {
            val list = listOf(
                LyThuyet(
                    cauHoi = "Phần của đường bộ được sử dụng cho các phương tiện giao thông qua lại là gì?",
                    dapAn1 = "Phần mặt đường và lề đường.",
                    dapAn2 = "Phần đường xe chạy.",
                    dapAn3 = "Phần đường xe cơ giới.",
                    dapAn4 = "",
                    dapAnDung = "2",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "‘“Làn đường” là gì?",
                    dapAn1 = "Là một phần của phần đường xe chạy được chia theo chiều dọc của đường, sử dụng cho xe chạy.",
                    dapAn2 = "Là một phần của phần đường xe chạy được chia theo chiều dọc của đường, có bề rộng đủ cho xe chạy an toàn.",
                    dapAn3 = "3.Là đường cho xe ô tô chạy, dừng, đỗ an toàn.",
                    dapAn4 = "",
                    dapAnDung = "2",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Trong các khái niệm dưới đây, “dải phân cách” được hiểu như thế nào là đúng?",
                    dapAn1 = "Là bộ phận của đường để ngăn cách không cho các loại xe vào những nơi không được phép.",
                    dapAn2 = "Là bộ phận của đường để phân tách phần đường xe chạy và hành lang an toàn giao thông.",
                    dapAn3 = "Là bộ phận của đường để phân chia mặt đường thành hai chiều xe chạy riêng biệt hoặc để phân chia phần đường của xe cơ giới và xe thô sơ.",
                    dapAn4 = "",
                    dapAnDung = "3",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "“Dải phân cách” trên đường bộ gồm những loại nào?",
                    dapAn1 = "Dải phân cách gồm loại cố định và loại di động.",
                    dapAn2 = "Dải phân cách gồm tường chống ồn, hộ lan cứng và hộ lan mềm.",
                    dapAn3 = "Dải phân cách gồm giá long môn và biển báo hiệu đường bộ.",
                    dapAn4 = "",
                    dapAnDung = "1",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "“Người lái xe được hiểu như thế nào trong các khái niệm dưới đây?",
                    dapAn1 = "Là người điều khiển xe cơ giới.",
                    dapAn2 = "Là người điều khiển xe thô sơ.",
                    dapAn3 = "Là người điều khiển xe có súc vật kéo.",
                    dapAn4 = "",
                    dapAnDung = "1",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Đường mà trên đó phương tiện tham gia giao thông được các phương tiện giao thông đến từ các hướng khác nhường đường khi qua nơi đường giao nhau, được cắm biển báo hiệu đường ưu tiên là loại đường gì?",
                    dapAn1 = "Đường không ưu tiên.",
                    dapAn2 = "Đường tỉnh lộ.",
                    dapAn3 = "Đường quốc lộ.",
                    dapAn4 = "Đường ưu tiên.",
                    dapAnDung = "4",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Khái niệm “phương tiện giao thông cơ giới đường bộ” được hiểu như thế nào là đúng?",
                    dapAn1 = "Gồm xe ô tô; máy kéo; xe mô tô hai bánh; xe mô tô ba bánh; xe gắn máy; xe cơ giới dùng cho người khuyết tật và xe máy chuyên dùng.",
                    dapAn2 = "Gồm ô tô; máy kéo; rơ moóc hoặc sơ mi rơ moóc được kéo bởi xe ô tô, máy kéo; xe mô tô hai bánh; xe mô tô ba bánh, xe gắn máy (kể cả xe máy điện) và các loại xe tương tự.",
                    dapAn3 = "",
                    dapAn4 = "",
                    dapAnDung = "2",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Khái niệm “phương tiện giao thông thô sơ đường bộ” được hiểu như thế nào là đúng?",
                    dapAn1 = "Gồm xe đạp (kể cả xe đạp máy, xe đạp điện), xe xích lô, xe lăn dùng cho người khuyết tật, xe súc vật kéo và các loại xe tương tự.",
                    dapAn2 = "Gồm xe đạp (kể cả xe đạp máy, xe đạp điện), xe gắn máy, xe cơ giới dùng cho người khuyết tật và xe máy chuyên dùng.",
                    dapAn3 = "Gồm xe ô tô, máy kéo, rơ moóc hoặc sơ mi rơ moóc được kéo bởi xe ô tô, máy kéo.",
                    dapAn4 = "",
                    dapAnDung = "1",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "“Phương tiện tham gia giao thông đường bộ” gồm những loại nào?",
                    dapAn1 = "Phương tiện giao thông cơ giới đường bộ.",
                    dapAn2 = "Phương tiện giao thông thô sơ đường bộ và xe máy chuyên dùng.",
                    dapAn3 = "Cả ý 1 và ý 2.",
                    dapAn4 = "",
                    dapAnDung = "3",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "“Người tham gia giao thông đường bộ” gồm những đối tượng nào?",
                    dapAn1 = "Người điều khiển, người sử dụng phương tiện tham gia giao thông đường bộ.",
                    dapAn2 = "Người điều khiển, dẫn dắt súc vật; người đi bộ trên đường bộ.",
                    dapAn3 = "Cả ý 1 và ý 2.",
                    dapAn4 = "",
                    dapAnDung = "3",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "“Người điều khiển phương tiện tham gia giao thông đường bộ” gồm những đối tượng nào dưới đây?",
                    dapAn1 = "Người điều khiển xe cơ giới, người điều khiển xe thô sơ.",
                    dapAn2 = "Người điều khiển xe máy chuyên dùng tham gia giao thông đường bộ.",
                    dapAn3 = "Cả ý 1 và ý 2.",
                    dapAn4 = "",
                    dapAnDung = "3",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Khái niệm “người điều khiển giao thông” được hiểu như thế nào là đúng?",
                    dapAn1 = "Là người điều khiển phương tiện tham gia giao thông tại nơi thi công, nơi ùn tắc giao thông, ở bến phà, tại cầu đường bộ đi chung với đường sắt.",
                    dapAn2 = "Là cảnh sát giao thông, người được giao nhiệm vụ hướng dẫn giao thông tại nơi thi công, nơi ùn tắc giao thông, ở bến phà, tại cầu đường bộ đi chung với đường sắt.",
                    dapAn3 = "Là người tham gia giao thông tại nơi thi công, nơi ùn tắc giao thông, ở bến phà, tại cầu đường bộ đi chung với đường sắt.",
                    dapAn4 = "",
                    dapAnDung = "2",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Trong các khái niệm dưới đây, khái niệm “dừng xe” được hiểu như thế nào là đúng?",
                    dapAn1 = "Là trạng thái đứng yên của phương tiện giao thông không giới hạn thời gian để cho người lên, xuống phương tiện, xếp dỡ hàng hóa hoặc thực hiện công việc khác.",
                    dapAn2 = "Là trạng thái đứng yên tạm thời của phương tiện giao thông trong một khoảng thời gian cần thiết đủ để cho người lên, xuống phương tiện, xếp dỡ hàng hóa hoặc thực hiện công việc khác.",
                    dapAn3 = "Là trạng thái đứng yên của phương tiện giao thông không giới hạn thời gian giữa 02 lần vận chuyển hàng hóa hoặc hành khách.",
                    dapAn4 = "",
                    dapAnDung = "2",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Khái niệm “đỗ xe” được hiểu như thế nào là đúng?",
                    dapAn1 = "Là trạng thái đứng yên của phương tiện giao thông có thời hạn trong một khoảng thời gian cần thiết đủ để cho người lên, xuống phương tiện, xếp dỡ hàng hóa hoặc thực hiện công việc khác.",
                    dapAn2 = "Là trạng thái đứng yên của phương tiện giao thông không giới hạn thời gian.",
                    dapAn3 = "",
                    dapAn4 = "",
                    dapAnDung = "2",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Cuộc đua xe chỉ được thực hiện khi nào?",
                    dapAn1 = "Diễn ra trên đường phố không có người qua lại.",
                    dapAn2 = "Được người dân ủng hộ.",
                    dapAn3 = "Được cơ quan có thẩm quyền cấp phép.",
                    dapAn4 = "",
                    dapAnDung = "3",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Người điều khiển phương tiện giao thông đường bộ mà trong cơ thể có chất ma túy có bị nghiêm cấm hay không?",
                    dapAn1 = "Bị nghiêm cấm.",
                    dapAn2 = "Không bị nghiêm cấm.",
                    dapAn3 = "Không bị nghiêm cấm, nếu có chất ma túy ở mức nhẹ, có thể điều khiển phương tiện tham gia giao thông.",
                    dapAn4 = "",
                    dapAnDung = "1",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Sử dụng rượu, bia khi lái xe, nếu bị phát hiện thì bị xử lý như thế nào?",
                    dapAn1 = "Chỉ bị nhắc nhở.",
                    dapAn2 = "Bị xử phạt hành chính hoặc có thể bị xử lý hình sự tùy theo mức độ vi phạm.",
                    dapAn3 = "Không bị xử lý hình sự.",
                    dapAn4 = "",
                    dapAnDung = "2",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Theo luật phòng chống tác hại của rượu, bia, đối tượng nào dưới đây bị cấm sử dụng rượu, bia khi tham gia giao thông?",
                    dapAn1 = "Người điều khiển: Xe ô tô, xe mô tô, xe đạp, xe gắn máy.",
                    dapAn2 = "Người ngồi phía sau người điều khiển xe cơ giới.",
                    dapAn3 = "Người đi bộ.",
                    dapAn4 = "Cả ý 1 và ý 2.",
                    dapAnDung = "1",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Hành vi điều khiển xe cơ giới chạy quá tốc độ quy định, giành đường, vượt ẩu có bị nghiêm cấm hay không?",
                    dapAn1 = "Bị nghiêm cấm tùy trường hợp.",
                    dapAn2 = "Không bị nghiêm cấm.",
                    dapAn3 = "Bị nghiêm cấm.",
                    dapAn4 = "",
                    dapAnDung = "3",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Khi lái xe trong khu đô thị và đông dân cư, trừ các khu vực có biển cấm sử dụng còi, người lái xe được sử dụng còi như thế nào trong các trường hợp dưới đây?",
                    dapAn1 = "Từ 22 giờ đêm đến 5 giờ sáng.",
                    dapAn2 = "Từ 5 giờ sáng đến 22 giờ tối.",
                    dapAn3 = "Từ 23 giờ đêm đến 5 giờ sáng hôm sau.",
                    dapAn4 = "",
                    dapAnDung = "2",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Người lái xe sử dụng đèn như thế nào khi lái xe trong khu đô thị và đông dân cư vào ban đêm?",
                    dapAn1 = "Bất cứ đèn nào miễn là mắt nhìn rõ phía trước.",
                    dapAn2 = "Chỉ bật đèn chiếu xa (đèn pha) khi không nhìn rõ đường.",
                    dapAn3 = "Đèn chiếu xa (đèn pha) khi đường vắng, đèn pha chiếu gần (đèn cốt) khi có xe đi ngược chiều.",
                    dapAn4 = "Đèn chiếu gần (đèn cốt).",
                    dapAnDung = "4",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Trong trường hợp đặc biệt, để được lắp đặt, sử dụng còi, đèn không đúng với thiết kế của nhà sản xuất đối với từng loại xe cơ giới, bạn phải đảm bảo yêu cầu nào dưới đây?",
                    dapAn1 = "Phải đảm bảo phụ tùng do đúng nhà sản xuất đó cung cấp.",
                    dapAn2 = "Phải được chấp thuận của cơ quan có thẩm quyền.",
                    dapAn3 = "Phải là xe đăng ký và hoạt động tại các khu vực có địa hình phức tạp.",
                    dapAn4 = "",
                    dapAnDung = "2",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Ở phần đường dành cho người đi bộ qua đường, trên cầu, đầu cầu, đường cao tốc, đường hẹp, đường dốc, tại nơi đường bộ giao nhau cùng mức với đường sắt có được quay đầu xe hay không?",
                    dapAn1 = "Được phép.",
                    dapAn2 = "Không được phép.",
                    dapAn3 = "Tùy từng trường hợp.",
                    dapAn4 = "",
                    dapAnDung = "2",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Bạn đang lái xe phía trước có một xe cảnh sát giao thông không phát tín hiệu ưu tiên, bạn có được phép vượt hay không?",
                    dapAn1 = "Không được vượt.",
                    dapAn2 = "Được vượt khi đang đi trên cầu.",
                    dapAn3 = "Được phép vượt khi đi qua nơi giao nhau có ít phương tiện cùng tham gia giao thông.",
                    dapAn4 = "Được vượt khi đảm bảo an toàn.",
                    dapAnDung = "4",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Bạn đang lái xe phía trước có một xe cứu thương đang phát tín hiệu ưu tiên, bạn có được phép vượt hay không?",
                    dapAn1 = "Không được vượt.",
                    dapAn2 = "Được vượt khi đang đi trên cầu.",
                    dapAn3 = "Được phép vượt khi đi qua nơi giao nhau có ít phương tiện cùng tham gia giao thông.",
                    dapAn4 = "Được vượt khi đảm bảo an toàn.",
                    dapAnDung = "1",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Người điều khiển xe mô tô hai bánh, ba bánh, xe gắn máy có được phép sử dụng xe để kéo hoặc đẩy các phương tiện khác khi tham gia giao thông không?",
                    dapAn1 = "Được phép.",
                    dapAn2 = "Nếu phương tiện được kéo, đẩy có khối lượng nhỏ hơn phương tiện của mình.",
                    dapAn3 = "Tùy trường hợp.",
                    dapAn4 = "Không được phép.",
                    dapAnDung = "4",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Khi điều khiển xe mô tô hai bánh, xe mô tô ba bánh, xe gắn máy, những hành vi buông cả hai tay; sử dụng xe để kéo, đẩy xe khác, vật khác; sử dụng chân chống của xe quệt xuống đường khi xe đang chạy có được phép hay không?",
                    dapAn1 = "Được phép.",
                    dapAn2 = "Tùy trường hợp.",
                    dapAn3 = "Không được phép.",
                    dapAn4 = "",
                    dapAnDung = "3",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Khi điều khiển xe mô tô hai bánh, xe mô tô ba bánh, xe gắn máy, những hành vi nào không được phép?",
                    dapAn1 = "Buông cả hai tay; sử dụng xe để kéo, đẩy xe khác, vật khác; sử dụng chân chống của xe quệt xuống đường khi xe đang chạy.",
                    dapAn2 = "Buông một tay; sử dụng xe để chở người hoặc hàng hóa; để chân chạm xuống đất khi khởi hành.",
                    dapAn3 = "Đội mũ bảo hiểm; chạy xe đúng tốc độ quy định và chấp hành đúng quy tắc giao thông đường bộ.",
                    dapAn4 = "Chở người ngồi sau dưới 16 tuổi.",
                    dapAnDung = "1",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Người ngồi trên xe mô tô hai bánh, xe mô tô ba bánh, xe gắn máy khi tham gia giao thông có được mang, vác vật cồng kềnh hay không?",
                    dapAn1 = "Được mang, vác tùy trường hợp cụ thể.",
                    dapAn2 = "Không được mang, vác.",
                    dapAn3 = "Được mang, vác nhưng phải đảm bảo an toàn.",
                    dapAn4 = "Được mang, vác tùy theo sức khỏe của bản thân.",
                    dapAnDung = "2",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Người ngồi trên xe mô tô hai bánh, xe mô tô ba bánh, xe gắn máy khi tham gia giao thông có được bám, kéo hoặc đẩy các phương tiện khác không?",
                    dapAn1 = "Được phép.",
                    dapAn2 = "Được bám trong trường hợp phương tiện của mình bị hỏng.",
                    dapAn3 = "Được kéo, đẩy trong trường hợp phương tiện khác bị hỏng.",
                    dapAn4 = "Không được phép.",
                    dapAnDung = "4",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Người ngồi trên xe mô tô hai bánh, xe mô tô ba bánh, xe gắn máy khi tham gia giao thông có được sử dụng ô khi trời mưa hay không?",
                    dapAn1 = "Được sử dụng.",
                    dapAn2 = "Chỉ người ngồi sau được sử dụng.",
                    dapAn3 = "Không được sử dụng.",
                    dapAn4 = "Được sử dụng nếu không có áo mưa.",
                    dapAnDung = "3",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Khi đang lên dốc, người ngồi trên xe mô tô có được kéo theo người đang điều khiển xe đạp hay không?",
                    dapAn1 = "Chỉ được phép nếu cả hai đội mũ bảo hiểm.",
                    dapAn2 = "Không được phép.",
                    dapAn3 = "Chỉ được thực hiện trên đường thật vắng.",
                    dapAn4 = "Chỉ được phép khi người đi xe đạp đã quá mệt.",
                    dapAnDung = "2",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Hành vi sử dụng xe mô tô để kéo, đẩy xe mô tô khác bị hết xăng đến trạm mua xăng có được phép hay không?",
                    dapAn1 = "Chỉ được kéo nếu đã nhìn thấy trạm xăng.",
                    dapAn2 = "Chỉ được thực hiện trên đường vắng phương tiện cùng tham gia giao thông.",
                    dapAn3 = "Không được phép.",
                    dapAn4 = "",
                    dapAnDung = "3",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Hành vi vận chuyển đồ vật cồng kềnh bằng xe mô tô, xe gắn máy khi tham gia giao thông có được phép hay không?",
                    dapAn1 = "Không được vận chuyển.",
                    dapAn2 = "Chỉ được vận chuyển khi đã chằng buộc cẩn thận.",
                    dapAn3 = "Chỉ được vận chuyển vật cồng kềnh trên xe máy nếu khoảng cách về nhà ngắn hơn 2 km.",
                    dapAn4 = "",
                    dapAnDung = "1",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Người đủ bao nhiêu tuổi trở lên thì được điều khiển xe mô tô hai bánh, xe mô tô ba bánh có dung tích xi lanh từ 50 cm3 trở lên và các loại xe có kết cấu tương tự; xe ô tô tải, máy kéo có trọng tải dưới 3,5 tấn; xe ô tô chở người đến 9 chỗ ngồi?",
                    dapAn1 = "16 tuổi.",
                    dapAn2 = "18 tuổi.",
                    dapAn3 = "17 tuổi.",
                    dapAn4 = "",
                    dapAnDung = "2",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Biển nào cấm máy kéo đi vào?",
                    dapAn1 = "Biển 1.",
                    dapAn2 = "Biển 2 và Biển 3.",
                    dapAn3 = "Biển 1 và Biển 3.",
                    dapAn4 = "Cả ba biển.",
                    dapAnDung = "2",
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Biển nào báo hiệu cấm xe mô tô ba bánh đi vào?",
                    dapAn1 = "Biển 1 và Biển 2.",
                    dapAn2 = "Biển 1 và Biển 3.",
                    dapAn3 = "Biển 2 và Biển 3.",
                    dapAn4 = "",
                    dapAnDung = "1",
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Biển nào dưới đây xe gắn máy được phép đi vào?",
                    dapAn1 = "Biển 1.",
                    dapAn2 = "Biển 2.",
                    dapAn3 = "Cả hai biển.",
                    dapAn4 = "",
                    dapAnDung = "2",
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Biển nào báo hiệu cấm xe mô tô hai bánh đi vào?",
                    dapAn1 = "Biển 1.",
                    dapAn2 = "Biển 2.",
                    dapAn3 = "Biển 3.",
                    dapAn4 = "",
                    dapAnDung = "1",
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Gặp biển nào thì xe mô tô hai bánh được đi vào?",
                    dapAn1 = "Không biển nào.",
                    dapAn2 = "Biển 1 và Biển 2.",
                    dapAn3 = "Biển 2 và Biển 3.",
                    dapAn4 = "Cả ba biển.",
                    dapAnDung = "3",
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Biển nào cấm quay xe?",
                    dapAn1 = "Biển 1",
                    dapAn2 = "Biển 2",
                    dapAn3 = "Không biển nào",
                    dapAn4 = "Cả hai biển",
                    dapAnDung = "2",
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Biển nào báo hiệu cấm mô tô và xe gắn máy đi vào?",
                    dapAn1 = "Biển 1",
                    dapAn2 = "Biển 2",
                    dapAn3 = "Cả hai biển",
                    dapAn4 = "",
                    dapAnDung = "3",
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Biển nào dưới đây báo hiệu cấm xe mô tô ba bánh đi vào?",
                    dapAn1 = "Biển 1",
                    dapAn2 = "Biển 2",
                    dapAn3 = "Biển 3",
                    dapAn4 = "",
                    dapAnDung = "1",
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Biển nào cấm đi ngược chiều?",
                    dapAn1 = "Biển 1",
                    dapAn2 = "Biển 2",
                    dapAn3 = "Cả hai biển",
                    dapAn4 = "",
                    dapAnDung = "1",
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Biển nào cấm xe công nông đi vào?",
                    dapAn1 = "Biển 1 và 3",
                    dapAn2 = "Biển 2 và 3",
                    dapAn3 = "Biển 1 và 2",
                    dapAn4 = "",
                    dapAnDung = "2",
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Biển nào cấm xe tải đi vào?",
                    dapAn1 = "Biển 1",
                    dapAn2 = "Biển 2",
                    dapAn3 = "Biển 3",
                    dapAn4 = "",
                    dapAnDung = "2",
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Biển nào dưới đây cấm xe mô tô hai bánh đi vào?",
                    dapAn1 = "Biển 1",
                    dapAn2 = "Biển 2",
                    dapAn3 = "Biển 3",
                    dapAn4 = "",
                    dapAnDung = "3",
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Biển nào báo hiệu cấm các loại xe cơ giới đi vào trừ xe gắn máy và xe thô sơ?",
                    dapAn1 = "Biển 1",
                    dapAn2 = "Biển 2",
                    dapAn3 = "Biển 3",
                    dapAn4 = "",
                    dapAnDung = "1",
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Biển nào cấm mô tô ba bánh đi vào?",
                    dapAn1 = "Biển 1 và 2",
                    dapAn2 = "Biển 1 và 3",
                    dapAn3 = "Biển 2 và 3",
                    dapAn4 = "",
                    dapAnDung = "1",
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Biển nào dưới đây cấm tất cả các loại phương tiện cơ giới đi vào trừ xe mô tô hai bánh?",
                    dapAn1 = "Biển 1",
                    dapAn2 = "Biển 2",
                    dapAn3 = "Biển 3",
                    dapAn4 = "",
                    dapAnDung = "2",
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Biển nào dưới đây cấm xe mô tô ba bánh đi vào?",
                    dapAn1 = "Biển 1",
                    dapAn2 = "Biển 2",
                    dapAn3 = "Biển 3",
                    dapAn4 = "",
                    dapAnDung = "1",
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Gặp biển nào thì người lái xe được phép quay đầu?",
                    dapAn1 = "Biển 1",
                    dapAn2 = "Biển 2",
                    dapAn3 = "Không biển nào",
                    dapAn4 = "",
                    dapAnDung = "1",
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Biển nào cho phép rẽ trái?",
                    dapAn1 = "Biển 1",
                    dapAn2 = "Biển 2",
                    dapAn3 = "Biển 3",
                    dapAn4 = "",
                    dapAnDung = "2",
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Biển nào cấm xe chở hàng nguy hiểm đi vào?",
                    dapAn1 = "Biển 1",
                    dapAn2 = "Biển 2",
                    dapAn3 = "Biển 3",
                    dapAn4 = "",
                    dapAnDung = "3",
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Biển nào báo hiệu đường cấm đi cả hai hướng?",
                    dapAn1 = "Biển 1",
                    dapAn2 = "Biển 2",
                    dapAn3 = "Biển 3",
                    dapAn4 = "",
                    dapAnDung = "1",
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Biển nào sau đây cấm taxi đi vào?",
                    dapAn1 = "Biển 1",
                    dapAn2 = "Biển 2",
                    dapAn3 = "Biển 3",
                    dapAn4 = "",
                    dapAnDung = "3",
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Các biển nào dưới đây cấm xe ô tô tải?",
                    dapAn1 = "Biển 1 và 2",
                    dapAn2 = "Biển 2 và 3",
                    dapAn3 = "Biển 1 và 3",
                    dapAn4 = "",
                    dapAnDung = "1",
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Biển nào báo hiệu cấm xe kéo rơ-moóc đi vào?",
                    dapAn1 = "Biển 1",
                    dapAn2 = "Biển 2",
                    dapAn3 = "Biển 3",
                    dapAn4 = "",
                    dapAnDung = "2",
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Khi gặp biển này, phương tiện nào được phép đi vào?",
                    dapAn1 = "Chỉ xe mô tô hai bánh",
                    dapAn2 = "Chỉ xe thô sơ",
                    dapAn3 = "Xe mô tô hai bánh và xe thô sơ",
                    dapAn4 = "",
                    dapAnDung = "2",
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Biển nào sau đây báo hiệu cấm ô tô và xe máy đi vào?",
                    dapAn1 = "Biển 1",
                    dapAn2 = "Biển 2",
                    dapAn3 = "Biển 3",
                    dapAn4 = "",
                    dapAnDung = "1",
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Biển nào sau đây báo hiệu cấm người đi bộ?",
                    dapAn1 = "Biển 1",
                    dapAn2 = "Biển 2",
                    dapAn3 = "Biển 3",
                    dapAn4 = "",
                    dapAnDung = "2",
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Các biển nào dưới đây cấm xe mô tô ba bánh đi vào?",
                    dapAn1 = "Biển 1 và 2",
                    dapAn2 = "Biển 2 và 3",
                    dapAn3 = "Biển 1 và 3",
                    dapAn4 = "",
                    dapAnDung = "1",
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Biển nào cấm xe thô sơ?",
                    dapAn1 = "Biển 1",
                    dapAn2 = "Biển 2",
                    dapAn3 = "Biển 3",
                    dapAn4 = "",
                    dapAnDung = "3",
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Các biển nào dưới đây báo hiệu cấm xe ô tô khách?",
                    dapAn1 = "Biển 1 và 3",
                    dapAn2 = "Biển 2 và 3",
                    dapAn3 = "Biển 1 và 2",
                    dapAn4 = "",
                    dapAnDung = "3",
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Các biển nào dưới đây cấm rẽ trái?",
                    dapAn1 = "Biển 1",
                    dapAn2 = "Biển 2",
                    dapAn3 = "Biển 3",
                    dapAn4 = "",
                    dapAnDung = "2",
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Biển nào dưới đây báo hiệu cấm xe mô tô hai bánh và mô tô ba bánh đi vào?",
                    dapAn1 = "Biển 1",
                    dapAn2 = "Biển 2",
                    dapAn3 = "Biển 3",
                    dapAn4 = "",
                    dapAnDung = "1",
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Biển nào cấm quay đầu xe?",
                    dapAn1 = "Biển 1",
                    dapAn2 = "Biển 2",
                    dapAn3 = "Không biển nào",
                    dapAn4 = "",
                    dapAnDung = "1",
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Biển nào dưới đây báo hiệu chiều dài đoạn đường hạn chế?",
                    dapAn1 = "Biển 1",
                    dapAn2 = "Biển 2",
                    dapAn3 = "Biển 3",
                    dapAn4 = "",
                    dapAnDung = "2",
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Biển nào dưới đây cấm xe ô tô rẽ trái?",
                    dapAn1 = "Biển 1",
                    dapAn2 = "Biển 2",
                    dapAn3 = "Biển 3",
                    dapAn4 = "",
                    dapAnDung = "3",
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Biển nào báo hiệu cấm đi thẳng và cấm rẽ trái?",
                    dapAn1 = "Biển 1",
                    dapAn2 = "Biển 2",
                    dapAn3 = "Biển 3",
                    dapAn4 = "",
                    dapAnDung = "2",
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Biển nào dưới đây báo hiệu chiều cao tối đa cho phép?",
                    dapAn1 = "Biển 1",
                    dapAn2 = "Biển 2",
                    dapAn3 = "Biển 3",
                    dapAn4 = "",
                    dapAnDung = "1",
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Biển nào dưới đây báo hiệu cấm xe ô tô vượt?",
                    dapAn1 = "Biển 1",
                    dapAn2 = "Biển 2",
                    dapAn3 = "Cả hai biển",
                    dapAn4 = "",
                    dapAnDung = "3",
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Biển nào báo hiệu cấm xe ô tô và xe mô tô đi vào?",
                    dapAn1 = "Biển 1",
                    dapAn2 = "Biển 2",
                    dapAn3 = "Biển 3",
                    dapAn4 = "",
                    dapAnDung = "1",
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Biển nào sau đây báo hiệu giới hạn trọng lượng toàn bộ xe?",
                    dapAn1 = "Biển 1",
                    dapAn2 = "Biển 2",
                    dapAn3 = "Biển 3",
                    dapAn4 = "",
                    dapAnDung = "2",
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Biển nào dưới đây báo hiệu hạn chế tốc độ tối đa cho phép?",
                    dapAn1 = "Biển 1",
                    dapAn2 = "Biển 2",
                    dapAn3 = "Biển 3",
                    dapAn4 = "",
                    dapAnDung = "1",
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Biển nào dưới đây báo hiệu cấm xe mô tô và xe gắn máy đi vào?",
                    dapAn1 = "Biển 1",
                    dapAn2 = "Biển 2",
                    dapAn3 = "Biển 3",
                    dapAn4 = "",
                    dapAnDung = "1",
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Biển nào báo hiệu đường cấm xe máy chuyên dùng?",
                    dapAn1 = "Biển 1",
                    dapAn2 = "Biển 2",
                    dapAn3 = "Biển 3",
                    dapAn4 = "",
                    dapAnDung = "3",
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Biển nào báo hiệu cấm ô tô con đi vào?",
                    dapAn1 = "Biển 1",
                    dapAn2 = "Biển 2",
                    dapAn3 = "Biển 3",
                    dapAn4 = "",
                    dapAnDung = "2",
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Biển nào báo hiệu cấm xe chở hàng nguy hiểm đi vào?",
                    dapAn1 = "Biển 1",
                    dapAn2 = "Biển 2",
                    dapAn3 = "Biển 3",
                    dapAn4 = "",
                    dapAnDung = "3",
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Các biển nào dưới đây cấm ô tô khách đi vào?",
                    dapAn1 = "Biển 1",
                    dapAn2 = "Biển 2",
                    dapAn3 = "Biển 3",
                    dapAn4 = "Cả ba biển",
                    dapAnDung = "3",
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Biển báo nào dưới đây thuộc nhóm biển báo chỉ dẫn?",
                    dapAn1 = "Biển báo màu xanh dương hình chữ nhật hoặc hình vuông.",
                    dapAn2 = "Biển báo hình tam giác viền đỏ.",
                    dapAn3 = "Biển báo hình tròn viền đỏ.",
                    dapAn4 = "",
                    dapAnDung = "1",
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Biển báo hình tròn viền đỏ thường mang ý nghĩa gì?",
                    dapAn1 = "Chỉ dẫn.",
                    dapAn2 = "Nguy hiểm.",
                    dapAn3 = "Cấm hoặc hạn chế.",
                    dapAn4 = "",
                    dapAnDung = "3",
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Biển báo hình tam giác viền đỏ, nền vàng là loại biển gì?",
                    dapAn1 = "Biển hiệu lệnh.",
                    dapAn2 = "Biển chỉ dẫn.",
                    dapAn3 = "Biển cảnh báo nguy hiểm.",
                    dapAn4 = "",
                    dapAnDung = "3",
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Biển báo nào yêu cầu người lái xe phải tuân thủ đúng theo hướng chỉ dẫn trên biển?",
                    dapAn1 = "Biển cấm.",
                    dapAn2 = "Biển chỉ dẫn.",
                    dapAn3 = "Biển hiệu lệnh.",
                    dapAn4 = "",
                    dapAnDung = "3",
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Biển báo hiệu lệnh thường có dạng hình gì?",
                    dapAn1 = "Hình tam giác viền đỏ.",
                    dapAn2 = "Hình tròn màu xanh lam.",
                    dapAn3 = "Hình vuông hoặc hình chữ nhật nền xanh.",
                    dapAn4 = "",
                    dapAnDung = "2",
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Những hành vi nào sau đây thể hiện là người có văn hóa giao thông?",
                    dapAn1 = "Luôn tuân thủ pháp luật về trật tự, an toàn giao thông đường bộ, nhường nhịn và giúp đỡ người khác.",
                    dapAn2 = "Đi nhanh, vượt đèn đỏ nếu không có lực lượng Công an.",
                    dapAn3 = "Bấm còi và nháy đèn liên tục để cảnh báo xe khác.",
                    dapAn4 = "Tránh nhường đường để đi nhanh hơn.",
                    dapAnDung = "1",
                    chuDe = "Văn hóa và đạo đức lái xe"
                ),

                LyThuyet(
                    cauHoi = "Khái niệm về văn hóa giao thông được hiểu như thế nào là đúng?",
                    dapAn1 = "Là sự hiểu biết và chấp hành nghiêm chỉnh pháp luật về giao thông, là ý thức trách nhiệm với cộng đồng khi tham gia giao thông.",
                    dapAn2 = "Là sự tôn trọng, nhường nhịn, giúp đỡ và ứng xử có văn hóa giữa những người tham gia giao thông với nhau.",
                    dapAn3 = "Cả hai ý trên.",
                    dapAn4 = "",
                    dapAnDung = "3",
                    chuDe = "Văn hóa và đạo đức lái xe"
                ),

                LyThuyet(
                    cauHoi = "Người lái xe không điều khiển xe đi đúng làn đường quy định, phóng nhanh, vượt ẩu, vượt đèn đỏ, đi vào đường cấm được coi là hành vi nào?",
                    dapAn1 = "Là thiếu văn hóa giao thông, vi phạm pháp luật về trật tự, an toàn giao thông đường bộ.",
                    dapAn2 = "Là thiếu văn hóa giao thông.",
                    dapAn3 = "",
                    dapAn4 = "",
                    dapAnDung = "1",
                    chuDe = "Văn hóa và đạo đức lái xe"
                ),

                LyThuyet(
                    cauHoi = "Người lái xe có văn hóa giao thông khi tham gia giao thông đường bộ phải đáp ứng các điều kiện nào?",
                    dapAn1 = "Hiểu biết và chấp hành nghiêm chỉnh pháp luật về giao thông đường bộ; có ý thức trách nhiệm với cộng đồng khi tham gia giao thông; tôn trọng, nhường nhịn, giúp đỡ và ứng xử có văn hóa với những người cùng tham gia giao thông.",
                    dapAn2 = "Điều khiển xe vượt quá tốc độ, đi không đúng làn đường.",
                    dapAn3 = "",
                    dapAn4 = "",
                    dapAnDung = "1",
                    chuDe = "Văn hóa và đạo đức lái xe"
                ),

                LyThuyet(
                    cauHoi = "Người lái xe mô tô có văn hóa giao thông khi tham gia giao thông phải tuân thủ những quy định nào?",
                    dapAn1 = "Điều khiển xe đi bên phải theo chiều đi của mình; đi đúng phần đường, làn đường quy định; đội mũ bảo hiểm đúng quy chuẩn kỹ thuật quốc gia, cài quai đúng quy cách.",
                    dapAn2 = "Điều khiển xe đi trên phần đường, làn đường có ít phương tiện tham gia giao thông.",
                    dapAn3 = "Điều khiển xe và đội mũ bảo hiểm ở nơi có biển báo bắt buộc đội mũ bảo hiểm.",
                    dapAn4 = "",
                    dapAnDung = "1",
                    chuDe = "Văn hóa và đạo đức lái xe"
                ),

                LyThuyet(
                    cauHoi = "Trong các hành vi dưới đây, người lái xe có văn hóa giao thông phải ứng xử như thế nào?",
                    dapAn1 = "Điều khiển xe đi bên phải theo chiều đi của mình; đi đúng phần đường, làn đường quy định; dừng, đỗ xe đúng nơi quy định; đã uống rượu, bia thì không lái xe.",
                    dapAn2 = "Điều khiển xe đi trên phần đường, làn đường có ít phương tiện giao thông; dừng xe, đỗ xe ở nơi thuận tiện hoặc theo yêu cầu của hành khách, của người thân.",
                    dapAn3 = "Dừng và đỗ xe ở nơi thuận tiện cho việc chuyên chở hành khách và giao nhận hàng hóa; sử dụng ít rượu, bia thì có thể lái xe.",
                    dapAn4 = "",
                    dapAnDung = "1",
                    chuDe = "Văn hóa và đạo đức lái xe"
                ),

                LyThuyet(
                    cauHoi = "Khi tham gia giao thông, việc sử dụng còi xe nên dùng như thế nào để thể hiện là người có văn hóa giao thông?",
                    dapAn1 = "Chỉ bấm còi khi thật sự cần thiết, không bấm còi liên tục hoặc kéo dài, sử dụng còi với mức âm lượng theo quy định.",
                    dapAn2 = "Bấm còi liên tục để các xe khác nhường đường.",
                    dapAn3 = "Bấm còi to khi đi qua khu vực đông dân cư.",
                    dapAn4 = "Không cần dùng còi, tránh gây tiếng ồn là văn minh.",
                    dapAnDung = "1",
                    chuDe = "Văn hóa và đạo đức lái xe"
                ),

                LyThuyet(
                    cauHoi = "Người điều khiển phương tiện tham gia giao thông đường bộ gây ra tai nạn giao thông đường bộ có trách nhiệm gì?",
                    dapAn1 = "Dừng ngay phương tiện, cảnh báo nguy hiểm, giữ nguyên hiện trường, trợ giúp người bị nạn và báo tin cho cơ quan Công an, cơ sở khám bệnh, chữa bệnh.",
                    dapAn2 = "Ở lại hiện trường vụ tai nạn giao thông đường bộ cho đến khi người của cơ quan Công an đến, trừ trường hợp phải đi cấp cứu, đưa người bị nạn đi cấp cứu hoặc xét thấy bị đe dọa đến tính mạng, sức khỏe nhưng phải đến trình báo ngay cơ quan Công an, Ủy ban nhân dân nơi gần nhất.",
                    dapAn3 = "Cung cấp thông tin xác định danh tính về bản thân, người liên quan đến vụ tai nạn giao thông đường bộ và thông tin liên quan của vụ tai nạn giao thông đường bộ cho cơ quan có thẩm quyền.",
                    dapAn4 = "Cả ba ý trên.",
                    dapAnDung = "4",
                    chuDe = "Văn hóa và đạo đức lái xe"
                ),

                LyThuyet(
                    cauHoi = "Người có mặt tại nơi xảy ra vụ tai nạn giao thông đường bộ có trách nhiệm gì?",
                    dapAn1 = "Giúp đỡ, cứu chữa kịp thời người bị nạn; báo tin ngay cho cơ quan Công an, cơ sở khám bệnh, chữa bệnh hoặc Ủy ban nhân dân nơi gần nhất; tham gia bảo vệ hiện trường; tham gia bảo vệ tài sản của người bị nạn; cung cấp thông tin liên quan về vụ tai nạn theo yêu cầu của cơ quan có thẩm quyền.",
                    dapAn2 = "Chụp lại hình ảnh vụ tai nạn (nếu có thiết bị ghi hình) và nhanh chóng rời khỏi hiện trường vụ tai nạn.",
                    dapAn3 = "",
                    dapAn4 = "",
                    dapAnDung = "1",
                    chuDe = "Văn hóa và đạo đức lái xe"
                ),

                LyThuyet(
                    cauHoi = "Trong đoạn đường hai chiều tại khu đông dân cư đang ùn tắc, người điều khiển xe mô tô có văn hóa giao thông sẽ lựa chọn cách xử lý nào?",
                    dapAn1 = "Cho xe lấn sang làn ngược chiều để nhanh chóng thoát khỏi nơi ùn tắc.",
                    dapAn2 = "Điều khiển xe trên vỉa hè để nhanh chóng thoát khỏi nơi ùn tắc.",
                    dapAn3 = "Kiên nhẫn tuân thủ hướng dẫn của người điều khiển giao thông hoặc tín hiệu đèn giao thông, di chuyển trên đúng phần đường bên phải theo chiều đi, nhường đường cho các phương tiện đi ngược chiều.",
                    dapAn4 = "",
                    dapAnDung = "3",
                    chuDe = "Văn hóa và đạo đức lái xe"
                ),

                LyThuyet(
                    cauHoi = "Hành vi nào dưới đây bị nghiêm cấm?",
                    dapAn1 = "Sử dụng xe đạp đi trên các tuyến quốc lộ.",
                    dapAn2 = "Rải vật sắc nhọn, đổ chất gây trơn trượt trên đường bộ.",
                    dapAn3 = "Cả hai ý trên.",
                    dapAn4 = "",
                    dapAnDung = "2",
                    chuDe = "Câu điểm liệt"
                ),
                LyThuyet(
                    cauHoi = "Hành vi đưa xe cơ giới, xe máy chuyên dùng tham gia giao thông đường bộ nào dưới đây bị cấm?",
                    dapAn1 = "Không có chứng nhận kiểm định an toàn kỹ thuật và bảo vệ môi trường.",
                    dapAn2 = "Hết niên hạn sử dụng.",
                    dapAn3 = "Cả hai ý trên.",
                    dapAn4 = "",
                    dapAnDung = "3",
                    chuDe = " Câu điểm liệt "
                ),
                LyThuyet(
                    cauHoi = "Tổ chức đua xe được phép thực hiện khi nào?",
                    dapAn1 = "Trên đường phố không có người qua lại.",
                    dapAn2 = "Được người dân ủng hộ.",
                    dapAn3 = "Được cơ quan có thẩm quyền cấp phép.",
                    dapAn4 = "",
                    dapAnDung = "3",
                    chuDe = " Câu điểm liệt "
                ),
                LyThuyet(
                    cauHoi = "Hành vi đua xe trái phép bị xử lý như thế nào?",
                    dapAn1 = "Chỉ bị nhắc nhở.",
                    dapAn2 = "Tùy theo mức độ của hành vi vi phạm có thể bị xử lý hành chính hoặc xử lý hình sự.",
                    dapAn3 = "",
                    dapAn4 = "",
                    dapAnDung = "2",
                    chuDe = " Câu điểm liệt "
                ),
                LyThuyet(
                    cauHoi = "Người điều khiển phương tiện tham gia giao thông đường bộ mà trong máu hoặc hơi thở có nồng độ cồn có bị nghiêm cấm không?",
                    dapAn1 = "Bị nghiêm cấm.",
                    dapAn2 = "Không bị nghiêm cấm.",
                    dapAn3 = "Không bị nghiêm cấm, nếu nồng độ cồn trong máu ở mức nhẹ, có thể điều khiển phương tiện tham gia giao thông.",
                    dapAn4 = "",
                    dapAnDung = "1",
                    chuDe = " Câu điểm liệt "
                ),
                LyThuyet(
                    cauHoi = "Theo Luật Phòng chống tác hại của rượu, bia, đối tượng nào dưới đây bị cấm sử dụng rượu, bia khi tham gia giao thông?",
                    dapAn1 = "Người điều khiển xe ô tô, xe mô tô, xe đạp, xe gắn máy.",
                    dapAn2 = "Người được chở trên xe cơ giới.",
                    dapAn3 = "Cả hai ý trên.",
                    dapAn4 = "",
                    dapAnDung = "1",
                    chuDe = " Câu điểm liệt "
                ),
                LyThuyet(
                    cauHoi = "Hành vi giao xe ô tô, mô tô cho người nào sau đây tham gia giao thông đường bộ bị nghiêm cấm?",
                    dapAn1 = "Người chưa đủ tuổi theo quy định.",
                    dapAn2 = "Người không có giấy phép lái xe.",
                    dapAn3 = "Người có giấy phép lái xe nhưng đã bị trừ hết 12 điểm.",
                    dapAn4 = "Cả ba ý trên.",
                    dapAnDung = "4",
                    chuDe = " Câu điểm liệt "
                ),
                LyThuyet(
                    cauHoi = "Hành vi nào sau đây bị nghiêm cấm?",
                    dapAn1 = "Điều khiển xe cơ giới lạng lách, đánh võng, rú ga liên tục khi tham gia giao thông trên đường.",
                    dapAn2 = "Xúc phạm, đe dọa, cản trở, chống đối hoặc không chấp hành hiệu lệnh, hướng dẫn, yêu cầu kiểm tra, kiểm soát của người thi hành công vụ về bảo đảm trật tự, an toàn giao thông đường bộ.",
                    dapAn3 = "Cả hai ý trên.",
                    dapAn4 = "",
                    dapAnDung = "3",
                    chuDe = " Câu điểm liệt "
                ),
                LyThuyet(
                    cauHoi = "Hành vi nào sau đây bị cấm?",
                    dapAn1 = "Lắp đặt, sử dụng thiết bị âm thanh, ánh sáng trên xe cơ giới, xe máy chuyên dùng gây mất trật tự, an toàn giao thông đường bộ.",
                    dapAn2 = "Cản trở người, phương tiện tham gia giao thông trên đường bộ; ném gạch, đất, đá, cát hoặc vật thể khác vào người, phương tiện đang tham gia giao thông trên đường bộ.",
                    dapAn3 = "Cả hai ý trên.",
                    dapAn4 = "",
                    dapAnDung = "3",
                    chuDe = " Câu điểm liệt "
                ),
                LyThuyet(
                    cauHoi = "Người lái xe được phép vượt xe trên cầu hẹp có một làn đường, đường cong có tầm nhìn bị hạn chế hay không?",
                    dapAn1 = "Được phép vượt khi đường vắng.",
                    dapAn2 = "Không được phép vượt.",
                    dapAn3 = "Được phép vượt khi có việc gấp.",
                    dapAn4 = "",
                    dapAnDung = "2",
                    chuDe = " Câu điểm liệt "
                ),
                LyThuyet(
                    cauHoi = "Muốn vượt xe phía trước, người lái xe mô tô phải có tín hiệu như thế nào dưới đây để bảo đảm an toàn?",
                    dapAn1 = "Bấm còi liên tục để xe phía trước biết xe mình xin vượt.",
                    dapAn2 = "Rú ga liên tục để xe phía trước biết xe mình xin vượt.",
                    dapAn3 = "Báo hiệu nhấp nháy bằng đèn chiếu sáng phía trước hoặc còi.",
                    dapAn4 = "",
                    dapAnDung = "3",
                    chuDe = " Câu điểm liệt "
                ),
                LyThuyet(
                    cauHoi = "Khi điều khiển phương tiện tham gia giao thông, hành vi nào sau đây bị cấm?",
                    dapAn1 = "Dùng tay cầm và sử dụng điện thoại hoặc thiết bị điện tử khác.",
                    dapAn2 = "Chỉ được chở người trên thùng xe ô tô chở hàng trong trường hợp chở người đi làm nhiệm vụ cứu nạn, cứu hộ, phòng, chống thiên tai, dịch bệnh hoặc thực hiện nhiệm vụ khẩn cấp.",
                    dapAn3 = "",
                    dapAn4 = "",
                    dapAnDung = "1",
                    chuDe = " Câu điểm liệt "
                ),
                LyThuyet(
                    cauHoi = "Người lái xe không được vượt xe khác khi gặp trường hợp nào dưới đây?",
                    dapAn1 = "Trên cầu hẹp có một làn đường; nơi đường giao nhau, đường bộ giao nhau cùng mức với đường sắt; khi gặp xe ưu tiên.",
                    dapAn2 = "Trên cầu có từ 02 làn xe trở lên.",
                    dapAn3 = "Trên đường có 02 làn đường được phân chia làn bằng vạch kẻ nét đứt.",
                    dapAn4 = "",
                    dapAnDung = "1",
                    chuDe = " Câu điểm liệt "
                ),
                LyThuyet(
                    cauHoi = "Người điều khiển xe mô tô hai bánh, xe mô tô ba bánh, xe gắn máy có được phép sử dụng xe để kéo hoặc đẩy các phương tiện khác khi tham gia giao thông không?",
                    dapAn1 = "Được phép.",
                    dapAn2 = "Nếu phương tiện được kéo, đẩy có khối lượng nhỏ hơn phương tiện của mình.",
                    dapAn3 = "Tùy trường hợp.",
                    dapAn4 = "Không được phép.",
                    dapAnDung = "4",
                    chuDe = " Câu điểm liệt "
                ),
                LyThuyet(
                    cauHoi = "Khi điều khiển xe mô tô hai bánh, xe mô tô ba bánh, xe gắn máy, những hành vi nào sau đây không được phép?",
                    dapAn1 = "Buông cả hai tay; đứng, nằm trên xe điều khiển xe; sử dụng chân chống hoặc vật khác quệt xuống đường khi xe đang chạy.",
                    dapAn2 = "Chở tối đa hai người phía sau khi chở người bệnh đi cấp cứu, áp giải người có hành vi vi phạm pháp luật, trẻ em dưới 12 tuổi và người già yếu hoặc người khuyết tật.",
                    dapAn3 = "",
                    dapAn4 = "",
                    dapAnDung = "1",
                    chuDe = " Câu điểm liệt "
                ),
                LyThuyet(
                    cauHoi = "Khi điều khiển xe mô tô hai bánh, xe mô tô ba bánh, xe gắn máy, những hành vi nào sau đây không được phép?",
                    dapAn1 = "Buông cả hai tay; sử dụng xe để kéo, đẩy xe khác, vật khác; sử dụng chân chống hoặc vật khác quệt xuống đường khi xe đang chạy.",
                    dapAn2 = "Sử dụng xe để chở người hoặc hàng hóa; để chân chạm xuống đất khi khởi hành.",
                    dapAn3 = "Đội mũ bảo hiểm; chạy xe đúng tốc độ quy định và chấp hành đúng quy tắc giao thông đường bộ.",
                    dapAn4 = "Chở người ngồi sau dưới 16 tuổi.",
                    dapAnDung = "1",
                    chuDe = " Câu điểm liệt "
                ),
                LyThuyet(
                    cauHoi = "Người lái xe, người được chở trên xe mô tô hai bánh, xe mô tô ba bánh, xe gắn máy phải thực hiện quy định nào dưới đây?",
                    dapAn1 = "Đội mũ bảo hiểm theo đúng quy chuẩn kỹ thuật quốc gia và cài quai đúng quy cách.",
                    dapAn2 = "Người lái xe phải đội mũ bảo hiểm, người được chở trên xe không nhất thiết phải đội mũ bảo hiểm.",
                    dapAn3 = "Phải đội mũ bảo hiểm nhưng không nhất thiết phải cài quai.",
                    dapAn4 = "",
                    dapAnDung = "1",
                    chuDe = " Câu điểm liệt "
                ),
                LyThuyet(
                    cauHoi = "Người lái xe mô tô hai bánh, xe mô tô ba bánh, xe gắn máy không được thực hiện các hành vi nào dưới đây?",
                    dapAn1 = "Đi xe dàn hàng ngang; buông cả hai tay.",
                    dapAn2 = "Sử dụng xe để kéo, đẩy xe khác, vật khác, dẫn dắt vật nuôi, mang, vác và chở vật cồng kềnh; chở người đứng trên xe, giá đèo hàng hoặc ngồi trên tay lái; xếp hàng hóa trên xe quá giới hạn quy định.",
                    dapAn3 = "Ngồi về một bên điều khiển xe; đứng, nằm trên xe điều khiển xe; thay người lái xe khi xe đang chạy; quay người về phía sau để điều khiển xe hoặc bịt mắt điều khiển xe; sử dụng chân chống hoặc vật khác quệt xuống đường khi xe đang chạy.",
                    dapAn4 = "Cả ba ý trên.",
                    dapAnDung = "4",
                    chuDe = " Câu điểm liệt "
                ),
                LyThuyet(
                    cauHoi = "Người lái xe mô tô hai bánh, xe mô tô ba bánh, xe gắn máy không được thực hiện các hành vi nào sau đây?",
                    dapAn1 = "Đi xe dàn hàng ngang; đi xe vào phần đường dành cho người đi bộ và phương tiện khác.",
                    dapAn2 = "Sử dụng ô, thiết bị âm thanh, trừ thiết bị trợ thính.",
                    dapAn3 = "Cả hai ý trên.",
                    dapAn4 = "",
                    dapAnDung = "3",
                    chuDe = " Câu điểm liệt "
                ),
                LyThuyet(
                    cauHoi = "Người lái xe mô tô hai bánh, xe mô tô ba bánh, xe gắn máy không được thực hiện hành vi nào sau đây?",
                    dapAn1 = "Đi trên phần đường, làn đường quy định, chấp hành hiệu lệnh của người điều khiển giao thông, đèn tín hiệu giao thông.",
                    dapAn2 = "Đi xe dàn hàng ngang, đi xe vào phần đường dành cho người đi bộ.",
                    dapAn3 = "Cả hai ý trên.",
                    dapAn4 = "",
                    dapAnDung = "2",
                    chuDe = " Câu điểm liệt "
                ),
                LyThuyet(
                    cauHoi = "Khi điều khiển xe mô tô tay ga xuống đường dốc dài, độ dốc cao, người lái xe cần thực hiện các thao tác nào dưới đây để bảo đảm an toàn?",
                    dapAn1 = "Giữ tay ga ở mức độ phù hợp, sử dụng phanh trước và phanh sau để giảm tốc độ.",
                    dapAn2 = "Nhả hết tay ga, tắt động cơ, sử dụng phanh trước và phanh sau để giảm tốc độ.",
                    dapAn3 = "Sử dụng phanh trước để giảm tốc độ kết hợp với tắt chìa khóa điện của xe.",
                    dapAn4 = "",
                    dapAnDung = "1",
                    chuDe = "Kỹ thuật lái xe"
                ),
                LyThuyet(
                    cauHoi = "Khi điều khiển xe trên đường vòng người lái xe cần phải làm gì để bảo đảm an toàn?",
                    dapAn1 = "Quan sát cẩn thận các chướng ngại vật và báo hiệu bằng coi, đèn; giảm tốc độ tới mức cần thiết, về số thấp và thực hiện quay vòng với tốc độ phù hợp với bán kính cong của đường vòng.",
                    dapAn2 = "Quan sát cẩn thận các chướng ngại vật và báo hiệu bằng còi, đèn; tăng tốc để nhanh chóng qua đường vòng và giảm tốc độ sau khi qua đường vòng.",
                    dapAn3 = "",
                    dapAn4 = "",
                    dapAnDung = "1",
                    chuDe = "Kỹ thuật lái xe"
                ),
                LyThuyet(
                    cauHoi = "Khi điều khiển xe qua đường sắt, người lái xe cần phải thực hiện các thao tác nào dưới đây để bảo đảm an toàn?",
                    dapAn1 = "Khi có chuông báo hoặc thanh chắn đã hạ xuống, người lái xe phải dừng xe tạm thời đúng khoảng cách an toàn, kéo phanh tay nếu đường dốc hoặc phải chờ lâu.",
                    dapAn2 = "Khi không có chuông báo hoặc thanh chắn không hạ xuống, người lái xe cần phải quan sát nếu thấy đủ điều kiện an toàn thì về số thấp, tăng ga nhẹ và không thay đổi số trong quá trình vượt qua đường sắt để tránh động cơ chết máy cho xe cho vượt qua.",
                    dapAn3 = "Cả hai ý trên.",
                    dapAn4 = "",
                    dapAnDung = "3",
                    chuDe = "Kỹ thuật lái xe"
                ),
                LyThuyet(
                    cauHoi = "Trong các loại nhiên liệu dưới đây, loại nhiên liệu nào giảm thiểu ô nhiễm môi trường?",
                    dapAn1 = "Xăng và dầu diesel.",
                    dapAn2 = "Xăng sinh học và khí sinh học.",
                    dapAn3 = "Ý 1 và ý 2.",
                    dapAn4 = "",
                    dapAnDung = "2",
                    chuDe = "Kỹ thuật lái xe"
                ),
                LyThuyet(
                    cauHoi = "Các biện pháp tiết kiệm nhiên liệu khi chạy xe?",
                    dapAn1 = "Bảo dưỡng xe theo định kỳ và có kế hoạch lộ trình trước khi xe chạy.",
                    dapAn2 = "Kiểm tra áp suất lốp theo quy định và chạy xe với tốc độ phù hợp với tình trạng mặt đường và mật độ giao thông trên đường.",
                    dapAn3 = "Cả hai ý trên.",
                    dapAn4 = "",
                    dapAnDung = "3",
                    chuDe = "Kỹ thuật lái xe"
                ),
                LyThuyet(
                    cauHoi = "Khi tầm nhìn bị hạn chế bởi sương mù hoặc mưa to, người lái xe phải thực hiện các thao tác nào để bảo đảm an toàn?",
                    dapAn1 = "Tăng tốc độ, chạy gần xe trước, nhìn đèn hậu để định hướng.",
                    dapAn2 = "Giảm tốc độ, chạy cách xa xe trước với khoảng cách an toàn, bật đèn sương mù và đèn chiếu gần.",
                    dapAn3 = "Tăng tốc độ, bật đèn pha vượt qua xe chạy trước.",
                    dapAn4 = "",
                    dapAnDung = "2",
                    chuDe = "Kỹ thuật lái xe"
                ),
                LyThuyet(
                    cauHoi = "Khi đèn pha của xe đi ngược chiều gây chói mắt, làm giảm khả năng quan sát trên đường, người lái xe xử lý như thế nào dưới đây để bảo đảm an toàn?",
                    dapAn1 = "Giảm tốc độ, giữ vững tay lái, nhìn chếch sang lề đường bên phải.",
                    dapAn2 = "Bật đèn pha chiếu xa và giữ nguyên tốc độ.",
                    dapAn3 = "Tăng tốc độ, bật đèn pha đối diện xe phía trước.",
                    dapAn4 = "",
                    dapAnDung = "1",
                    chuDe = "Kỹ thuật lái xe"
                ),
                LyThuyet(
                    cauHoi = "Để đạt được hiệu quả phanh cao nhất, người lái xe mô tô phải sử dụng các kỹ năng như thế nào dưới đây?",
                    dapAn1 = "Sử dụng phanh trước.",
                    dapAn2 = "Sử dụng phanh sau.",
                    dapAn3 = "Giảm hết ga, sử dụng đồng thời cả phanh sau và phanh trước.",
                    dapAn4 = "",
                    dapAnDung = "3",
                    chuDe = "Kỹ thuật lái xe"
                ),
                LyThuyet(
                    cauHoi = "Khi đang lái xe mô tô hoặc ô tô, nếu có nhu cầu sử dụng điện thoại để nhắn tin hoặc gọi điện, người lái xe phải thực hiện như thế nào trong các tình huống nêu dưới đây?",
                    dapAn1 = "Giảm tốc độ để bảo đảm an toàn với xe phía trước và sử dụng điện thoại để liên lạc.",
                    dapAn2 = "Giảm tốc độ để dừng xe ở nơi cho phép sau đó sử dụng điện thoại để liên lạc.",
                    dapAn3 = "Tăng tốc độ để cách xa xe phía sau và sử dụng điện thoại để liên lạc.",
                    dapAn4 = "",
                    dapAnDung = "2",
                    chuDe = "Kỹ thuật lái xe"
                ),
                LyThuyet(
                    cauHoi = "Những thói quen nào dưới đây khi điều khiển xe mô tô tay ga tham gia giao thông dễ gây tai nạn nguy hiểm?",
                    dapAn1 = "Sử dụng còi.",
                    dapAn2 = "Phanh đồng thời cả phanh trước và phanh sau.",
                    dapAn3 = "Chỉ sử dụng phanh trước.",
                    dapAn4 = "",
                    dapAnDung = "3",
                    chuDe = "Kỹ thuật lái xe"
                ),
                LyThuyet(
                    cauHoi = "Khi điều khiển xe mô tô quay đầu, người lái xe cần thực hiện như thế nào để bảo đảm an toàn?",
                    dapAn1 = "Bật tín hiệu báo rẽ trước khi quay đầu, từ từ giảm tốc độ đến mức có thể dừng lại.",
                    dapAn2 = "Chỉ quay đầu xe tại những nơi được phép quay đầu.",
                    dapAn3 = "Quan sát an toàn các phương tiện tới từ phía trước, phía sau, hai bên đồng thời nhường đường cho xe từ bên phải và phía trước đi tới.",
                    dapAn4 = "Cả ba ý trên.",
                    dapAnDung = "4",
                    chuDe = "Kỹ thuật lái xe"
                ),
                LyThuyet(
                    cauHoi = "Tay ga trên xe mô tô hai bánh có tác dụng gì dưới đây?",
                    dapAn1 = "Để điều khiển xe chạy về phía trước.",
                    dapAn2 = "Để điều tiết công suất động cơ qua đó điều khiển tốc độ của xe.",
                    dapAn3 = "Để điều khiển xe chạy lùi.",
                    dapAn4 = "Ý 1 và ý 2.",
                    dapAnDung = "4",
                    chuDe = "Kỹ thuật lái xe"
                ),
                LyThuyet(
                    cauHoi = "Gương chiếu hậu của xe mô tô hai bánh có tác dụng gì dưới đây?",
                    dapAn1 = "Để quan sát an toàn phía bên trái khi chuẩn bị rẽ trái.",
                    dapAn2 = "Để quan sát an toàn phía bên phải khi chuẩn bị rẽ phải.",
                    dapAn3 = "Để quan sát an toàn phía sau của bên trái và bên phải trước khi chuyển hướng.",
                    dapAn4 = "Để quan sát an toàn phía trước cả bên trái và bên phải trước khi chuyển hướng.",
                    dapAnDung = "3",
                    chuDe = "Kỹ thuật lái xe"
                ),
                LyThuyet(
                    cauHoi = "Để bảo đảm an toàn khi tham gia giao thông, người lái xe mô tô hai bánh cần điều khiển tay ga như thế nào?",
                    dapAn1 = "Tăng ga thật mạnh, giảm ga từ từ.",
                    dapAn2 = "Tăng ga thật mạnh, giảm ga thật nhanh.",
                    dapAn3 = "Tăng ga từ từ, giảm ga thật nhanh.",
                    dapAn4 = "Tăng ga từ từ, giảm ga từ từ.",
                    dapAnDung = "3",
                    chuDe = "Kỹ thuật lái xe"
                ),
                LyThuyet(
                    cauHoi = "Để tránh đổ, ngã khi điều khiển xe mô tô hai bánh ở nơi đường xấu, nhỏ và hẹp, người lái xe cần xử lý như thế nào?",
                    dapAn1 = "Đi ở tốc độ thấp, quan sát liên tục khoảng cách từ 05 m đến 10 m phía trước để điều chỉnh sớm hướng di chuyển.",
                    dapAn2 = "Trong quá trình di chuyển không nên dùng phanh trước tránh làm khóa bánh dẫn hướng.",
                    dapAn3 = "Không được lắc người sang trái hoặc phải nhiều, trọng tâm cơ thể cần trùng với trọng tâm của xe.",
                    dapAn4 = "Cả ba ý trên.",
                    dapAnDung = "4",
                    chuDe = "Kỹ thuật lái xe"
                ),

                )
            cursor.close()
            list.forEach { insertLyThuyet(it) }
        } else cursor.close()
        db.close()
    }

    fun insertLyThuyet(item: LyThuyet) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("CauHoi", item.cauHoi)
            put("DapAn1", item.dapAn1)
            put("DapAn2", item.dapAn2)
            put("DapAn3", item.dapAn3)
            put("DapAn4", item.dapAn4)
            put("DapAnDung", item.dapAnDung)
            put("ChuDe", item.chuDe)
        }
        db.insert("LyThuyet", null, values)
        db.close()
    }

    fun getAllLyThuyet(): List<LyThuyet> {
        val list = mutableListOf<LyThuyet>()
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM LyThuyet", null)
        if (cursor.moveToFirst()) {
            do {
                list.add(
                    LyThuyet(
                        cauHoi = cursor.getString(0),
                        dapAn1 = cursor.getString(1),
                        dapAn2 = cursor.getString(2),
                        dapAn3 = cursor.getString(3),
                        dapAn4 = cursor.getString(4),
                        dapAnDung = cursor.getString(5),
                        chuDe = cursor.getString(6)
                    )
                )
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return list
    }

    fun getLyThuyetTheoChuDe(chuDe: String): List<LyThuyet> {
        val list = mutableListOf<LyThuyet>()
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM LyThuyet WHERE ChuDe = ?", arrayOf(chuDe))
        if (cursor.moveToFirst()) {
            do {
                list.add(
                    LyThuyet(
                        cauHoi = cursor.getString(0),
                        dapAn1 = cursor.getString(1),
                        dapAn2 = cursor.getString(2),
                        dapAn3 = cursor.getString(3),
                        dapAn4 = cursor.getString(4),
                        dapAnDung = cursor.getString(5),
                        chuDe = cursor.getString(6)
                    )
                )
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return list
    }

    // =====================================================
    // =============== BIỂN BÁO GIAO THÔNG =================
    // =====================================================

    fun createDefaultBienBao() {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM BienBao", null)
        if (cursor.count == 0) {
            val list = listOf(
                BienBao(
                    "101",
                    "Cấm đi ngược chiều",
                    "Biển báo cấm xe đi vào chiều ngược lại.",
                    R.drawable.cam_nguoc_chieu
                ),
                BienBao(
                    "102",
                    "Cấm rẽ trái",
                    "Biển báo cấm các phương tiện rẽ trái.",
                    R.drawable.cam_re_trai
                ),
                BienBao(
                    "103",
                    "Cấm quay đầu xe",
                    "Biển báo cấm quay đầu xe tại vị trí này.",
                    R.drawable.cam_quay_dau
                ),
                BienBao(
                    "104",
                    "Cấm vượt",
                    "Biển báo cấm vượt các phương tiện khác.",
                    R.drawable.cam_vuot
                )
            )
            cursor.close()
            list.forEach { insertBienBao(it) }
        } else cursor.close()
        db.close()
    }

    fun insertBienBao(bb: BienBao) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("SoHieu", bb.soHieu)
            put("TenBienBao", bb.tenBienBao)
            put("NoiDung", bb.noiDung)
            put("HinhAnh", bb.hinhAnh)
        }
        db.insert("BienBao", null, values)
        db.close()
    }

    fun getAllBienBao(): List<BienBao> {
        val list = mutableListOf<BienBao>()
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM BienBao", null)
        if (cursor.moveToFirst()) {
            do {
                list.add(
                    BienBao(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getInt(3)
                    )
                )
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return list
    }

    // =====================================================
    // =============== LUẬT GIAO THÔNG =====================
    // =====================================================

    fun createDefaultLuat() {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM Luat", null)
        if (cursor.count == 0) {
            val list = listOf(
                Luat("L001", "Người điều khiển phương tiện giao thông đường bộ phải có giấy phép lái xe phù hợp với loại xe được phép điều khiển."),
                Luat("L002", "Người ngồi trên xe mô tô, xe gắn máy phải đội mũ bảo hiểm và cài quai đúng quy cách."),
                Luat("L003", "Người điều khiển xe phải nhường đường cho người đi bộ, xe lăn của người khuyết tật qua đường tại nơi có vạch kẻ đường cho người đi bộ."),
                Luat("L004", "Cấm sử dụng rượu bia khi điều khiển phương tiện tham gia giao thông.")
            )
            cursor.close()
            list.forEach { insertLuat(it) }
        } else cursor.close()
        db.close()
    }

    fun insertLuat(l: Luat) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("MaLuat", l.maLuat)
            put("NoiDung", l.noiDung)
        }
        db.insert("Luat", null, values)
        db.close()
    }

    fun getAllLuat(): List<Luat> {
        val list = mutableListOf<Luat>()
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM Luat", null)
        if (cursor.moveToFirst()) {
            do {
                list.add(Luat(cursor.getString(0), cursor.getString(1)))
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return list
    }
}

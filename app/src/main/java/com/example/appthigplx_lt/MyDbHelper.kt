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
                ID INTEGER PRIMARY KEY AUTOINCREMENT,
                CauHoi TEXT,
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


        // === BẢNG CHI TIẾT TIẾN ĐỘ ===
        db.execSQL(
            """
            CREATE TABLE IF NOT EXISTS ProgressDetail(
                ChuDe TEXT,
                CauHoi TEXT,
                IsCorrect INTEGER,
                PRIMARY KEY (ChuDe, CauHoi)
            )
        """.trimIndent()
        )
    }


    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS LyThuyet")
        db.execSQL("DROP TABLE IF EXISTS BienBao")
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
                    cauHoi = "Người đủ 16 tuổi được điều khiển các loại xe nào dưới đây?",
                    dapAn1 = "Xe mô tô 2 bánh có dung tích xi lanh từ 50 cm3 trở lên.",
                    dapAn2 = "Xe gắn máy có dung tích xi lanh dưới 50 cm3.",
                    dapAn3 = "Xe ô tô tải dưới 3,5 tấn; xe chở người đến 9 chỗ ngồi.",
                    dapAn4 = "Tất cả các ý nêu trên.",
                    dapAnDung = "2",
                    chuDe = "Khái niệm và Quy tắc"
                ),

                LyThuyet(
                    cauHoi = "Người có giấy phép lái xe mô tô hạng A1 không được phép điều khiển loại xe nào dưới đây?",
                    dapAn1 = "Xe mô tô có dung tích xi lanh 125 cm3.",
                    dapAn2 = "Xe mô tô có dung tích xi lanh từ 175 cm3 trở lên.",
                    dapAn3 = "Xe mô tô có dung tích xi lanh 100 cm3.",
                    dapAn4 = "",
                    dapAnDung = "2",
                    chuDe = "Khái niệm và Quy tắc"
                ),

                LyThuyet(
                    cauHoi = "Người có giấy phép lái xe mô tô hạng A1 được phép điều khiển loại xe nào dưới đây?",
                    dapAn1 = "Xe mô tô hai bánh có dung tích xi lanh từ 50 cm3 đến dưới 175 cm3.",
                    dapAn2 = "Xe mô tô ba bánh dùng cho người khuyết tật.",
                    dapAn3 = "Cả ý 1 và ý 2.",
                    dapAn4 = "",
                    dapAnDung = "3",
                    chuDe = "Khái niệm và Quy tắc"
                ),

                LyThuyet(
                    cauHoi = "Biển báo hiệu có dạng hình tròn, viền đỏ, nền trắng, trên nền có hình vẽ hoặc chữ số, chữ viết màu đen là loại biển gì dưới đây?",
                    dapAn1 = "Biển báo nguy hiểm.",
                    dapAn2 = "Biển báo cấm.",
                    dapAn3 = "Biển báo hiệu lệnh.",
                    dapAn4 = "Biển báo chỉ dẫn.",
                    dapAnDung = "2",
                    chuDe = "Khái niệm và Quy tắc"
                ),

                LyThuyet(
                    cauHoi = "Biển báo hiệu có dạng tam giác đều, viền đỏ, nền màu vàng, trên có hình vẽ màu đen là loại biển gì dưới đây?",
                    dapAn1 = "Biển báo nguy hiểm.",
                    dapAn2 = "Biển báo cấm.",
                    dapAn3 = "Biển báo hiệu lệnh.",
                    dapAn4 = "Biển báo chỉ dẫn.",
                    dapAnDung = "1",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Biển báo hiệu hình tròn có nền xanh lam có hình vẽ màu trắng là loại biển gì dưới đây?",
                    dapAn1 = "Biển báo nguy hiểm.",
                    dapAn2 = "Biển báo cấm.",
                    dapAn3 = "Biển báo hiệu lệnh phải thi hành.",
                    dapAn4 = "Biển báo chỉ dẫn.",
                    dapAnDung = "3",
                    chuDe = "Khái niệm và Quy tắc"
                ),

                LyThuyet(
                    cauHoi = "Biển báo hiệu hình chữ nhật hoặc hình vuông hoặc hình mũi tên nền xanh lam là loại biển gì dưới đây?",
                    dapAn1 = "Biển báo nguy hiểm.",
                    dapAn2 = "Biển báo cấm.",
                    dapAn3 = "Biển báo hiệu lệnh phải thi hành.",
                    dapAn4 = "Biển báo chỉ dẫn.",
                    dapAnDung = "4",
                    chuDe = "Khái niệm và Quy tắc"
                ),

                LyThuyet(
                    cauHoi = "Khi sử dụng giấy phép lái xe đã khai báo mất để điều khiển phương tiện cơ giới đường bộ, ngoài việc bị thu hồi giấy phép lái xe, chịu trách nhiệm trước pháp luật, người lái xe không được cấp giấy phép lái xe trong thời gian bao nhiêu năm?",
                    dapAn1 = "02 năm.",
                    dapAn2 = "03 năm.",
                    dapAn3 = "05 năm.",
                    dapAn4 = "04 năm.",
                    dapAnDung = "3",
                    chuDe = "Khái niệm và Quy tắc"
                ),

                LyThuyet(
                    cauHoi = "Khi gặp hiệu lệnh như dưới đây của cảnh sát giao thông thì người tham gia giao thông phải đi như thế nào là đúng quy tắc giao thông?",
                    dapAn1 = "Người tham gia giao thông ở các hướng phải dừng lại.",
                    dapAn2 = "Người tham gia giao thông ở các hướng được đi theo chiều gậy chỉ của cảnh sát giao thông.",
                    dapAn3 = "Người tham gia giao thông ở phía trước và phía sau người điều khiển được đi tất cả các hướng; người tham gia giao thông ở phía bên phải và phía bên trái người điều khiển phải dừng lại.",
                    dapAn4 = "Người tham gia giao thông ở phía trước và phía sau người điều khiển phải dừng lại; người tham gia giao thông ở phía bên phải và phía bên trái người điều khiển được đi tất cả các hướng.",
                    dapAnDung = "4",
                    hinhAnh = R.drawable.knqt1,
                    chuDe = "Khái niệm và Quy tắc"
                ),

                LyThuyet(
                    cauHoi = "Khi gặp hiệu lệnh như dưới đây của cảnh sát giao thông thì người tham gia giao thông phải đi như thế nào là đúng quy tắc giao thông?",
                    dapAn1 = "Người tham gia giao thông ở hướng đối diện cảnh sát giao thông được đi, các hướng khác cần phải dừng lại.",
                    dapAn2 = "Người tham gia giao thông được rẽ phải theo chiều mũi tên màu xanh ở bục cảnh sát giao thông.",
                    dapAn3 = "Người tham gia giao thông ở các hướng đều phải dừng lại trừ các xe ở trong khu vực giao nhau.",
                    dapAn4 = "Người ở hướng đối diện cảnh sát giao thông phải dừng lại, các hướng khác được đi trong đó có bạn.",
                    dapAnDung = "3",
                    hinhAnh = R.drawable.knqt2,
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Tại nơi có biển báo hiệu cố định lại có báo hiệu tạm thời thì người tham gia giao thông phải chấp hành hiệu lệnh của báo hiệu nào?",
                    dapAn1 = "Biển báo hiệu cố định.",
                    dapAn2 = "Báo hiệu tạm thời.",
                    dapAn3 = "",
                    dapAn4 = "",
                    dapAnDung = "2",
                    chuDe = "Khái niệm và Quy tắc"
                ),

                LyThuyet(
                    cauHoi = "Trên đường có nhiều làn đường cho xe đi cùng chiều được phân biệt bằng vạch kẻ phân làn đường, người điều khiển phương tiện phải cho xe đi như thế nào?",
                    dapAn1 = "Cho xe đi trên bất kỳ làn đường nào hoặc giữa hai làn đường nếu không có xe phía trước; khi cần thiết phải chuyển làn đường, người lái xe phải quan sát xe phía trước để bảo đảm an toàn.",
                    dapAn2 = "Phải cho xe đi trong một làn đường và chỉ được chuyển làn đường ở những nơi cho phép; khi chuyển làn phải có tín hiệu báo trước và phải bảo đảm an toàn.",
                    dapAn3 = "Phải cho xe đi trong một làn đường, khi cần thiết phải chuyển làn đường, người lái xe phải quan sát xe phía trước để bảo đảm an toàn.",
                    dapAn4 = "",
                    dapAnDung = "2",
                    chuDe = "Khái niệm và Quy tắc"
                ),

                LyThuyet(
                    cauHoi = "Trên đường một chiều có vạch kẻ phân làn đường, xe thô sơ và xe cơ giới phải đi như thế nào là đúng quy tắc giao thông?",
                    dapAn1 = "Xe thô sơ phải đi trên làn đường bên trái ngoài cùng, xe cơ giới, xe máy chuyên dùng đi trên làn đường bên phải.",
                    dapAn2 = "Xe thô sơ phải đi trên làn đường bên phải trong cùng, xe cơ giới, xe máy chuyên dùng đi trên làn đường bên trái.",
                    dapAn3 = "Xe thô sơ đi trên làn đường phù hợp không gây cản trở giao thông, xe cơ giới, xe máy chuyên dùng đi trên làn đường bên phải.",
                    dapAn4 = "",
                    dapAnDung = "2",
                    chuDe = "Khái niệm và Quy tắc"
                ),

                LyThuyet(
                    cauHoi = "Bạn đang lái xe trong khu vực đô thị từ 22 giờ đến 5 giờ sáng hôm sau và cần vượt một xe khác, bạn cần báo hiệu như thế nào để đảm bảo an toàn giao thông?",
                    dapAn1 = "Phải báo hiệu bằng đèn hoặc còi.",
                    dapAn2 = "Chỉ được báo hiệu bằng còi.",
                    dapAn3 = "Phải báo hiệu bằng cả còi và đèn.",
                    dapAn4 = "Chỉ báo hiệu bằng đèn.",
                    dapAnDung = "4",
                    chuDe = "Khái niệm và Quy tắc"
                ),

                LyThuyet(
                    cauHoi = "Khi điều khiển xe chạy trên đường biết có xe sau xin vượt nếu đủ điều kiện an toàn người lái xe phải làm gì?",
                    dapAn1 = "Tăng tốc độ và ra hiệu cho xe sau vượt, không được gây trở ngại cho xe sau vượt.",
                    dapAn2 = "Người điều khiển phương tiện phía trước phải giảm tốc độ, đi sát về bên phải của phần đường xe chạy cho đến khi xe sau đã vượt qua, không được gây trở ngại cho xe sau vượt.",
                    dapAn3 = "Cho xe tránh về bên trái mình và ra hiệu cho xe sau vượt; nếu có chướng ngại vật phía trước hoặc thiếu điều kiện an toàn chưa cho vượt được phải ra hiệu cho xe sau biết; cấm gây trở ngại cho xe xin vượt.",
                    dapAn4 = "",
                    dapAnDung = "2",
                    chuDe = "Khái niệm và Quy tắc"
                ),

                LyThuyet(
                    cauHoi = "Khi muốn chuyển hướng, người lái xe phải thực hiện như thế nào để đảm bảo an toàn giao thông?",
                    dapAn1 = "Quan sát gương, ra tín hiệu, quan sát an toàn và chuyển hướng.",
                    dapAn2 = "Quan sát gương, giảm tốc độ, ra tín hiệu chuyển hướng, quan sát an toàn và chuyển hướng.",
                    dapAn3 = "Quan sát gương, tăng tốc độ, ra tín hiệu và chuyển hướng.",
                    dapAn4 = "",
                    dapAnDung = "2",
                    chuDe = "Khái niệm và Quy tắc"
                ),

                LyThuyet(
                    cauHoi = "Khi tránh xe đi ngược chiều, các xe phải nhường đường như thế nào là đúng quy tắc giao thông?",
                    dapAn1 = "Nơi đường hẹp chỉ đủ cho một xe chạy và có chỗ tránh xe thì xe nào ở gần chỗ tránh hơn phải vào vị trí tránh, nhường đường cho xe kia đi.",
                    dapAn2 = "Xe xuống dốc phải nhường đường cho xe đang lên dốc; xe nào có chướng ngại vật phía trước phải nhường đường cho xe không có chướng ngại vật đi trước.",
                    dapAn3 = "Xe lên dốc phải nhường đường cho xe xuống dốc; xe nào không có chướng ngại vật đi phía trước phải nhường đường cho xe có chướng ngại vật đi trước.",
                    dapAn4 = "Cả ý 1 và ý 2.",
                    dapAnDung = "4",
                    chuDe = "Khái niệm và Quy tắc"
                ),

                LyThuyet(
                    cauHoi = "Bạn đang lái xe trên đường hẹp, xuống dốc và gặp một xe đang đi lên dốc, bạn cần làm gì?",
                    dapAn1 = "Tiếp tục đi vì xe lên dốc phải nhường đường cho mình.",
                    dapAn2 = "Nhường đường cho xe lên dốc.",
                    dapAn3 = "Chỉ nhường đường khi xe lên dốc nháy đèn.",
                    dapAn4 = "",
                    dapAnDung = "2",
                    chuDe = "Khái niệm và Quy tắc"
                ),

                LyThuyet(
                    cauHoi = "Tại nơi đường giao nhau, người lái xe đang đi trên đường không ưu tiên phải nhường đường như thế nào là đúng quy tắc giao thông?",
                    dapAn1 = "Nhường đường cho xe đi ở bên phải mình tới.",
                    dapAn2 = "Nhường đường cho xe đi ở bên trái mình tới.",
                    dapAn3 = "Nhường đường cho xe đi trên đường ưu tiên hoặc đường chính từ bất kỳ hướng nào tới.",
                    dapAn4 = "",
                    dapAnDung = "3",
                    chuDe = "Khái niệm và Quy tắc"
                ),

                LyThuyet(
                    cauHoi = "Tại nơi đường giao nhau không có báo hiệu đi theo vòng xuyến, người điều khiển phương tiện phải nhường đường như thế nào là đúng quy tắc giao thông?",
                    dapAn1 = "Phải nhường đường cho xe đi đến từ bên phải.",
                    dapAn2 = "Xe báo hiệu xin đường trước xe đó được đi trước.",
                    dapAn3 = "Phải nhường đường cho xe đi đến từ bên trái.",
                    dapAn4 = "",
                    dapAnDung = "1",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Tại nơi đường bộ giao nhau cùng mức với đường sắt chỉ có đèn tín hiệu hoặc chuông báo hiệu, khi đèn tín hiệu màu đỏ đã bật sáng hoặc có tiếng chuông báo hiệu, người tham gia giao thông phải dừng lại ngay và giữ khoảng cách tối thiểu bao nhiêu mét tính từ ray gần nhất?",
                    dapAn1 = "5 mét.",
                    dapAn2 = "3 mét.",
                    dapAn3 = "4 mét.",
                    dapAn4 = "",
                    dapAnDung = "1",
                    chuDe = "Khái niệm và Quy tắc"
                ),

                LyThuyet(
                    cauHoi = "Người điều khiển phương tiện tham gia giao thông trong hầm đường bộ ngoài việc phải tuân thủ các quy tắc giao thông còn phải thực hiện những quy định nào dưới đây?",
                    dapAn1 = "Xe cơ giới, xe máy chuyên dùng phải bật đèn; xe thô sơ phải bật đèn hoặc có vật phát sáng báo hiệu; chỉ được dừng xe, đỗ xe ở nơi quy định.",
                    dapAn2 = "Xe cơ giới phải bật đèn ngay cả khi đường hầm sáng; phải cho xe chạy trên một làn đường và chỉ chuyển làn ở nơi được phép; được quay đầu xe, lùi xe khi cần thiết.",
                    dapAn3 = "Xe máy chuyên dùng phải bật đèn ngay cả khi đường hầm sáng; phải cho xe chạy trên một làn đường và chỉ chuyển làn ở nơi được phép; được quay đầu xe, lùi xe khi cần thiết.",
                    dapAn4 = "",
                    dapAnDung = "1",
                    chuDe = "Khái niệm và Quy tắc"
                ),

                LyThuyet(
                    cauHoi = "Người ngồi trên xe mô tô 2 bánh, xe gắn máy phải đội mũ bảo hiểm có cài quai đúng quy cách khi nào?",
                    dapAn1 = "Khi tham gia giao thông đường bộ.",
                    dapAn2 = "Chỉ khi đi trên đường chuyên dùng; đường cao tốc.",
                    dapAn3 = "Khi tham gia giao thông trên đường tỉnh lộ hoặc quốc lộ.",
                    dapAn4 = "",
                    dapAnDung = "1",
                    chuDe = "Khái niệm và Quy tắc"
                ),

                LyThuyet(
                    cauHoi = "Người điều khiển xe mô tô hai bánh, xe gắn máy được phép chở tối đa 2 người trong những trường hợp nào?",
                    dapAn1 = "Chở người bệnh đi cấp cứu; trẻ em dưới 14 tuổi.",
                    dapAn2 = "Áp giải người có hành vi vi phạm pháp luật.",
                    dapAn3 = "Cả ý 1 và ý 2.",
                    dapAn4 = "",
                    dapAnDung = "3",
                    chuDe = "Khái niệm và Quy tắc"
                ),

                LyThuyet(
                    cauHoi = "Người điều khiển xe mô tô hai bánh, xe gắn máy không được thực hiện những hành vi nào dưới đây?",
                    dapAn1 = "Đi vào phần đường dành cho người đi bộ và phương tiện khác; sử dụng ô, điện thoại di động, thiết bị âm thanh (trừ thiết bị trợ thính), đi xe dàn hàng ngang.",
                    dapAn2 = "Chở 02 người; trong đó, có người bị bệnh đi cấp cứu hoặc trẻ em dưới 14 tuổi hoặc áp giải người có hành vi vi phạm pháp luật.",
                    dapAn3 = "Điều khiển phương tiện tham gia giao thông trên đường tỉnh lộ hoặc quốc lộ.",
                    dapAn4 = "",
                    dapAnDung = "1",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Người điều khiển xe mô tô hai bánh, xe gắn máy có được đi xe dàn hàng ngang; xe đi vào phần đường dành cho người đi bộ và phương tiện khác; sử dụng ô, điện thoại di động, thiết bị âm thanh (trừ thiết bị trợ thính) hay không?",
                    dapAn1 = "Được phép nhưng phải đảm bảo an toàn.",
                    dapAn2 = "Không được phép.",
                    dapAn3 = "Được phép tùy từng hoàn cảnh, điều kiện cụ thể.",
                    dapAn4 = "",
                    dapAnDung = "2",
                    chuDe = "Khái niệm và Quy tắc"
                ),

                LyThuyet(
                    cauHoi = "Người lái xe phải giảm tốc độ thấp hơn tốc độ tối đa cho phép (có thể dừng lại một cách an toàn) trong trường hợp nào dưới đây?",
                    dapAn1 = "Khi có báo hiệu cảnh báo nguy hiểm hoặc có chướng ngại vật trên đường; khi chuyển hướng xe chạy hoặc tầm nhìn bị hạn chế; khi qua nơi đường giao nhau, nơi đường bộ giao nhau với đường sắt; đường vòng; đường có địa hình quanh co, đèo dốc.",
                    dapAn2 = "Khi qua cầu, cống hẹp; khi lên gần đỉnh dốc, khi xuống dốc, khi qua trường học, khu đông dân cư, khu vực đang thi công trên đường bộ; hiện trường xảy ra tai nạn giao thông.",
                    dapAn3 = "Khi điều khiển xe vượt xe khác trên đường quốc lộ, đường cao tốc.",
                    dapAn4 = "Cả ý 1 và ý 2.",
                    dapAnDung = "4",
                    chuDe = "Khái niệm và Quy tắc"
                ),

                LyThuyet(
                    cauHoi = "Tại ngã ba hoặc ngã tư không có đảo an toàn, người lái xe phải nhường đường như thế nào là đúng trong các trường hợp dưới đây?",
                    dapAn1 = "Nhường đường cho người đi bộ đang đi trên phần đường dành cho người đi bộ sang đường; nhường đường cho xe đi trên đường ưu tiên, đường chính từ bất kỳ hướng nào tới; nhường đường cho xe ưu tiên, xe đi từ bên phải đến.",
                    dapAn2 = "Nhường đường cho người đi bộ đang đứng chờ đi qua phần đường dành cho người đi bộ sang đường; nhường đường cho xe đi trên đường ngược chiều, đường nhánh từ bất kỳ hướng nào tới; nhường đường cho xe đi từ bên trái đến.",
                    dapAn3 = "Không phải nhường đường.",
                    dapAn4 = "",
                    dapAnDung = "1",
                    chuDe = "Khái niệm và Quy tắc"
                ),

                LyThuyet(
                    cauHoi = "Khi điều khiển xe cơ giới, người lái xe phải bật đèn tín hiệu báo rẽ trong trường hợp nào sau đây?",
                    dapAn1 = "Khi cho xe chạy thẳng.",
                    dapAn2 = "Trước khi thay đổi làn đường.",
                    dapAn3 = "Sau khi thay đổi làn đường.",
                    dapAn4 = "",
                    dapAnDung = "2",
                    chuDe = "Khái niệm và Quy tắc"
                ),

                LyThuyet(
                    cauHoi = "Trên đoạn đường hai chiều không có giải phân cách giữa, người lái xe không được vượt xe khác trong các trường hợp nào dưới đây?",
                    dapAn1 = "Xe bị vượt bất ngờ tăng tốc độ và cố tình không nhường đường.",
                    dapAn2 = "Xe bị vượt giảm tốc độ và nhường đường.",
                    dapAn3 = "Phát hiện có xe đi ngược chiều.",
                    dapAn4 = "Cả ý 1 và ý 3.",
                    dapAnDung = "4",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Người lái xe mô tô xử lý như thế nào khi cho xe mô tô phía sau vượt?",
                    dapAn1 = "Nếu đủ điều kiện an toàn, người lái xe phải giảm tốc độ, đi sát về bên phải của phần đường xe chạy cho đến khi xe sau đã vượt qua, không được gây trở ngại đối với xe xin vượt.",
                    dapAn2 = "Lái xe vào lề đường bên trái và giảm tốc độ để xe phía sau vượt qua, không được gây trở ngại đối với xe xin vượt.",
                    dapAn3 = "Nếu đủ điều kiện an toàn, người lái xe phải tăng tốc độ, đi sát về bên phải của phần đường chạy cho đến khi xe sau vượt qua.",
                    dapAn4 = "",
                    dapAnDung = "1",
                    chuDe = "Khái niệm và Quy tắc"
                ),

                LyThuyet(
                    cauHoi = "Trong các trường hợp dưới đây, để bảo đảm an toàn khi tham gia giao thông, người lái xe mô tô cần thực hiện như thế nào?",
                    dapAn1 = "Phải đội mũ bảo hiểm đạt chuẩn, có cài quai đúng quy cách, mặc quần áo gọn gàng; không sử dụng ô, điện thoại di động, thiết bị âm thanh (trừ thiết bị trợ thính).",
                    dapAn2 = "Phải đội mũ bảo hiểm khi trời mưa gió hoặc trời quá nắng; có thể sử dụng ô, điện thoại di động, thiết bị âm thanh nhưng phải đảm bảo an toàn.",
                    dapAn3 = "Phải đội mũ bảo hiểm khi cảm thấy mất an toàn giao thông hoặc khi chuẩn bị di chuyển quãng đường xa.",
                    dapAn4 = "",
                    dapAnDung = "1",
                    chuDe = "Khái niệm và Quy tắc"
                ),

                LyThuyet(
                    cauHoi = "Đường bộ trong khu vực đông dân cư gồm những đoạn đường nào dưới đây?",
                    dapAn1 = "Là đoạn đường nằm trong khu công nghiệp có đông người và phương tiện tham gia giao thông và được xác định cụ thể bằng biển chỉ dẫn địa giới.",
                    dapAn2 = "Là đoạn đường bộ nằm trong khu vực nội thành phố, nội thị xã, nội thị trấn và những đoạn đường có đông dân cư sinh sống sát dọc theo đường, có các hoạt động ảnh hưởng đến an toàn giao thông; được xác định bằng biển báo hiệu là đường khu đông dân cư.",
                    dapAn3 = "Là đoạn đường nằm ngoài khu vực nội thành phố, nội thị xã có đông người và phương tiện tham gia giao thông và được xác định cụ thể bằng biển chỉ dẫn địa giới.",
                    dapAn4 = "",
                    dapAnDung = "2",
                    chuDe = "Khái niệm và Quy tắc"
                ),

                LyThuyet(
                    cauHoi = "Tốc độ tối đa cho phép đối với xe máy chuyên dùng, xe gắn máy (kể cả xe máy điện) và các loại xe tương tự trên đường bộ (trừ đường cao tốc) không được vượt quá bao nhiêu km/h?",
                    dapAn1 = "50 km/h.",
                    dapAn2 = "40 km/h.",
                    dapAn3 = "60 km/h.",
                    dapAn4 = "",
                    dapAnDung = "2",
                    chuDe = "Khái niệm và Quy tắc"
                ),

                LyThuyet(
                    cauHoi = "Trên đường bộ (trừ đường cao tốc) trong khu vực đông dân cư, đường đôi có dải phân cách giữa, xe mô tô hai bánh, ô tô chở người đến 30 chỗ tham gia giao thông với tốc độ tối đa cho phép là bao nhiêu?",
                    dapAn1 = "60 km/h.",
                    dapAn2 = "50 km/h.",
                    dapAn3 = "40 km/h.",
                    dapAn4 = "",
                    dapAnDung = "1",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Trên đường bộ (trừ đường cao tốc) trong khu vực đông dân cư, đường hai chiều không có dải phân cách giữa, xe mô tô hai bánh, ô tô chở người đến 30 chỗ tham gia giao thông với tốc độ tối đa cho phép là bao nhiêu?",
                    dapAn1 = "60 km/h.",
                    dapAn2 = "50 km/h.",
                    dapAn3 = "40 km/h.",
                    dapAn4 = "",
                    dapAnDung = "2",
                    chuDe = "Khái niệm và Quy tắc"
                ),

                LyThuyet(
                    cauHoi = "Trên đường bộ (trừ đường cao tốc) trong khu vực đông dân cư, đường hai chiều hoặc đường một chiều có một làn xe cơ giới, loại xe nào dưới đây được tham gia giao thông với tốc độ tối đa cho phép là 50 km/h?",
                    dapAn1 = "Ô tô con, ô tô tải, ô tô chở người trên 30 chỗ.",
                    dapAn2 = "Xe gắn máy, xe máy chuyên dùng.",
                    dapAn3 = "Cả ý 1 và ý 2.",
                    dapAn4 = "",
                    dapAnDung = "1",
                    chuDe = "Khái niệm và Quy tắc"
                ),

                LyThuyet(
                    cauHoi = "Khi điều khiển xe chạy với tốc độ dưới 60 km/h, để đảm bảo khoảng cách an toàn giữa hai xe, người lái xe phải điều khiển xe như thế nào?",
                    dapAn1 = "Chủ động giữ khoảng cách an toàn phù hợp với xe chạy liền trước xe của mình.",
                    dapAn2 = "Đảm bảo khoảng cách an toàn theo mật độ phương tiện, tình hình giao thông thực tế.",
                    dapAn3 = "Cả ý 1 và ý 2.",
                    dapAn4 = "",
                    dapAnDung = "3",
                    chuDe = "Khái niệm và Quy tắc"
                ),

                LyThuyet(
                    cauHoi = "Người lái xe phải giảm tốc độ thấp hơn tốc độ tối đa cho phép đến mức cần thiết, chú ý quan sát và chuẩn bị sẵn sàng những tình huống có thể xảy ra để phòng ngừa tai nạn trong các trường hợp nào dưới đây?",
                    dapAn1 = "Gặp biển báo nguy hiểm trên đường.",
                    dapAn2 = "Gặp biển chỉ dẫn trên đường.",
                    dapAn3 = "Gặp biển báo hết mọi lệnh cấm.",
                    dapAn4 = "Gặp biển báo hết hạn chế tốc độ tối đa cho phép.",
                    dapAnDung = "1",
                    chuDe = "Khái niệm và Quy tắc"
                ),

                LyThuyet(
                    cauHoi = "Các phương tiện tham gia giao thông đường bộ (kể cả những xe có quyền ưu tiên) đều phải dừng lại bên phải đường của mình và trước vạch 'dừng xe' tại các điểm giao cắt giữa đường bộ và đường sắt khi có báo hiệu dừng nào dưới đây?",
                    dapAn1 = "Hiệu lệnh của nhân viên gác chắn.",
                    dapAn2 = "Đèn đỏ sáng nháy, cờ đỏ, biển đỏ.",
                    dapAn3 = "Còi, chuông kêu, chắn đã đóng.",
                    dapAn4 = "Tất cả các ý trên.",
                    dapAnDung = "4",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Tác dụng của mũ bảo hiểm đối với người ngồi trên xe mô tô hai bánh trong trường hợp xảy ra tai nạn giao thông là gì?",
                    dapAn1 = "Để làm đẹp.",
                    dapAn2 = "Để tránh mưa nắng.",
                    dapAn3 = "Để giảm thiểu chấn thương vùng đầu.",
                    dapAn4 = "Để các loại phương tiện khác dễ quan sát.",
                    dapAnDung = "3",
                    chuDe = "Khái niệm và Quy tắc"
                ),

                LyThuyet(
                    cauHoi = "Tại nơi đường giao nhau, người lái xe đang đi trên đường không ưu tiên phải xử lý như thế nào là đúng quy tắc giao thông?",
                    dapAn1 = "Tăng tốc độ qua đường giao nhau để đi trước xe đi trên đường ưu tiên.",
                    dapAn2 = "Giảm tốc độ qua đường giao nhau để đi trước xe đi trên đường ưu tiên.",
                    dapAn3 = "Nhường đường cho xe đi trên ưu tiên từ bất kỳ hướng nào tới.",
                    dapAn4 = "",
                    dapAnDung = "3",
                    chuDe = "Khái niệm và Quy tắc"
                ),

                LyThuyet(
                    cauHoi = "Người lái xe phải xử lý như thế nào khi quan sát phía trước thấy người đi bộ đang sang đường tại nơi có vạch đường dành cho người đi bộ để đảm bảo an toàn?",
                    dapAn1 = "Giảm tốc độ, đi từ từ để vượt qua trước người đi bộ.",
                    dapAn2 = "Giảm tốc độ, có thể dừng lại nếu cần thiết trước vạch dừng xe để nhường đường cho người đi bộ qua đường.",
                    dapAn3 = "Tăng tốc độ để vượt qua trước người đi bộ.",
                    dapAn4 = "",
                    dapAnDung = "2",
                    chuDe = "Khái niệm và Quy tắc"
                ),

                LyThuyet(
                    cauHoi = "Theo Luật Giao thông đường bộ, tín hiệu đèn giao thông gồm 3 màu nào dưới đây?",
                    dapAn1 = "Đỏ – Vàng – Xanh.",
                    dapAn2 = "Cam – Vàng – Xanh.",
                    dapAn3 = "Vàng – Xanh dương – Xanh lá.",
                    dapAn4 = "Đỏ – Cam – Xanh.",
                    dapAnDung = "1",
                    chuDe = "Khái niệm và Quy tắc"
                ),

                LyThuyet(
                    cauHoi = "Tại nơi đường giao nhau, khi đèn điều khiển giao thông có tín hiệu màu vàng, người điều khiển giao thông phải chấp hành như thế nào là đúng quy tắc giao thông?",
                    dapAn1 = "Phải cho xe dừng lại trước vạch dừng, trường hợp đã đi quá vạch dừng hoặc đã quá gần vạch dừng nếu dừng lại thấy nguy hiểm thì được đi tiếp.",
                    dapAn2 = "Trong trường hợp tín hiệu vàng nhấp nháy là được đi nhưng phải giảm tốc độ, chú ý quan sát nhường đường cho người đi bộ qua đường.",
                    dapAn3 = "Nhanh chóng tăng tốc độ, vượt qua nút giao và chú ý đảm bảo an toàn.",
                    dapAn4 = "Cả ý 1 và ý 2.",
                    dapAnDung = "4",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Để báo hiệu cho xe phía trước biết xe mô tô của bạn muốn vượt, bạn phải có tín hiệu như thế nào dưới đây?",
                    dapAn1 = "Ra tín hiệu bằng tay rồi cho xe vượt qua.",
                    dapAn2 = "Tăng ga mạnh để gây sự chú ý rồi cho xe vượt qua.",
                    dapAn3 = "Bạn phải có tín hiệu bằng đèn hoặc còi.",
                    dapAn4 = "",
                    dapAnDung = "3",
                    chuDe = "Khái niệm và Quy tắc"
                ),

                LyThuyet(
                    cauHoi = "Người điều khiển xe mô tô phải giảm tốc độ và hết sức thận trọng khi qua những đoạn đường nào dưới đây?",
                    dapAn1 = "Đường ướt, đường có sỏi cát trên nền đường.",
                    dapAn2 = "Đường hẹp có nhiều điểm giao cắt từ hai phía.",
                    dapAn3 = "Đường đèo dốc, vòng liên tục.",
                    dapAn4 = "Tất cả các ý nêu trên.",
                    dapAnDung = "4",
                    chuDe = "Khái niệm và Quy tắc"
                ),

                LyThuyet(
                    cauHoi = "Khi gặp xe buýt đang dừng đón, trả khách, người điều khiển xe mô tô phải xử lý như thế nào dưới đây để đảm bảo an toàn giao thông?",
                    dapAn1 = "Tăng tốc độ để nhanh chóng vượt qua bến đỗ.",
                    dapAn2 = "Giảm tốc độ đến mức an toàn có thể và quan sát người qua đường và từ từ vượt qua xe buýt.",
                    dapAn3 = "Yêu cầu phải dừng lại phía sau xe buýt chờ xe rời bến mới đi tiếp.",
                    dapAn4 = "",
                    dapAnDung = "2",
                    chuDe = "Khái niệm và Quy tắc"
                ),

                LyThuyet(
                    cauHoi = "Khái niệm về văn hóa giao thông được hiểu như thế nào là đúng?",
                    dapAn1 = "Là sự hiểu biết và chấp hành nghiêm chỉnh pháp luật về giao thông; là ý thức trách nhiệm với cộng đồng khi tham gia giao thông.",
                    dapAn2 = "Là ứng xử có văn hóa, có tình yêu thương con người trong các tình huống không may xảy ra khi tham gia giao thông.",
                    dapAn3 = "Cả ý 1 và ý 2.",
                    dapAn4 = "",
                    dapAnDung = "3",
                    chuDe = "Khái niệm và Quy tắc"
                ),

                LyThuyet(
                    cauHoi = "Trong các hành vi dưới đây, người lái xe mô tô có văn hóa giao thông phải ứng xử như thế nào?",
                    dapAn1 = "Điều khiển xe đi bên phải theo chiều đi của mình; đi đúng phần đường, làn đường quy định; đội mũ bảo hiểm đạt chuẩn, cài quai đúng quy định.",
                    dapAn2 = "Điều khiển xe đi trên phần đường, làn đường có ít phương tiện tham gia giao thông.",
                    dapAn3 = "Điều khiển xe và đội mũ bảo hiểm ở nơi có biển báo bắt buộc đội mũ bảo hiểm.",
                    dapAn4 = "",
                    dapAnDung = "1",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Trong các hành vi dưới đây, người lái xe ô tô, mô tô có văn hóa giao thông phải ứng xử như thế nào?",
                    dapAn1 = "Điều khiển xe đi bên phải theo chiều đi của mình; đi đúng phần đường, làn đường quy định; dừng, đỗ xe đúng nơi quy định; đã uống rượu, bia thì không lái xe.",
                    dapAn2 = "Điều khiển xe đi trên phần đường, làn đường có ít phương tiện giao thông; dừng xe, đỗ xe ở nơi thuận tiện hoặc theo yêu cầu của hành khách, của người thân.",
                    dapAn3 = "Dừng và đỗ xe ở nơi thuận tiện cho việc chuyên chở hành khách và giao nhận hàng hóa; sử dụng ít rượu, bia thì có thể lái xe.",
                    dapAn4 = "",
                    dapAnDung = "1",
                    chuDe = "Khái niệm và Quy tắc"
                ),

                LyThuyet(
                    cauHoi = "Khi xảy ra tai nạn giao thông, có người bị thương nghiêm trọng, người lái xe và người có mặt tại hiện trường vụ tai nạn phải thực hiện các công việc gì dưới đây?",
                    dapAn1 = "Thực hiện sơ cứu ban đầu trong trường hợp khẩn cấp; thông báo vụ tai nạn đến cơ quan thi hành pháp luật.",
                    dapAn2 = "Nhanh chóng lái xe gây tai nạn hoặc đi nhờ xe khác ra khỏi hiện trường vụ tai nạn.",
                    dapAn3 = "Cả ý 1 và ý 2.",
                    dapAn4 = "",
                    dapAnDung = "1",
                    chuDe = "Khái niệm và Quy tắc"
                ),

                LyThuyet(
                    cauHoi = "Trên đường đang xảy ra ùn tắc có những hành vi nào sau đây là thiếu văn hóa khi tham gia giao thông?",
                    dapAn1 = "Bấm còi liên tục thúc giục các phương tiện phía trước nhường đường.",
                    dapAn2 = "Đi lên vỉa hè, tận dụng mọi khoảng trống để nhanh chóng thoát khỏi nơi ùn tắc.",
                    dapAn3 = "Lấn sang trái đường cố gắng vượt lên xe khác.",
                    dapAn4 = "Tất cả các ý nêu trên.",
                    dapAnDung = "1",
                    chuDe = "Khái niệm và Quy tắc"
                ),

                LyThuyet(
                    cauHoi = "Khi điều khiển xe mô tô tay ga chạy xuống đường dốc dài, độ dốc cao, người lái xe cần thực hiện những thao tác nào dưới đây để đảm bảo an toàn?",
                    dapAn1 = "Giữ tay ga ở mức độ phù hợp, sử dụng phanh trước và phanh sau để giảm tốc độ.",
                    dapAn2 = "Nhả hết tay ga, tắt động cơ, sử dụng phanh trước và phanh sau để giảm tốc độ.",
                    dapAn3 = "Sử dụng phanh trước để giảm tốc độ kết hợp tắt chìa khóa điện của xe.",
                    dapAn4 = "",
                    dapAnDung = "4",
                    chuDe = "Khái niệm và Quy tắc"
                ),

                LyThuyet(
                    cauHoi = "Khi quay đầu xe, người lái xe cần phải quan sát và thực hiện các thao tác nào để đảm bảo an toàn giao thông?",
                    dapAn1 = "Quan sát biển báo hiệu để biết nơi được phép quay đầu; quan sát kỹ địa hình nơi chọn để quay đầu; lựa chọn quỹ đạo quay đầu xe cho thích hợp; quay đầu xe với tốc độ thấp; thường xuyên báo tín hiệu để người, các phương tiện xung quanh được biết; nếu quay đầu xe ở nơi nguy hiểm thì đưa đầu xe về phía nguy hiểm, đưa đuôi xe về phía an toàn.",
                    dapAn2 = "Quan sát biển báo hiệu để biết nơi được phép quay đầu; quan sát kỹ địa hình nơi chọn để quay đầu; lựa chọn quỹ đạo quay đầu xe; quay đầu xe với tốc độ tối đa; thường xuyên báo tín hiệu để người, các phương tiện xung quanh được biết; nếu quay đầu xe ở nơi nguy hiểm thì đưa đuôi xe về phía nguy hiểm và đầu xe về phía an toàn.",
                    dapAn3 = "",
                    dapAn4 = "",
                    dapAnDung = "1",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Khi tránh nhau trên đường hẹp, người lái xe cần phải chú ý những điểm nào để đảm bảo an toàn giao thông?",
                    dapAn1 = "Không nên đi cố vào đường hẹp; xe đi ở phía sườn núi nên dừng lại trước để nhường đường; khi dừng xe nhường đường phải đỗ ngay ngắn.",
                    dapAn2 = "Trong khi tránh nhau không nên đổi số; khi tránh nhau ban đêm, phải tắt đèn pha bật đèn cốt.",
                    dapAn3 = "Khi tránh nhau ban đêm, phải thường xuyên bật đèn pha tắt đèn cốt.",
                    dapAn4 = "Cả ý 1 và ý 2.",
                    dapAnDung = "4",
                    chuDe = "Khái niệm và Quy tắc"
                ),

                LyThuyet(
                    cauHoi = "Khi điều khiển xe trên đường vòng người lái xe cần phải làm gì để đảm bảo an toàn?",
                    dapAn1 = "Quan sát cẩn thận các chướng ngại vật và báo hiệu bằng còi, đèn; giảm tốc độ tới mức cần thiết, về số thấp và thực hiện quay vòng với tốc độ phù hợp với bán kính cong của đường vòng.",
                    dapAn2 = "Quan sát cẩn thận các chướng ngại vật và báo hiệu bằng còi, đèn; tăng tốc để nhanh chóng qua đường vòng và giảm tốc độ sau khi qua đường vòng.",
                    dapAn3 = "",
                    dapAn4 = "",
                    dapAnDung = "1",
                    chuDe = "Khái niệm và Quy tắc"
                ),

                LyThuyet(
                    cauHoi = "Để đạt được hiệu quả phanh cao nhất, người lái xe mô tô phải sử dụng các kỹ năng như thế nào dưới đây?",
                    dapAn1 = "Sử dụng phanh trước.",
                    dapAn2 = "Sử dụng phanh sau.",
                    dapAn3 = "Giảm hết ga; sử dụng đồng thời cả phanh sau và phanh trước.",
                    dapAn4 = "",
                    dapAnDung = "3",
                    chuDe = "Khái niệm và Quy tắc"
                ),

                LyThuyet(
                    cauHoi = "Khi đang lái xe mô tô và ô tô, nếu có nhu cầu sử dụng điện thoại để nhắn tin hoặc gọi điện, người lái xe phải thực hiện như thế nào trong các tình huống nêu dưới đây?",
                    dapAn1 = "Giảm tốc độ để đảm bảo an toàn với xe phía trước và sử dụng điện thoại để liên lạc.",
                    dapAn2 = "Giảm tốc độ để dừng xe ở nơi cho phép dừng xe sau đó sử dụng điện thoại để liên lạc.",
                    dapAn3 = "Tăng tốc độ để cách xa xe phía sau và sử dụng điện thoại để liên lạc.",
                    dapAn4 = "",
                    dapAnDung = "2",
                    chuDe = "Khái niệm và Quy tắc"
                ),

                LyThuyet(
                    cauHoi = "Những thói quen nào dưới đây khi điều khiển xe mô tô tay ga tham gia giao thông dễ gây tai nạn nguy hiểm?",
                    dapAn1 = "Sử dụng còi.",
                    dapAn2 = "Phanh đồng thời cả phanh trước và phanh sau.",
                    dapAn3 = "Chỉ sử dụng phanh trước.",
                    dapAn4 = "",
                    dapAnDung = "3",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Khi điều khiển xe mô tô quay đầu người lái xe cần thực hiện như thế nào để đảm bảo an toàn?",
                    dapAn1 = "Bật tín hiệu báo rẽ trước khi quay đầu, từ từ giảm tốc độ đến mức có thể dừng lại.",
                    dapAn2 = "Chỉ quay đầu xe ở những nơi được phép quay đầu.",
                    dapAn3 = "Quan sát an toàn các phương tiện tới từ phía trước, phía sau, hai bên đồng thời nhường đường cho xe từ bên phải và phía trước đi tới.",
                    dapAn4 = "Tất cả các ý nêu trên.",
                    dapAnDung = "4",
                    chuDe = "Khái niệm và Quy tắc"
                ),

                LyThuyet(
                    cauHoi = "Tay ga trên xe mô tô hai bánh có tác dụng gì trong các trường hợp dưới đây?",
                    dapAn1 = "Để điều khiển xe chạy về phía trước.",
                    dapAn2 = "Để điều tiết công suất động cơ qua đó điều khiển tốc độ của xe.",
                    dapAn3 = "Để điều khiển xe chạy lùi.",
                    dapAn4 = "Cả ý 1 và ý 2.",
                    dapAnDung = "4",
                    chuDe = "Khái niệm và Quy tắc"
                ),

                LyThuyet(
                    cauHoi = "Gương chiếu hậu của xe mô tô hai bánh có tác dụng gì trong các trường hợp dưới đây?",
                    dapAn1 = "Để quan sát an toàn phía bên trái khi chuẩn bị rẽ trái.",
                    dapAn2 = "Để quan sát an toàn phía bên phải khi chuẩn bị rẽ phải.",
                    dapAn3 = "Để quan sát an toàn phía sau cả bên trái và bên phải trước khi chuyển hướng.",
                    dapAn4 = "Để quan sát an toàn phía trước cả bên trái và bên phải trước khi chuyển hướng.",
                    dapAnDung = "3",
                    chuDe = "Khái niệm và Quy tắc"
                ),

                LyThuyet(
                    cauHoi = "Để đảm bảo an toàn khi tham gia giao thông, người lái xe mô tô hai bánh cần điều khiển tay ga như thế nào trong các trường hợp dưới đây?",
                    dapAn1 = "Tăng ga thật nhanh, giảm ga từ từ.",
                    dapAn2 = "Tăng ga thật nhanh, giảm ga thật nhanh.",
                    dapAn3 = "Tăng ga từ từ, giảm ga thật nhanh.",
                    dapAn4 = "Tăng ga từ từ, giảm ga từ từ.",
                    dapAnDung = "3",
                    chuDe = "Khái niệm và Quy tắc"
                ),

                LyThuyet(
                    cauHoi = "Kỹ thuật cơ bản để giữ thăng bằng khi điều khiển xe mô tô đi trên đường gồ ghề như thế nào trong các trường hợp dưới đây?",
                    dapAn1 = "Đứng thẳng trên giá gác chân lái sau đó hơi gập đầu gối và khuỷu tay, đi chậm để không nẩy quá mạnh.",
                    dapAn2 = "Ngồi lùi lại phía sau, tăng ga vượt nhanh qua đoạn đường xóc.",
                    dapAn3 = "Ngồi lệch sang bên trái hoặc bên phải để lấy thăng bằng qua đoạn đường gồ ghề.",
                    dapAn4 = "",
                    dapAnDung = "1",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Biển nào cấm máy kéo?",
                    dapAn1 = "Biển 1",
                    dapAn2 = "Biển 2 và 3",
                    dapAn3 = "Biển 1 và 3",
                    dapAn4 = "Cả ba biển",
                    dapAnDung = "2",
                    hinhAnh = R.drawable.bbdb1,
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Biển nào báo hiệu cấm xe mô tô ba bánh đi vào?",
                    dapAn1 = "Biển 1 và 2",
                    dapAn2 = "Biển 1 và 3",
                    dapAn3 = "Biển 2 và 3",
                    dapAn4 = "",
                    dapAnDung = "1",
                    hinhAnh = R.drawable.bbdb2,
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Biển nào dưới đây xe gắn máy được phép đi vào?",
                    dapAn1 = "Biển 1",
                    dapAn2 = "Biển 2",
                    dapAn3 = "Cả hai biển",
                    dapAn4 = "",
                    dapAnDung = "3",
                    hinhAnh = R.drawable.bbdb3,
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Biển nào báo hiệu cấm xe mô tô hai bánh đi vào?",
                    dapAn1 = "Biển 1",
                    dapAn2 = "Biển 2",
                    dapAn3 = "Biển 3",
                    dapAn4 = "",
                    dapAnDung = "1",
                    hinhAnh = R.drawable.bbdb4,
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Khi gặp biển nào thì xe mô tô hai bánh được đi vào?",
                    dapAn1 = "Không biển nào",
                    dapAn2 = "Biển 1 và 2",
                    dapAn3 = "Biển 2 và 3",
                    dapAn4 = "Cả ba biển",
                    dapAnDung = "3",
                    hinhAnh = R.drawable.bbdb5,
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Biển nào cấm quay xe?",
                    dapAn1 = "Biển 1",
                    dapAn2 = "Biển 2",
                    dapAn3 = "Không biển nào",
                    dapAn4 = "Cả 2 biển",
                    dapAnDung = "2",
                    hinhAnh = R.drawable.bbdb6,
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Biển nào cấm xe rẽ trái?",
                    dapAn1 = "Biển 1",
                    dapAn2 = "Biển 2",
                    dapAn3 = "Cả hai biển",
                    dapAn4 = "",
                    dapAnDung = "1",
                    hinhAnh = R.drawable.bbdb7,
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Khi gặp biển nào xe được rẽ trái?",
                    dapAn1 = "Biển 1",
                    dapAn2 = "Biển 2",
                    dapAn3 = "Không biển nào",
                    dapAn4 = "",
                    dapAnDung = "2",
                    hinhAnh = R.drawable.bbdb8,
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Biển nào cấm các phương tiện giao thông đường bộ rẽ phải?",
                    dapAn1 = "Biển 1 và 2",
                    dapAn2 = "Biển 1 và 3",
                    dapAn3 = "Biển 2 và 3",
                    dapAn4 = "Cả ba biển",
                    dapAnDung = "1",
                    hinhAnh = R.drawable.bbdb9,
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Biển nào cấm các phương tiện giao thông đường bộ rẽ trái?",
                    dapAn1 = "Biển 1 và 2",
                    dapAn2 = "Biển 1 và 3",
                    dapAn3 = "Biển 2 và 3",
                    dapAn4 = "Cả ba biển",
                    dapAnDung = "1",
                    hinhAnh = R.drawable.bbdb10,
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Biển nào cho phép xe rẽ trái?",
                    dapAn1 = "Biển 1",
                    dapAn2 = "Biển 2",
                    dapAn3 = "Không biển nào",
                    dapAn4 = "",
                    dapAnDung = "2",
                    hinhAnh = R.drawable.bbdb11,
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Biển nào xe quay đầu không bị cấm?",
                    dapAn1 = "Biển 1",
                    dapAn2 = "Biển 2",
                    dapAn3 = "Cả 2 biển",
                    dapAn4 = "",
                    dapAnDung = "3",
                    hinhAnh = R.drawable.bbdb12,
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Biển nào xe được phép quay đầu nhưng không được rẽ trái?",
                    dapAn1 = "Biển 1",
                    dapAn2 = "Biển 2",
                    dapAn3 = "Cả 2 biển",
                    dapAn4 = "",
                    dapAnDung = "1",
                    hinhAnh = R.drawable.bbdb13,
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Biển nào là biển cấm đi ngược chiều?",
                    dapAn1 = "Biển 1",
                    dapAn2 = "Biển 2",
                    dapAn3 = "Cả ba biển",
                    dapAn4 = "",
                    dapAnDung = "2",
                    hinhAnh = R.drawable.bbdb14,
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Biển nào dưới đây các phương tiện không được phép đi vào?",
                    dapAn1 = "Biển 1",
                    dapAn2 = "Biển 2",
                    dapAn3 = "Biển 1 và 2",
                    dapAn4 = "",
                    dapAnDung = "3",
                    hinhAnh = R.drawable.bbdb15,
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Khi gặp biển nào xe ưu tiên theo luật định vẫn phải dừng?",
                    dapAn1 = "Biển 1",
                    dapAn2 = "Biển 2",
                    dapAn3 = "Cả ba biển",
                    dapAn4 = "",
                    dapAnDung = "2",
                    hinhAnh = R.drawable.bbdb16,
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Biển nào cấm tất cả các loại xe cơ giới và thô sơ đi lại trên đường, trừ xe ưu tiên theo luật định?",
                    dapAn1 = "Biển 1",
                    dapAn2 = "Biển 2",
                    dapAn3 = "Cả hai biển",
                    dapAn4 = "",
                    dapAnDung = "1",
                    hinhAnh = R.drawable.bbdb17,
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Biển báo này có ý nghĩa như thế nào?",
                    dapAn1 = "Tốc độ tối đa cho phép về ban đêm cho các phương tiện là 70 km/h",
                    dapAn2 = "Tốc độ tối thiểu cho phép về ban đêm cho các phương tiện là 70 km/h",
                    dapAn4 = "",
                    dapAn3 = "",
                    dapAnDung = "1",
                    hinhAnh = R.drawable.bbdb18,
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Biển này có hiệu lực đối với xe mô tô hai, ba bánh không?",
                    dapAn1 = "Có",
                    dapAn2 = "Không",
                    dapAn3 = "",
                    dapAn4 = "",
                    dapAnDung = "1",
                    hinhAnh = R.drawable.bbdb19,
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Biển này có ý nghĩa gì?",
                    dapAn1 = "Cấm xe cơ giới đi thẳng",
                    dapAn2 = "Cấm xe ô tô và mô tô đi về bên trái và bên phải",
                    dapAn3 = "Hướng trái và phải không cấm xe cơ giới",
                    dapAn4 = "",
                    dapAnDung = "2",
                    hinhAnh = R.drawable.bbdb20,
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Biển phụ đặt dưới biển cấm bóp còi có ý nghĩa gì?",
                    dapAn1 = "Báo khoảng cách đến nơi cấm bóp còi",
                    dapAn2 = "Chiều dài đoạn đường cấm bóp còi từ nơi đặt biển",
                    dapAn3 = "Báo cấm dùng còi có độ vang xa 500m",
                    dapAn4 = "",
                    dapAnDung = "2",
                    hinhAnh = R.drawable.bbdb21,
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Chiều dài đoạn đường dài 500m từ nơi đặt biển này, người lái xe có được phép bấm còi không?",
                    dapAn1 = "Được phép",
                    dapAn2 = "Không được phép",
                    dapAn3 = "",
                    dapAn4 = "",
                    dapAnDung = "2",
                    hinhAnh = R.drawable.bbdb22,
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Biển nào xe mô tô hai bánh được đi vào?",
                    dapAn1 = "Biển 1 và 2",
                    dapAn2 = "Biển 1 và 3",
                    dapAn3 = "Biển 2 và 3",
                    dapAn4 = "",
                    dapAnDung = "2",
                    hinhAnh = R.drawable.bbdb23,
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Biển nào xe mô tô hai bánh không được đi vào?",
                    dapAn1 = "Biển 1",
                    dapAn2 = "Biển 2",
                    dapAn3 = "Biển 3",
                    dapAn4 = "",
                    dapAnDung = "2",
                    hinhAnh = R.drawable.bbdb24,
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Ba biển này có hiệu lực như thế nào?",
                    dapAn1 = "Cấm các loại xe ở biển phụ đi vào",
                    dapAn2 = "Cấm các loại xe cơ giới đi vào trừ loại xe ở biển phụ",
                    dapAn3 = "",
                    dapAn4 = "",
                    dapAnDung = "1",
                    hinhAnh = R.drawable.bbdb25,
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Biển này có ý nghĩa gì?",
                    dapAn1 = "Cấm dừng xe về hướng bên trái",
                    dapAn2 = "Cấm đỗ xe và cấm dừng xe theo hướng bên phải",
                    dapAn3 = "Được phép đỗ xe và dừng xe theo hướng bên phải",
                    dapAn4 = "",
                    dapAnDung = "2",
                    hinhAnh = R.drawable.bbdb26,
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Biển nào là biển tốc độ tối đa cho phép về ban đêm?",
                    dapAn1 = "Biển 1",
                    dapAn2 = "Biển 2",
                    dapAn3 = "Cả 2 biển",
                    dapAn4 = "",
                    dapAnDung = "1",
                    hinhAnh = R.drawable.bbdb27,
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Biển nào báo hiệu hạn chế tốc độ của phương tiện không vượt quá trị số ghi trên biển?",
                    dapAn1 = "Biển 1",
                    dapAn2 = "Biển 2",
                    dapAn3 = "",
                    dapAn4 = "",
                    dapAnDung = "2",
                    hinhAnh = R.drawable.bbdb28,
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Số 50 trên biển báo dưới đây có ý nghĩa gì?",
                    dapAn1 = "Tốc độ tối đa các xe cơ giới được phép chạy",
                    dapAn2 = "Tốc độ tối thiểu các xe cơ giới được phép chạy",
                    dapAn3 = "",
                    dapAn4 = "",
                    dapAnDung = "1",
                    hinhAnh = R.drawable.bbdb29,
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Biển báo này có ý nghĩa gì?",
                    dapAn1 = "Báo hiệu tốc độ tối đa cho phép các xe cơ giới chạy",
                    dapAn2 = "Báo hiệu tốc độ tối thiểu cho phép các xe cơ giới chạy",
                    dapAn3 = "",
                    dapAn4 = "",
                    dapAnDung = "2",
                    hinhAnh = R.drawable.bbdb30,
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Gặp biển nào người lái xe phải nhường đường cho người đi bộ?",
                    dapAn1 = "Biển 1",
                    dapAn2 = "Biển 2",
                    dapAn3 = "Biển 3",
                    dapAn4 = "Biển 1 và 3",
                    dapAnDung = "1",
                    hinhAnh = R.drawable.bbdb31,
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Biển nào chỉ đường dành cho người đi bộ, các loại xe không được đi vào khi gặp biển này?",
                    dapAn1 = "Biển 1",
                    dapAn2 = "Biển 1 và 3",
                    dapAn3 = "Biển 3",
                    dapAn4 = "Cả ba biển",
                    dapAnDung = "3",
                    hinhAnh = R.drawable.bbdb32,
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Biển nào báo hiệu ‘đường dành cho xe thô sơ’?",
                    dapAn1 = "Biển 1",
                    dapAn2 = "Biển 2",
                    dapAn3 = "Biển 3",
                    dapAn4 = "",
                    dapAnDung = "1",
                    hinhAnh = R.drawable.bbdb33,
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Biển nào báo hiệu sắp đến chỗ giao nhau nguy hiểm?",
                    dapAn1 = "Biển 1",
                    dapAn2 = "Biển 1 và 2",
                    dapAn3 = "Biển 2 và 3",
                    dapAn4 = "Cả ba biển",
                    dapAnDung = "4",
                    hinhAnh = R.drawable.bbdb34,
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Biển nào báo hiệu ‘giao nhau với đường sắt có rào chắn’?",
                    dapAn1 = "Biển 1",
                    dapAn2 = "Biển 2 và 3",
                    dapAn3 = "Biển 3",
                    dapAn4 = "",
                    dapAnDung = "1",
                    hinhAnh = R.drawable.bbdb35,
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Biển nào báo hiệu ‘giao nhau có tín hiệu đèn’?",
                    dapAn1 = "Biển 1",
                    dapAn2 = "Biển 2",
                    dapAn3 = "Biển 3",
                    dapAn4 = "Cả ba biển",
                    dapAnDung = "3",
                    hinhAnh = R.drawable.bbdb36,
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Biển nào báo hiệu nguy hiểm giao nhau với đường sắt?",
                    dapAn1 = "Biển 1 và 2",
                    dapAn2 = "Biển 1 và 3",
                    dapAn3 = "Biển 2 và 3",
                    dapAn4 = "Cả ba biển",
                    dapAnDung = "2",
                    hinhAnh = R.drawable.bbdb37,
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Biển nào báo hiệu đường bộ giao nhau với đường sắt không có rào chắn?",
                    dapAn1 = "Biển 1 và 2",
                    dapAn2 = "Biển 1 và 3",
                    dapAn3 = "Biển 2 và 3",
                    dapAn4 = "Cả ba biển",
                    dapAnDung = "3",
                    hinhAnh = R.drawable.bbdb38,
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Biển nào báo hiệu sắp đến chỗ giao nhau giữa đường bộ và đường sắt?",
                    dapAn1 = "Biển 1",
                    dapAn2 = "Biển 2",
                    dapAn3 = "Biển 3",
                    dapAn4 = "Biển 1 và 3",
                    dapAnDung = "1",
                    hinhAnh = R.drawable.bbdb39,
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Biển nào báo hiệu ‘cửa chui’?",
                    dapAn1 = "Biển 1",
                    dapAn2 = "Biển 2",
                    dapAn3 = "Biển 3",
                    dapAn4 = "",
                    dapAnDung = "2",
                    hinhAnh = R.drawable.bbdb40,
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Hai biển này có ý nghĩa gì?",
                    dapAn1 = "Nơi đường sắt giao vuông góc với đường bộ không có rào chắn",
                    dapAn2 = "Báo trước sắp đến vị trí giao cắt đường bộ với đường sắt cùng mức, không vuông góc và không có rào chắn",
                    dapAn3 = "Nơi đường sắt giao nhau với đường bộ",
                    dapAn4 = "",
                    dapAnDung = "1",
                    hinhAnh = R.drawable.bbdb41,
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Biển nào báo hiệu hết đoạn đường ưu tiên?",
                    dapAn1 = "Biển 1",
                    dapAn2 = "Biển 2",
                    dapAn3 = "Biển 3",
                    dapAn4 = "",
                    dapAnDung = "3",
                    hinhAnh = R.drawable.bbdb42,
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Biển nào báo hiệu, chỉ dẫn xe đi trên đường này được quyền ưu tiên qua nơi giao nhau?",
                    dapAn1 = "Biển 1 và 2",
                    dapAn2 = "Biển 1 và 3",
                    dapAn3 = "Biển 2 và 3",
                    dapAn4 = "Cả ba biển",
                    dapAnDung = "2",
                    hinhAnh = R.drawable.bbdb43,
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Biển nào báo hiệu giao nhau với đường không ưu tiên?",
                    dapAn1 = "Biển 1",
                    dapAn2 = "Biển 2",
                    dapAn3 = "Biển 3",
                    dapAn4 = "Biển 2 và 3",
                    dapAnDung = "1",
                    hinhAnh = R.drawable.bbdb44,
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Biển nào báo hiệu giao nhau với đường ưu tiên?",
                    dapAn1 = "Biển 1",
                    dapAn2 = "Biển 2",
                    dapAn3 = "Biển 3",
                    dapAn4 = "Cả ba biển",
                    dapAnDung = "3",
                    hinhAnh = R.drawable.bbdb45,
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Biển nào báo hiệu đường giao nhau của các tuyến đường cùng cấp?",
                    dapAn1 = "Biển 1",
                    dapAn2 = "Biển 2",
                    dapAn3 = "Biển 3",
                    dapAn4 = "",
                    dapAnDung = "1",
                    hinhAnh = R.drawable.bbdb46,
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Biển nào báo hiệu đường hai chiều?",
                    dapAn1 = "Biển 1",
                    dapAn2 = "Biển 2",
                    dapAn3 = "Biển 3",
                    dapAn4 = "",
                    dapAnDung = "1",
                    hinhAnh = R.drawable.bbdb47,
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Biển nào báo hiệu phải giảm tốc độ, nhường đường cho xe cơ giới đi ngược chiều qua đường hẹp?",
                    dapAn1 = "Biển 1",
                    dapAn2 = "Biển 2",
                    dapAn3 = "Biển 3",
                    dapAn4 = "",
                    dapAnDung = "2",
                    hinhAnh = R.drawable.bbdb48,
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Biển nào chỉ dẫn được ưu tiên qua đường hẹp?",
                    dapAn1 = "Biển 1",
                    dapAn2 = "Biển 2",
                    dapAn3 = "Biển 3",
                    dapAn4 = "Biển 2 và 3",
                    dapAnDung = "3",
                    hinhAnh = R.drawable.bbdb49,
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Biển nào báo hiệu đường đôi?",
                    dapAn1 = "Biển 1",
                    dapAn2 = "Biển 2",
                    dapAn3 = "Biển 3",
                    dapAn4 = "",
                    dapAnDung = "3",
                    hinhAnh = R.drawable.bbdb50,
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Biển nào báo hiệu kết thúc đường đôi?",
                    dapAn1 = "Biển 1",
                    dapAn2 = "Biển 2",
                    dapAn3 = "Biển 3",
                    dapAn4 = "",
                    dapAnDung = "3",
                    hinhAnh = R.drawable.bbdb51,
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Biển nào báo hiệu giao nhau với đường hai chiều?",
                    dapAn1 = "Biển 1",
                    dapAn2 = "Biển 2",
                    dapAn3 = "Biển 3",
                    dapAn4 = "",
                    dapAnDung = "1",
                    hinhAnh = R.drawable.bbdb52,
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Biển nào báo hiệu giao nhau với đường hai chiều?",
                    dapAn1 = "Biển 1",
                    dapAn2 = "Biển 2",
                    dapAn3 = "Biển 3",
                    dapAn4 = "",
                    dapAnDung = "2",
                    hinhAnh = R.drawable.bbdb53,
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Biển nào báo hiệu chú ý chướng ngại vật?",
                    dapAn1 = "Biển 1",
                    dapAn2 = "Biển 2 và 3",
                    dapAn3 = "Cả ba biển",
                    dapAn4 = "",
                    dapAnDung = "2",
                    hinhAnh = R.drawable.bbdb54,
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Biển nào báo hiệu đường hầm?",
                    dapAn1 = "Cả ba biển",
                    dapAn2 = "Biển 2",
                    dapAn3 = "Biển 2 và 3",
                    dapAn4 = "",
                    dapAnDung = "2",
                    hinhAnh = R.drawable.bbdb55,
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Biển nào dưới đây là biển cầu hẹp?",
                    dapAn1 = "Biển 1",
                    dapAn2 = "Biển 2",
                    dapAn3 = "Biển 3",
                    dapAn4 = "",
                    dapAnDung = "2",
                    hinhAnh = R.drawable.bbdb56,
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Gặp biển nào người tham gia giao thông phải đi chậm và thận trọng để phòng khả năng xuất hiện và di chuyển bất ngờ của trẻ em trên mặt đường?",
                    dapAn1 = "Biển 1",
                    dapAn2 = "Biển 2",
                    dapAn3 = "",
                    dapAn4 = "",
                    dapAnDung = "2",
                    hinhAnh = R.drawable.bbdb57,
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Biển nào chỉ dẫn nơi bắt đầu đoạn đường dành cho người đi bộ?",
                    dapAn1 = "Biển 1",
                    dapAn2 = "Biển 2",
                    dapAn3 = "Biển 3",
                    dapAn4 = "",
                    dapAnDung = "2",
                    hinhAnh = R.drawable.bbdb58,
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Biển nào báo hiệu gần đến đoạn đường thường có trẻ em đi ngang qua?",
                    dapAn1 = "Biển 1",
                    dapAn2 = "Biển 2",
                    dapAn3 = "Biển 3",
                    dapAn4 = "Cả ba biển",
                    dapAnDung = "2",
                    hinhAnh = R.drawable.bbdb59,
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Biển nào sau đây là biển dốc xuống nguy hiểm?",
                    dapAn1 = "Biển 1",
                    dapAn2 = "Biển 2",
                    dapAn3 = "",
                    dapAn4 = "",
                    dapAnDung = "1",
                    hinhAnh = R.drawable.bbdb60,
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Biển báo này có ý nghĩa gì?",
                    dapAn1 = "Báo hiệu đường có ổ gà, lồi lõm",
                    dapAn2 = "Báo hiệu đường có gờ giảm tốc phía trước",
                    dapAn3 = "",
                    dapAn4 = "",
                    dapAnDung = "1",
                    hinhAnh = R.drawable.bbdb61,
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Các biển báo này có ý nghĩa gì?",
                    dapAn1 = "Để báo trước gần tới đoạn đường có hiện tượng đất đã từ trên ta luy dương sạt lở bất ngờ gây nguy hiểm cho xe cộ và người đi đường.",
                    dapAn2 = "Để báo trước nơi có kết cấu mặt đường rời rạc, khi phương tiện đi qua, làm cho các viên đá, sỏi văng lên gây nguy hiểm và mất an toàn cho người và phương tiện tham gia giao thông.",
                    dapAn3 = "Để cảnh báo những đoạn nền đường yếu, đoạn đường đang theo dõi lún mà việc vận hành xe ở tốc độ cao có thể gây nguy hiểm.",
                    dapAn4 = "",
                    dapAnDung = "1",
                    hinhAnh = R.drawable.bbdb62,
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Biển nào báo hiệu các phương tiện phải tuân thủ tốc độ tối đa cho phép trên từng làn đường?",
                    dapAn1 = "Biển 1",
                    dapAn2 = "Biển 2",
                    dapAn3 = "Cả hai biển",
                    dapAn4 = "",
                    dapAnDung = "3",
                    hinhAnh = R.drawable.bbdb63,
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Khi gặp biển nào thì các phương tiện không được đi vào, trừ ô tô và mô tô?",
                    dapAn1 = "Biển 1",
                    dapAn2 = "Biển 2",
                    dapAn3 = "",
                    dapAn4 = "",
                    dapAnDung = "1",
                    hinhAnh = R.drawable.bbdb64,
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Biển này có ý nghĩa gì?",
                    dapAn1 = "Chỉ hướng đi phải theo",
                    dapAn2 = "Biển báo hiệu cho người lái xe biết số lượng làn đường trên mặt đường và hướng đi trên mỗi làn đường phải theo",
                    dapAn3 = "Chỉ hướng đường phải theo",
                    dapAn4 = "",
                    dapAnDung = "2",
                    hinhAnh = R.drawable.bbdb65,
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Biển nào (đặt trước ngã ba, ngã tư) cho phép xe được rẽ sang hướng khác?",
                    dapAn1 = "Biển 1",
                    dapAn2 = "Biển 2",
                    dapAn3 = "Không biển nào",
                    dapAn4 = "",
                    dapAnDung = "3",
                    hinhAnh = R.drawable.bbdb66,
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Biển nào báo hiệu “hướng đi thẳng phải theo”?",
                    dapAn1 = "Biển 1",
                    dapAn2 = "Biển 2",
                    dapAn3 = "",
                    dapAn4 = "",
                    dapAnDung = "1",
                    hinhAnh = R.drawable.bbdb67,
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Biển nào báo hiệu “đường một chiều”?",
                    dapAn1 = "Biển 1",
                    dapAn2 = "Biển 2",
                    dapAn3 = "Cả hai biển",
                    dapAn4 = "",
                    dapAnDung = "2",
                    hinhAnh = R.drawable.bbdb68,
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Trong các biển dưới đây biển nào là biển “hết tốc độ tối đa cho phép”?",
                    dapAn1 = "Biển 1",
                    dapAn2 = "Biển 2",
                    dapAn3 = "Biển 3",
                    dapAn4 = "Cả ba biển",
                    dapAnDung = "1",
                    hinhAnh = R.drawable.bbdb69,
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Trong các biển dưới đây biển nào là biển “hết tốc độ tối thiểu”?",
                    dapAn1 = "Biển 1",
                    dapAn2 = "Biển 2",
                    dapAn3 = "Biển 3",
                    dapAn4 = "Cả ba biển",
                    dapAnDung = "3",
                    hinhAnh = R.drawable.bbdb70,
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Biển nào dưới đây báo hiệu hết cấm vượt?",
                    dapAn1 = "Biển 1",
                    dapAn2 = "Biển 2",
                    dapAn3 = "Biển 3",
                    dapAn4 = "Biển 2 và 3",
                    dapAnDung = "4",
                    hinhAnh = R.drawable.bbdb71,
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Trong các biển dưới đây biển nào là biển “hết mọi lệnh cấm”?",
                    dapAn1 = "Biển 1",
                    dapAn2 = "Biển 2",
                    dapAn3 = "Biển 3",
                    dapAn4 = "Cả ba biển",
                    dapAnDung = "2",
                    hinhAnh = R.drawable.bbdb72,
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Biển nào cho phép được quay đầu xe đi theo hướng ngược lại khi đặt biển trước ngã ba, ngã tư?",
                    dapAn1 = "Biển 1",
                    dapAn2 = "Biển 2",
                    dapAn3 = "Biển 3",
                    dapAn4 = "Không biển nào",
                    dapAnDung = "3",
                    hinhAnh = R.drawable.bbdb73,
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Biển nào không cho phép rẽ phải?",
                    dapAn1 = "Biển 1",
                    dapAn2 = "Biển 2",
                    dapAn3 = "Biển 3",
                    dapAn4 = "Biển 1 và 3",
                    dapAnDung = "1",
                    hinhAnh = R.drawable.bbdb74,
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Khi đến chỗ giao nhau, gặp biển nào thì người lái xe không được cho xe đi thẳng, phải rẽ sang hướng khác?",
                    dapAn1 = "Biển 1 và 2",
                    dapAn2 = "Biển 1 và 3",
                    dapAn3 = "Biển 2 và 3",
                    dapAn4 = "Cả ba biển",
                    dapAnDung = "1",
                    hinhAnh = R.drawable.bbdb75,
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Biển 1 có ý nghĩa gì?",
                    dapAn1 = "Biển chỉ dẫn hết cấm đỗ xe theo giờ trong khu vực.",
                    dapAn2 = "Biển chỉ dẫn hết hiệu lực đỗ xe trên các tuyến đường đối ngoại.",
                    dapAn3 = "Biển chỉ dẫn khu vực đỗ xe trên các tuyến đường đối ngoại.",
                    dapAn4 = "",
                    dapAnDung = "1",
                    hinhAnh = R.drawable.bbdb76,
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Biển nào chỉ dẫn cho người đi bộ sử dụng cầu vượt qua đường?",
                    dapAn1 = "Biển 1",
                    dapAn2 = "Biển 2",
                    dapAn3 = "Cả hai biển",
                    dapAn4 = "Không biển nào",
                    dapAnDung = "1",
                    hinhAnh = R.drawable.bbdb77,
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Biển nào chỉ dẫn cho người đi bộ sử dụng hầm chui qua đường?",
                    dapAn1 = "Biển 1",
                    dapAn2 = "Biển 2",
                    dapAn3 = "Cả hai biển",
                    dapAn4 = "Không biển nào",
                    dapAnDung = "2",
                    hinhAnh = R.drawable.bbdb78,
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Biển nào báo hiệu nơi đỗ xe cho người tàn tật?",
                    dapAn1 = "Biển 1",
                    dapAn2 = "Biển 2",
                    dapAn3 = "Biển 3",
                    dapAn4 = "",
                    dapAnDung = "2",
                    hinhAnh = R.drawable.bbdb79,
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Gặp biển báo này, người tham gia giao thông phải xử lý như thế nào?",
                    dapAn1 = "Dừng xe tại khu vực có trạm cảnh sát giao thông",
                    dapAn2 = "Tiếp tục lưu thông với tốc độ bình thường",
                    dapAn3 = "Phải giảm tốc độ đến mức độ an toàn và không được vượt khi đi qua khu vực này.",
                    dapAn4 = "",
                    dapAnDung = "3",
                    hinhAnh = R.drawable.bbdb80,
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Gặp biển báo này người lái xe có bắt buộc phải chạy vòng theo đảo an toàn theo hướng mũi tên khi muốn chuyển hướng hay không?",
                    dapAn1 = "Bắt buộc",
                    dapAn2 = "Không bắt buộc",
                    dapAn3 = "",
                    dapAn4 = "",
                    dapAnDung = "1",
                    hinhAnh = R.drawable.bbdb81,
                    chuDe = "Biển báo đường bộ"
                ),
                LyThuyet(
                    cauHoi = "Biển nào báo hiệu “cầu vượt liên thông”?",
                    dapAn1 = "Biển 2 và 3",
                    dapAn2 = "Biển 1 và 2",
                    dapAn3 = "Biển 1 và 3",
                    dapAn4 = "Cả ba biển",
                    dapAnDung = "3",
                    hinhAnh = R.drawable.bbdb82,
                    chuDe = "Biển báo đường bộ"
                ),

                LyThuyet(
                    cauHoi = "Biển số 1 có ý nghĩa gì?",
                    dapAn1 = "Đi thẳng hoặc rẽ trái trên cầu vượt",
                    dapAn2 = "Đi thẳng hoặc rẽ phải trên cầu vượt",
                    dapAn3 = "Báo hiệu cầu vượt liên thông",
                    dapAn4 = "",
                    dapAnDung = "3",
                    hinhAnh = R.drawable.bbdb83,
                    chuDe = "Biển báo đường bộ"
                ),

                LyThuyet(
                    cauHoi = "Biển nào báo hiệu “tuyến đường cầu vượt cắt qua”?",
                    dapAn1 = "Biển 1 và 2",
                    dapAn2 = "Biển 1 và 3",
                    dapAn3 = "Biển 2 và 3",
                    dapAn4 = "",
                    dapAnDung = "1",
                    hinhAnh = R.drawable.bbdb84,
                    chuDe = "Biển báo đường bộ"
                ),

                LyThuyet(
                    cauHoi = "Biển báo dưới đây có ý nghĩa gì?",
                    dapAn1 = "Chỉ dẫn khoảng cách đến làn đường cứu nạn (làn thoát xe khẩn cấp)",
                    dapAn2 = "Báo hiệu đường cụt phía trước",
                    dapAn3 = "Báo hiệu nút giao gần nhất phía trước",
                    dapAn4 = "Báo hiệu trạm dừng nghỉ phía trước",
                    dapAnDung = "1",
                    hinhAnh = R.drawable.bbdb85,
                    chuDe = "Biển báo đường bộ"
                ),

                LyThuyet(
                    cauHoi = "Tại đoạn đường có biển “làn đường dành riêng cho từng loại xe” các phương tiện có được phép chuyển sang làn khác khi đến gần nơi đường bộ giao nhau hay không?",
                    dapAn1 = "Được phép chuyển sang làn khác",
                    dapAn2 = "Không được phép chuyển sang làn khác, chỉ được đi trong làn quy định theo biển",
                    dapAn3 = "",
                    dapAn4 = "",
                    dapAnDung = "1",
                    hinhAnh = R.drawable.bbdb86,
                    chuDe = "Biển báo đường bộ"
                ),

                LyThuyet(
                    cauHoi = "Biển nào chỉ dẫn người lái xe đi được cả hai hướng?",
                    dapAn1 = "Biển 1",
                    dapAn2 = "Biển 2",
                    dapAn3 = "",
                    dapAn4 = "",
                    dapAnDung = "2",
                    hinhAnh = R.drawable.bbdb87,
                    chuDe = "Biển báo đường bộ"
                ),

                LyThuyet(
                    cauHoi = "Biển này có ý nghĩa như thế nào?",
                    dapAn1 = "Chỉ dẫn chướng ngại vật phía trước",
                    dapAn2 = "Chỉ dẫn hướng rẽ để nhắc người điều khiển phương tiện chuẩn bị đổi hướng đi khi sắp vào đường cong nguy hiểm",
                    dapAn3 = "",
                    dapAn4 = "",
                    dapAnDung = "2",
                    hinhAnh = R.drawable.bbdb88,
                    chuDe = "Biển báo đường bộ"
                ),

                LyThuyet(
                    cauHoi = "Vạch mũi tên chỉ hướng trên mặt đường nào dưới đây cho phép xe chỉ được đi thẳng và rẽ phải?",
                    dapAn1 = "Vạch 1",
                    dapAn2 = "Vạch 2 và 3",
                    dapAn3 = "Vạch 3",
                    dapAn4 = "Vạch 1 và 2",
                    dapAnDung = "3",
                    hinhAnh = R.drawable.bbdb89,
                    chuDe = "Biển báo đường bộ"
                ),

                LyThuyet(
                    cauHoi = "Vạch kẻ đường nào dưới đây là vạch phân chia hai chiều xe chạy, xe không được lấn làn, không được đè lên vạch?",
                    dapAn1 = "Vạch 1",
                    dapAn2 = "Vạch 2",
                    dapAn3 = "Vạch 3",
                    dapAn4 = "Cả 3 vạch",
                    dapAnDung = "2",
                    hinhAnh = R.drawable.bbdb90,
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
                LyThuyet(
                    cauHoi = "Theo hướng mũi tên, xe nào chấp hành đúng quy tắc giao thông?",
                    dapAn1 = "Xe khách, xe tải, xe mô tô.",
                    dapAn2 = "Xe tải, xe mô tô.",
                    dapAn3 = "Chỉ xe con.",
                    dapAn4 = "",
                    dapAnDung = "3",
                    hinhAnh = R.drawable.sh1,
                    chuDe = "Sa hình"
                ),
                LyThuyet(
                    cauHoi = "Theo hướng mũi tên, thứ tự các xe đi như thế nào là đúng quy tắc giao thông?",
                    dapAn1 = "Xe tải, xe khách, xe con, xe mô tô.",
                    dapAn2 = "Xe tải, xe mô tô, xe khách, xe con.",
                    dapAn3 = "Xe khách, xe tải, xe con, xe mô tô.",
                    dapAn4 = "Xe mô tô, xe khách, xe tải, xe con.",
                    dapAnDung = "2",
                    hinhAnh = R.drawable.sh2,
                    chuDe = "Sa hình"
                ),
                LyThuyet(
                    cauHoi = "Theo hướng mũi tên, thứ tự các xe đi như thế nào là đúng quy tắc giao thông?",
                    dapAn1 = "Xe tải, xe con, xe mô tô.",
                    dapAn2 = "Xe con, xe tải, xe mô tô.",
                    dapAn3 = "Xe mô tô, xe con, xe tải.",
                    dapAn4 = "Xe con, xe mô tô, xe tải.",
                    dapAnDung = "3",
                    hinhAnh = R.drawable.sh3,
                    chuDe = "Sa hình"
                ),
                LyThuyet(
                    cauHoi = "Trường hợp này xe nào được quyền đi trước?",
                    dapAn1 = "Xe mô tô.",
                    dapAn2 = "Xe con.",
                    dapAn3 = "",
                    dapAn4 = "",
                    dapAnDung = "2",
                    hinhAnh = R.drawable.sh4,
                    chuDe = "Sa hình"
                ),
                LyThuyet(
                    cauHoi = "Xe nào được quyền đi trước trong trường hợp này?",
                    dapAn1 = "Xe mô tô.",
                    dapAn2 = "Xe cứu thương đi làm nhiệm vụ cấp cứu.",
                    dapAn3 = "",
                    dapAn4 = "",
                    dapAnDung = "2",
                    hinhAnh = R.drawable.sh5,
                    chuDe = "Sa hình"
                ),
                LyThuyet(
                    cauHoi = "Theo tín hiệu đèn, xe nào được phép đi?",
                    dapAn1 = "Xe con và xe khách.",
                    dapAn2 = "Xe mô tô.",
                    dapAn3 = "",
                    dapAn4 = "",
                    dapAnDung = "1",
                    hinhAnh = R.drawable.sh6,
                    chuDe = "Sa hình"
                ),
                LyThuyet(
                    cauHoi = "Theo tín hiệu đèn, xe nào đi là đúng quy tắc giao thông?",
                    dapAn1 = "Xe khách, xe mô tô.",
                    dapAn2 = "Xe con, xe tải.",
                    dapAn3 = "Xe tải, xe mô tô.",
                    dapAn4 = "",
                    dapAnDung = "2",
                    hinhAnh = R.drawable.sh7,
                    chuDe = "Sa hình"
                ),
                LyThuyet(
                    cauHoi = "Các xe đi theo hướng mũi tên, những xe nào vi phạm quy tắc giao thông?",
                    dapAn1 = "Xe khách, xe tải, xe mô tô.",
                    dapAn2 = "Xe tải, xe con, xe mô tô.",
                    dapAn3 = "Xe khách, xe con, xe mô tô.",
                    dapAn4 = "",
                    dapAnDung = "1",
                    hinhAnh = R.drawable.sh8,
                    chuDe = "Sa hình"
                ),
                LyThuyet(
                    cauHoi = "Theo hướng mũi tên, thứ tự các xe đi như thế nào là đúng quy tắc giao thông?",
                    dapAn1 = "Xe khách, xe tải, xe mô tô, xe con.",
                    dapAn2 = "Xe con, xe khách, xe tải, xe mô tô.",
                    dapAn3 = "Xe mô tô, xe tải, xe khách, xe con.",
                    dapAn4 = "Xe mô tô, xe tải, xe con, xe khách.",
                    dapAnDung = "3",
                    hinhAnh = R.drawable.sh9,
                    chuDe = "Sa hình"
                ),
                LyThuyet(
                    cauHoi = "Trong trường hợp này xe nào đỗ vi phạm quy tắc giao thông?",
                    dapAn1 = "Xe tải.",
                    dapAn2 = "Xe con và mô tô.",
                    dapAn3 = "Cả ba xe.",
                    dapAn4 = "Xe con và xe tải.",
                    dapAnDung = "1",
                    hinhAnh = R.drawable.sh10,
                    chuDe = "Sa hình"
                ),
                LyThuyet(
                    cauHoi = "Theo hướng mũi tên, những hướng nào xe gắn máy được phép đi?",
                    dapAn1 = "Cả ba hướng.",
                    dapAn2 = "Chỉ hướng 1 và 3.",
                    dapAn3 = "Chỉ hướng 1.",
                    dapAn4 = "",
                    dapAnDung = "1",
                    hinhAnh = R.drawable.sh11,
                    chuDe = "Sa hình"
                ),
                LyThuyet(
                    cauHoi = "Xe nào đỗ vi phạm quy tắc giao thông?",
                    dapAn1 = "Cả hai xe.",
                    dapAn2 = "Không xe nào vi phạm.",
                    dapAn3 = "Chỉ xe mô tô vi phạm.",
                    dapAn4 = "Chỉ xe tải vi phạm.",
                    dapAnDung = "1",
                    hinhAnh = R.drawable.sh12,
                    chuDe = "Sa hình"
                ),
                LyThuyet(
                    cauHoi = "Xe nào đỗ vi phạm quy tắc giao thông?",
                    dapAn1 = "Chỉ xe mô tô.",
                    dapAn2 = "Chỉ xe tải.",
                    dapAn3 = "Cả ba xe.",
                    dapAn4 = "Chỉ xe mô tô và xe tải.",
                    dapAnDung = "3",
                    hinhAnh = R.drawable.sh13,
                    chuDe = "Sa hình"
                ),
                LyThuyet(
                    cauHoi = "Xe nào được quyền đi trước trong trường hợp này?",
                    dapAn1 = "Xe con.",
                    dapAn2 = "Xe mô tô.",
                    dapAn3 = "",
                    dapAn4 = "",
                    dapAnDung = "2",
                    hinhAnh = R.drawable.sh14,
                    chuDe = "Sa hình"
                ),
                LyThuyet(
                    cauHoi = "Theo hướng mũi tên, thứ tự các xe đi như thế nào là đúng quy tắc giao thông?",
                    dapAn1 = "Xe con (A), xe mô tô, xe con (B), xe đạp.",
                    dapAn2 = "Xe con (B), xe đạp, xe mô tô, xe con (A).",
                    dapAn3 = "Xe con (A), xe con (B), xe mô tô + xe đạp.",
                    dapAn4 = "Xe mô tô + xe đạp, xe con (A), xe con (B).",
                    dapAnDung = "4",
                    hinhAnh = R.drawable.sh15,
                    chuDe = "Sa hình"
                ),
                LyThuyet(
                    cauHoi = "Xe nào được quyền đi trước trong trường hợp này?",
                    dapAn1 = "Xe mô tô.",
                    dapAn2 = "Xe con.",
                    dapAn3 = "",
                    dapAn4 = "",
                    dapAnDung = "1",
                    hinhAnh = R.drawable.sh16,
                    chuDe = "Sa hình"
                ),
                LyThuyet(
                    cauHoi = "Xe nào vi phạm quy tắc giao thông?",
                    dapAn1 = "Xe khách.",
                    dapAn2 = "Xe mô tô.",
                    dapAn3 = "Xe con.",
                    dapAn4 = "Xe con và xe mô tô.",
                    dapAnDung = "3",
                    hinhAnh = R.drawable.sh17,
                    chuDe = "Sa hình"
                ),
                LyThuyet(
                    cauHoi = "Các xe đi như thế nào là đúng quy tắc giao thông?",
                    dapAn1 = "Các xe ở tay phải và trái được phép đi thẳng.",
                    dapAn2 = "Các xe ở mọi hướng được rẽ phải.",
                    dapAn3 = "Tất cả xe phải dừng lại trừ xe đã ở trong ngã tư.",
                    dapAn4 = "",
                    dapAnDung = "3",
                    hinhAnh = R.drawable.sh18,
                    chuDe = "Sa hình"
                ),
                LyThuyet(
                    cauHoi = "Theo hướng mũi tên, xe nào được phép đi?",
                    dapAn1 = "Xe mô tô, xe con.",
                    dapAn2 = "Xe con, xe tải.",
                    dapAn3 = "Xe mô tô, xe tải.",
                    dapAn4 = "Cả ba xe.",
                    dapAnDung = "3",
                    hinhAnh = R.drawable.sh19,
                    chuDe = "Sa hình"
                ),
                LyThuyet(
                    cauHoi = "Trong hình dưới đây, xe nào chấp hành đúng quy tắc giao thông?",
                    dapAn1 = "Chỉ xe khách, xe mô tô.",
                    dapAn2 = "Tất cả các loại xe.",
                    dapAn3 = "Không xe nào đúng.",
                    dapAn4 = "",
                    dapAnDung = "2",
                    hinhAnh = R.drawable.sh20,
                    chuDe = "Sa hình"
                ),
                LyThuyet(
                    cauHoi = "Theo hướng mũi tên, những hướng nào xe mô tô được phép đi?",
                    dapAn1 = "Cả ba hướng.",
                    dapAn2 = "Hướng 1 và 2.",
                    dapAn3 = "Hướng 1 và 3.",
                    dapAn4 = "Hướng 2 và 3.",
                    dapAnDung = "3",
                    hinhAnh = R.drawable.sh21,
                    chuDe = "Sa hình"
                ),
                LyThuyet(
                    cauHoi = "Thứ tự xe đi đúng là gì?",
                    dapAn1 = "Công an → Quân sự → Con + Mô tô.",
                    dapAn2 = "Quân sự → Công an → Con + Mô tô.",
                    dapAn3 = "Mô tô + Con → Quân sự → Công an.",
                    dapAn4 = "",
                    dapAnDung = "2",
                    hinhAnh = R.drawable.sh22,
                    chuDe = "Sa hình"
                ),
                LyThuyet(
                    cauHoi = "Xe nào vi phạm?",
                    dapAn1 = "Xe con (E), xe mô tô (C).",
                    dapAn2 = "Xe tải (A), xe mô tô (D).",
                    dapAn3 = "Xe khách (B), xe mô tô (C).",
                    dapAn4 = "Xe khách (B), xe mô tô (D).",
                    dapAnDung = "1",
                    hinhAnh = R.drawable.sh23,
                    chuDe = "Sa hình"
                ),
                LyThuyet(
                    cauHoi = "Xe nào vi phạm?",
                    dapAn1 = "Xe con (B), xe mô tô (C).",
                    dapAn2 = "Xe con (A), xe mô tô (C).",
                    dapAn3 = "Xe con (E), xe mô tô (D).",
                    dapAn4 = "Tất cả.",
                    dapAnDung = "3",
                    hinhAnh = R.drawable.sh24,
                    chuDe = "Sa hình"
                ),
                LyThuyet(
                    cauHoi = "Xe nào phải dừng?",
                    dapAn1 = "Xe khách, xe mô tô.",
                    dapAn2 = "Xe tải, xe mô tô.",
                    dapAn3 = "Xe con, xe tải.",
                    dapAn4 = "",
                    dapAnDung = "3",
                    hinhAnh = R.drawable.sh25,
                    chuDe = "Sa hình"
                ),
                LyThuyet(
                    cauHoi = "Được vượt không?",
                    dapAn1 = "Cho phép.",
                    dapAn2 = "Không được vượt.",
                    dapAn3 = "",
                    dapAn4 = "",
                    dapAnDung = "2",
                    hinhAnh = R.drawable.sh26,
                    chuDe = "Sa hình"
                ),
                LyThuyet(
                    cauHoi = "Xe nào vi phạm đèn?",
                    dapAn1 = "Xe mô tô.",
                    dapAn2 = "Xe ô tô con.",
                    dapAn3 = "Không xe nào.",
                    dapAn4 = "Cả hai.",
                    dapAnDung = "4",
                    hinhAnh = R.drawable.sh27,
                    chuDe = "Sa hình"
                ),
                LyThuyet(
                    cauHoi = "Xe nào vi phạm?",
                    dapAn1 = "Xe con.",
                    dapAn2 = "Xe tải.",
                    dapAn3 = "Xe con, xe tải.",
                    dapAn4 = "",
                    dapAnDung = "2",
                    hinhAnh = R.drawable.sh28,
                    chuDe = "Sa hình"
                ),
                LyThuyet(
                    cauHoi = "Xe nào vi phạm?",
                    dapAn1 = "Xe con, xe tải, xe khách.",
                    dapAn2 = "Xe tải, xe khách, xe mô tô.",
                    dapAn3 = "Xe khách, xe mô tô, xe con.",
                    dapAn4 = "Cả bốn xe.",
                    dapAnDung = "2",
                    hinhAnh = R.drawable.sh29,
                    chuDe = "Sa hình"
                ),
                LyThuyet(
                    cauHoi = "Xe nào đúng?",
                    dapAn1 = "Xe tải, xe mô tô.",
                    dapAn2 = "Xe khách, xe mô tô.",
                    dapAn3 = "Xe tải, xe con.",
                    dapAn4 = "Xe mô tô, xe con.",
                    dapAnDung = "2",
                    hinhAnh = R.drawable.sh30,
                    chuDe = "Sa hình"
                ),
                LyThuyet(
                    cauHoi = "Thứ tự đúng?",
                    dapAn1 = "Bạn → Mô tô → Con.",
                    dapAn2 = "Con → Bạn → Mô tô.",
                    dapAn3 = "Mô tô → Con → Bạn.",
                    dapAn4 = "",
                    dapAnDung = "3",
                    hinhAnh = R.drawable.sh31,
                    chuDe = "Sa hình"
                ),
                LyThuyet(
                    cauHoi = "Thứ tự đúng?",
                    dapAn1 = "Bạn → Mô tô → Con.",
                    dapAn2 = "Con → Bạn → Mô tô.",
                    dapAn3 = "Mô tô → Con → Bạn.",
                    dapAn4 = "",
                    dapAnDung = "2",
                    hinhAnh = R.drawable.sh32,
                    chuDe = "Sa hình"
                ),
                LyThuyet(
                    cauHoi = "Thứ tự đúng?",
                    dapAn1 = "Bạn → Mô tô → Đạp.",
                    dapAn2 = "Mô tô → Đạp → Bạn.",
                    dapAn3 = "Đạp → Mô tô → Bạn.",
                    dapAn4 = "",
                    dapAnDung = "3",
                    hinhAnh = R.drawable.sh33,
                    chuDe = "Sa hình"
                ),
                LyThuyet(
                    cauHoi = "Xe dừng đúng?",
                    dapAn1 = "Xe con.",
                    dapAn2 = "Xe mô tô.",
                    dapAn3 = "Cả 2 đúng.",
                    dapAn4 = "",
                    dapAnDung = "3",
                    hinhAnh = R.drawable.sh34,
                    chuDe = "Sa hình"
                ),
                LyThuyet(
                    cauHoi = "Đi thế nào an toàn?",
                    dapAn1 = "Vượt phải.",
                    dapAn2 = "Chờ container rẽ xong.",
                    dapAn3 = "Vượt trái.",
                    dapAn4 = "",
                    dapAnDung = "2",
                    hinhAnh = R.drawable.sh35,
                    chuDe = "Sa hình"
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

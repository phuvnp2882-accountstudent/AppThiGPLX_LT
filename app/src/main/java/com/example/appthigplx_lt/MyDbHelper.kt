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

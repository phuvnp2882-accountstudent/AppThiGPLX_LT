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
                    cauHoi = "Phần của đường bộ được sử dụng cho phương tiện giao thông đường bộ đi lại là gì?",
                    dapAn1 = "Phần mặt đường và lề đường.",
                    dapAn2 = "Phần đường xe chạy.",
                    dapAn3 = "Phần đường xe cơ giới.",
                    dapAn4 = "",
                    dapAnDung = "2",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Làn đường là gì?",
                    dapAn1 = "Là một phần của phần đường xe chạy được chia theo chiều dọc của đường, sử dụng cho xe chạy.",
                    dapAn2 = "Là một phần của phần đường xe chạy được chia theo chiều dọc của đường, có đủ chiều rộng cho xe chạy an toàn.",
                    dapAn3 = "Là đường cho xe ô tô chạy, dừng, đỗ an toàn.",
                    dapAn4 = "",
                    dapAnDung = "2",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Khổ giới hạn của đường bộ được hiểu như thế nào là đúng?",
                    dapAn1 = "Khổ giới hạn của đường bộ là khoảng trống có kích thước giới hạn về chiều rộng, chiều cao của đường bộ để các xe, bao gồm cả hàng hoá xếp trên xe đi qua được an toàn và được xác định theo quy chuẩn, tiêu chuẩn kỹ thuật của đường bộ.",
                    dapAn2 = "Là khoảng trống có kích thước giới hạn về chiều rộng của đường, cầu, bến phà, hầm trên đường bộ để các xe kể cả hàng hóa xếp trên xe đi qua được an toàn.",
                    dapAn3 = "Là khoảng trống có kích thước giới hạn về chiều cao của cầu, bến phà, hầm trên đường bộ để các xe đi qua được an toàn.",
                    dapAn4 = "",
                    dapAnDung = "1",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Dải phân cách được lắp đặt để làm gì?",
                    dapAn1 = "Để phân chia các làn đường dành cho xe cơ giới và xe thô sơ trên đường cao tốc.",
                    dapAn2 = "Để phân chia phần đường xe chạy thành hai chiều riêng biệt hoặc để phân chia phần đường dành cho xe cơ giới và xe thô sơ hoặc của nhiều loại xe khác nhau trên cùng một chiều đường.",
                    dapAn3 = "Để phân tách phần đường xe chạy và hành lang an toàn giao thông.",
                    dapAn4 = "",
                    dapAnDung = "2",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Vạch kẻ đường là gì?",
                    dapAn1 = "Là báo hiệu đường bộ để hỗ trợ cảnh báo nguy hiểm cho người tham gia giao thông đường bộ.",
                    dapAn2 = "Là vạch chỉ sự phân chia làn đường, vị trí hoặc hướng đi, vị trí dừng lại.",
                    dapAn3 = "Là báo hiệu cho người tham gia giao thông đường bộ về các thông tin của đường bộ.",
                    dapAn4 = "Cả ba ý trên.",
                    dapAnDung = "4",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Người điều khiển phương tiện tham gia giao thông đường bộ được hiểu như thế nào là đúng?",
                    dapAn1 = "Là người điều khiển xe cơ giới, người điều khiển xe thô sơ, người điều khiển xe máy chuyên dùng.",
                    dapAn2 = "Là người được giao nhiệm vụ hướng dẫn giao thông trên đường bộ.",
                    dapAn3 = "Cả hai ý trên.",
                    dapAn4 = "",
                    dapAnDung = "1",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Người lái xe được hiểu như thế nào là đúng?",
                    dapAn1 = "Là người điều khiển xe cơ giới.",
                    dapAn2 = "Là người điều khiển xe thô sơ.",
                    dapAn3 = "Là người điều khiển xe máy chuyên dùng.",
                    dapAn4 = "",
                    dapAnDung = "1",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Trong nhóm các phương tiện giao thông đường bộ dưới đây, nhóm phương tiện nào là xe cơ giới?",
                    dapAn1 = "Xe ô tô; máy kéo; xe mô tô hai bánh; xe mô tô ba bánh; xe gắn máy; xe cơ giới dùng cho người khuyết tật và xe máy chuyên dùng; xe đạp, xe đạp máy, xe đạp điện.",
                    dapAn2 = "Xe ô tô; rơ moóc được kéo bởi xe ô tô; sơ mi rơ moóc được kéo bởi ô tô đầu kéo; xe chở người bốn bánh có gắn động cơ; xe chở hàng bốn bánh có gắn động cơ; xe mô tô, xe gắn máy và các loại xe tương tự.",
                    dapAn3 = "",
                    dapAn4 = "",
                    dapAnDung = "2",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Trong nhóm các phương tiện giao thông đường bộ dưới đây, nhóm phương tiện nào là xe thô sơ?",
                    dapAn1 = "Xe đạp, xe đạp máy, xe đạp điện; xe xích lô; xe lăn dùng cho người khuyết tật; xe vật nuôi kéo và các loại xe tương tự.",
                    dapAn2 = "Xe đạp (kể cả xe đạp máy, xe đạp điện), xe gắn máy, xe cơ giới dùng cho người khuyết tật và xe máy chuyên dùng.",
                    dapAn3 = "Xe ô tô, máy kéo, rơ moóc hoặc sơ mi rơ moóc được kéo bởi xe ô tô, máy kéo.",
                    dapAn4 = "",
                    dapAnDung = "1",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Phương tiện giao thông đường bộ gồm những loại nào?",
                    dapAn1 = "Phương tiện giao thông cơ giới đường bộ.",
                    dapAn2 = "Phương tiện giao thông thô sơ đường bộ, xe máy chuyên dùng và các loại xe tương tự.",
                    dapAn3 = "Cả hai ý trên.",
                    dapAn4 = "",
                    dapAnDung = "3",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Người tham gia giao thông đường bộ gồm những đối tượng nào?",
                    dapAn1 = "Người điều khiển, người được chở trên phương tiện tham gia giao thông đường bộ.",
                    dapAn2 = "Người điều khiển, dẫn dắt vật nuôi trên đường bộ; người đi bộ trên đường bộ.",
                    dapAn3 = "Cả hai ý trên.",
                    dapAn4 = "",
                    dapAnDung = "3",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Người điều khiển phương tiện tham gia giao thông đường bộ gồm những đối tượng nào dưới đây?",
                    dapAn1 = "Người điều khiển xe cơ giới, người điều khiển xe thô sơ.",
                    dapAn2 = "Người điều khiển xe máy chuyên dùng.",
                    dapAn3 = "Cả hai ý trên.",
                    dapAn4 = "",
                    dapAnDung = "3",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Người điều khiển giao thông đường bộ được hiểu như thế nào là đúng?",
                    dapAn1 = "Là người điều khiển phương tiện tham gia giao thông đường bộ.",
                    dapAn2 = "Là Cảnh sát giao thông và người được giao nhiệm vụ hướng dẫn giao thông trên đường bộ.",
                    dapAn3 = "Là người tham gia giao thông đường bộ.",
                    dapAn4 = "",
                    dapAnDung = "2",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Hành vi nào dưới đây bị nghiêm cấm? (Câu liệt)",
                    dapAn1 = "Sử dụng xe đạp đi trên các tuyến quốc lộ.",
                    dapAn2 = "Rải vật sắc nhọn, đổ chất gây trơn trượt trên đường bộ.",
                    dapAn3 = "Cả hai ý trên.",
                    dapAn4 = "",
                    dapAnDung = "2",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Hành vi đưa xe cơ giới, xe máy chuyên dùng tham gia giao thông đường bộ nào dưới đây bị cấm? (Câu liệt)",
                    dapAn1 = "Không có chứng nhận kiểm định an toàn kỹ thuật và bảo vệ môi trường.",
                    dapAn2 = "Hết niên hạn sử dụng.",
                    dapAn3 = "Cả hai ý trên.",
                    dapAn4 = "",
                    dapAnDung = "3",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Tổ chức đua xe được phép thực hiện khi nào? (Câu liệt)",
                    dapAn1 = "Trên đường phố không có người qua lại.",
                    dapAn2 = "Được người dân ủng hộ.",
                    dapAn3 = "Được cơ quan có thẩm quyền cấp phép.",
                    dapAn4 = "",
                    dapAnDung = "3",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Hành vi đua xe trái phép bị xử lý như thế nào? (Câu liệt)",
                    dapAn1 = "Chỉ bị nhắc nhở.",
                    dapAn2 = "Tùy theo mức độ của hành vi vi phạm có thể bị xử lý hành chính hoặc xử lý hình sự.",
                    dapAn3 = "",
                    dapAn4 = "",
                    dapAnDung = "2",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Người điều khiển phương tiện tham gia giao thông đường bộ mà trong máu hoặc hơi thở có nồng độ cồn có bị nghiêm cấm không? (Câu liệt)",
                    dapAn1 = "Bị nghiêm cấm.",
                    dapAn2 = "Không bị nghiêm cấm.",
                    dapAn3 = "Không bị nghiêm cấm, nếu nồng độ cồn trong máu ở mức nhẹ, có thể điều khiển phương tiện tham gia giao thông.",
                    dapAn4 = "",
                    dapAnDung = "1",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Theo Luật Phòng chống tác hại của rượu, bia, đối tượng nào dưới đây bị cấm sử dụng rượu, bia khi tham gia giao thông? (Câu liệt)",
                    dapAn1 = "Người điều khiển xe ô tô, xe mô tô, xe đạp, xe gắn máy.",
                    dapAn2 = "Người được chở trên xe cơ giới.",
                    dapAn3 = "Cả hai ý trên.",
                    dapAn4 = "",
                    dapAnDung = "1",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Hành vi giao xe ô tô, mô tô cho người nào sau đây tham gia giao thông đường bộ bị nghiêm cấm? (Câu liệt)",
                    dapAn1 = "Người chưa đủ tuổi theo quy định.",
                    dapAn2 = "Người không có giấy phép lái xe.",
                    dapAn3 = "Người có giấy phép lái xe nhưng đã bị trừ hết 12 điểm.",
                    dapAn4 = "Cả ba ý trên.",
                    dapAnDung = "4",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Hành vi nào sau đây bị nghiêm cấm? (Câu liệt)",
                    dapAn1 = "Điều khiển xe cơ giới lạng lách, đánh võng, rú ga liên tục khi tham gia giao thông trên đường.",
                    dapAn2 = "Xúc phạm, đe dọa, cản trở, chống đối hoặc không chấp hành hiệu lệnh, hướng dẫn, yêu cầu kiểm tra, kiểm soát của người thi hành công vụ về bảo đảm trật tự, an toàn giao thông đường bộ.",
                    dapAn3 = "Cả hai ý trên.",
                    dapAn4 = "",
                    dapAnDung = "3",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Các hành vi nào sau đây bị cấm đối với phương tiện tham gia giao thông đường bộ?",
                    dapAn1 = "Cải tạo xe ô tô loại khác thành xe ô tô chở người phục vụ mục đích quốc phòng, an ninh.",
                    dapAn2 = "Cải tạo trái phép; cố ý can thiệp làm sai lệch chỉ số trên đồng hồ báo quãng đường đã chạy của xe ô tô; cắt, hàn, tẩy xóa, đục sửa, đóng lại trái phép số khung, số động cơ của xe cơ giới, xe máy chuyên dùng.",
                    dapAn3 = "",
                    dapAn4 = "",
                    dapAnDung = "2",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Hành vi nào sau đây bị cấm? (Câu liệt)",
                    dapAn1 = "Lắp đặt, sử dụng thiết bị âm thanh, ánh sáng trên xe cơ giới, xe máy chuyên dùng gây mất trật tự, an toàn giao thông đường bộ.",
                    dapAn2 = "Cản trở người, phương tiện tham gia giao thông trên đường bộ; ném gạch, đất, đá, cát hoặc vật thể khác vào người, phương tiện đang tham gia giao thông trên đường bộ.",
                    dapAn3 = "Cả hai ý trên.",
                    dapAn4 = "",
                    dapAnDung = "3",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Việc sản xuất, sử dụng, mua, bán trái phép biển số xe có bị nghiêm cấm hay không?",
                    dapAn1 = "Không bị nghiêm cấm.",
                    dapAn2 = "Bị nghiêm cấm.",
                    dapAn3 = "Bị nghiêm cấm tuỳ trường hợp.",
                    dapAn4 = "",
                    dapAnDung = "2",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Khi điều khiển phương tiện tham gia giao thông, những hành vi nào dưới đây bị nghiêm cấm?",
                    dapAn1 = "Thay đổi tốc độ của xe nhiều lần.",
                    dapAn2 = "Điều khiển phương tiện sau 23 giờ trong ngày.",
                    dapAn3 = "Lạng lách, đánh võng, rú ga liên tục.",
                    dapAn4 = "",
                    dapAnDung = "3",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Có bao nhiêu nhóm biển báo hiệu đường bộ?",
                    dapAn1 = "Ba nhóm: Biển báo cấm, biển báo nguy hiểm và biển hiệu lệnh.",
                    dapAn2 = "Bốn nhóm: Biển báo cấm, biển báo nguy hiểm, biển hiệu lệnh và biển phụ.",
                    dapAn3 = "Năm nhóm: Biển báo cấm, biển báo nguy hiểm, biển hiệu lệnh, biển chỉ dẫn, biển phụ.",
                    dapAn4 = "",
                    dapAnDung = "3",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Tại nơi có vạch kẻ đường hoặc tại nơi mà người đi bộ, xe lăn của người khuyết tật đang qua đường, người điều khiển phương tiện tham gia giao thông phải thực hiện như thế nào?",
                    dapAn1 = "Giảm tốc độ và nhường đường cho người đi bộ, xe lăn của người khuyết tật qua đường đảm bảo an toàn.",
                    dapAn2 = "Quan sát, giảm tốc độ hoặc dừng lại để bảo đảm an toàn cho người đi bộ, xe lăn của người khuyết tật qua đường.",
                    dapAn3 = "Quan sát, tăng tốc độ và điều khiển phương tiện nhanh chóng đi qua.",
                    dapAn4 = "",
                    dapAnDung = "2",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Người điều khiển xe mô tô phải quan sát, giảm tốc độ hoặc dừng lại để bảo đảm an toàn trong các trường hợp nào dưới đây?",
                    dapAn1 = "Đường hẹp, đường vòng, đường quanh co, đường đèo, dốc.",
                    dapAn2 = "Nơi cầu, cống hẹp, đập tràn, đường ngầm, hầm chui, hầm đường bộ.",
                    dapAn3 = "Trời mưa, gió, sương, khói, bụi, mặt đường trơn trượt, lầy lội, có nhiều đất đá, vật liệu rơi vãi ảnh hưởng đến an toàn giao thông đường bộ.",
                    dapAn4 = "Cả ba ý trên.",
                    dapAnDung = "4",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Khi gặp hiệu lệnh điều khiển của Cảnh sát giao thông như hình dưới đây thì người tham gia giao thông đường bộ phải đi như thế nào là đúng quy tắc giao thông?",
                    dapAn1 = "Người tham gia giao thông đường bộ ở các hướng phải dừng lại.",
                    dapAn2 = "Người tham gia giao thông đường bộ ở các hướng được đi theo chiều gậy chỉ của Cảnh sát giao thông.",
                    dapAn3 = "Người tham gia giao thông đường bộ ở phía trước và phía sau người điều khiển được đi tất cả các hướng; người tham gia giao thông đường bộ ở phía bên phải và phía bên trái người điều khiển phải dừng lại.",
                    dapAn4 = "Người tham gia giao thông đường bộ ở phía trước và phía sau người điều khiển phải dừng lại; người tham gia giao thông đường bộ ở phía bên phải và phía bên trái người điều khiển được đi tất cả các hướng.",
                    dapAnDung = "4",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Khi gặp hiệu lệnh điều khiển của Cảnh sát giao thông như hình dưới đây thì người tham gia giao thông đường bộ phải đi như thế nào là đúng quy tắc giao thông?",
                    dapAn1 = "Người tham gia giao thông đường bộ ở phía sau Cảnh sát giao thông được đi, các hướng khác phải dừng lại.",
                    dapAn2 = "Người tham gia giao thông đường bộ được rẽ phải theo chiều mũi tên màu xanh ở bục Cảnh sát giao thông.",
                    dapAn3 = "Người tham gia giao thông đường bộ ở tất cả các hướng phải dừng lại, trừ các xe đã ở trong khu vực giao nhau.",
                    dapAn4 = "Người tham gia giao thông đường bộ ở phía trước Cảnh sát giao thông phải dừng lại, các hướng khác được đi.",
                    dapAnDung = "3",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Khi hiệu lệnh của người điều khiển giao thông trái với tín hiệu đèn giao thông hoặc biển báo hiệu đường bộ thì người tham gia giao thông đường bộ phải chấp hành báo hiệu đường bộ nào dưới đây?",
                    dapAn1 = "Theo hiệu lệnh của người điều khiển giao thông.",
                    dapAn2 = "Theo tín hiệu đèn giao thông.",
                    dapAn3 = "Theo biển báo hiệu đường bộ.",
                    dapAn4 = "",
                    dapAnDung = "1",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Khi ở một vị trí vừa có biển báo hiệu đặt cố định vừa có biển báo hiệu tạm thời mà hai biển có ý nghĩa khác nhau, người tham gia giao thông đường bộ phải chấp hành hiệu lệnh của biển báo hiệu nào?",
                    dapAn1 = "Biển báo hiệu đặt cố định.",
                    dapAn2 = "Biển báo hiệu tạm thời.",
                    dapAn3 = "Theo quyết định của người tham gia giao thông nhưng phải bảo đảm an toàn.",
                    dapAn4 = "",
                    dapAnDung = "2",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Tại nơi đường giao nhau, khi đèn điều khiển giao thông có tín hiệu màu vàng, người điều khiển phương tiện tham gia giao thông phải chấp hành như thế nào là đúng quy tắc giao thông?",
                    dapAn1 = "Dừng lại trước vạch dừng; trường hợp đang đi trên vạch dừng hoặc đã đi qua vạch dừng mà tín hiệu đèn màu vàng thì được đi tiếp; trường hợp tín hiệu đèn màu vàng nhấp nháy, người điều khiển phương tiện tham gia giao thông đường bộ được đi nhưng phải quan sát, giảm tốc độ hoặc dừng lại nhường đường cho người đi bộ, xe lăn của người khuyết tật qua đường hoặc các phương tiện khác.",
                    dapAn2 = "Tăng tốc độ nhanh chóng vượt qua nút giao.",
                    dapAn3 = "Quan sát, giảm tốc độ, từ từ vượt qua nút giao.",
                    dapAn4 = "",
                    dapAnDung = "1",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Người lái xe trên đường cần chấp hành quy định về tốc độ tối đa như thế nào?",
                    dapAn1 = "Chỉ lớn hơn tốc độ tối đa cho phép khi đường vắng.",
                    dapAn2 = "Chỉ lớn hơn tốc độ tối đa cho phép khi vào ban đêm.",
                    dapAn3 = "Không vượt quá tốc độ tối đa cho phép.",
                    dapAn4 = "",
                    dapAnDung = "3",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Phương tiện tham gia giao thông đường bộ di chuyển với tốc độ thấp hơn phải đi như thế nào?",
                    dapAn1 = "Đi về bên trái theo chiều đi của mình.",
                    dapAn2 = "Đi về bên phải theo chiều đi của mình.",
                    dapAn3 = "Đi ở bất cứ bên nào nhưng phải bấm đèn cảnh báo nguy hiểm để báo hiệu cho các phương tiện khác.",
                    dapAn4 = "",
                    dapAnDung = "2",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Trên một chiều đường có vạch kẻ phân làn đường, người lái xe cơ giới, xe máy chuyên dùng phải điều khiển xe đi trên làn đường nào?",
                    dapAn1 = "Đi trên làn đường bên phải trong cùng.",
                    dapAn2 = "Đi trên làn đường bên trái.",
                    dapAn3 = "Đi ở bất cứ làn nào nhưng phải bảo đảm tốc độ cho phép.",
                    dapAn4 = "",
                    dapAnDung = "2",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Người lái xe phải giảm tốc độ, có tín hiệu rẽ phải và đi sát về bên phải của phần đường xe chạy trong các trường hợp nào dưới đây?",
                    dapAn1 = "Khi xe chạy phía trước có tín hiệu vượt xe khác.",
                    dapAn2 = "Khi phía trước có xe chạy ngược chiều.",
                    dapAn3 = "Khi xe sau xin vượt nếu đủ điều kiện an toàn.",
                    dapAn4 = "Khi xe sau có tín hiệu vượt bên phải.",
                    dapAnDung = "3",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Vượt xe là gì?",
                    dapAn1 = "Là tình huống giao thông trên đường mà mỗi chiều đường xe chạy chỉ có một làn đường dành cho xe cơ giới, xe đi phía sau di chuyển sang bên trái để di chuyển lên trước xe phía trước.",
                    dapAn2 = "Là tình huống giao thông trên đường có từ hai làn đường dành cho xe cơ giới cùng chiều trở lên được phân biệt bằng vạch kẻ đường, xe đi phía sau di chuyển lên trước xe phía trước theo quy tắc sử dụng làn đường.",
                    dapAn3 = "",
                    dapAn4 = "",
                    dapAnDung = "1",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Người lái xe được phép vượt xe trên cầu hẹp có một làn đường, đường cong có tầm nhìn bị hạn chế hay không? (Câu liệt)",
                    dapAn1 = "Được phép vượt khi đường vắng.",
                    dapAn2 = "Không được phép vượt.",
                    dapAn3 = "Được phép vượt khi có việc gấp.",
                    dapAn4 = "",
                    dapAnDung = "2",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Muốn vượt xe phía trước, người lái xe mô tô phải có tín hiệu như thế nào dưới đây để bảo đảm an toàn? (Câu liệt)",
                    dapAn1 = "Bấm còi liên tục để xe phía trước biết xe mình xin vượt.",
                    dapAn2 = "Rú ga liên tục để xe phía trước biết xe mình xin vượt.",
                    dapAn3 = "Báo hiệu nhấp nháy bằng đèn chiếu sáng phía trước hoặc còi.",
                    dapAn4 = "",
                    dapAnDung = "3",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Khi lái xe trong khu đông dân cư, khu vực cơ sở khám bệnh, chữa bệnh trừ các khu vực có biển cấm sử dụng còi, người lái xe được sử dụng còi trong thời gian nào?",
                    dapAn1 = "Từ 22 giờ ngày hôm trước đến 05 giờ ngày hôm sau.",
                    dapAn2 = "Từ 05 giờ đến 22 giờ.",
                    dapAn3 = "Từ 23 giờ ngày hôm trước đến 05 giờ sáng hôm sau.",
                    dapAn4 = "",
                    dapAnDung = "2",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Người lái xe sử dụng đèn như thế nào khi đi trên các đoạn đường qua khu đông dân cư có hệ thống chiếu sáng đang hoạt động?",
                    dapAn1 = "Chỉ bật đèn chiếu xa (đèn pha).",
                    dapAn2 = "Bật đèn chiếu xa (đèn pha) khi đường vắng, bật đèn chiếu gần (đèn cốt) khi có xe đi ngược chiều.",
                    dapAn3 = "Chỉ bật đèn chiếu gần (đèn cốt).",
                    dapAn4 = "",
                    dapAnDung = "3",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Khi điều khiển phương tiện tham gia giao thông, hành vi nào sau đây bị cấm? (Câu liệt)",
                    dapAn1 = "Dùng tay cầm và sử dụng điện thoại hoặc thiết bị điện tử khác.",
                    dapAn2 = "Chỉ được chở người trên thùng xe ô tô chở hàng trong trường hợp chở người đi làm nhiệm vụ cứu nạn, cứu hộ, phòng, chống thiên tai, dịch bệnh hoặc thực hiện nhiệm vụ khẩn cấp.",
                    dapAn3 = "",
                    dapAn4 = "",
                    dapAnDung = "1",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Người lái xe không được vượt xe khác khi gặp trường hợp nào dưới đây? (Câu liệt)",
                    dapAn1 = "Trên cầu hẹp có một làn đường; nơi đường giao nhau, đường bộ giao nhau cùng mức với đường sắt; khi gặp xe ưu tiên.",
                    dapAn2 = "Trên cầu có từ 02 làn xe trở lên.",
                    dapAn3 = "Trên đường có 02 làn đường được phân chia làn bằng vạch kẻ nét đứt.",
                    dapAn4 = "",
                    dapAnDung = "1",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Nơi nào cấm quay đầu xe?",
                    dapAn1 = "Ở phần đường dành cho người đi bộ qua đường, trên cầu, đầu cầu, gầm cầu vượt, ngầm.",
                    dapAn2 = "Tại nơi đường bộ giao nhau cùng mức với đường sắt, đường hẹp, đường dốc, đoạn đường cong tầm nhìn bị che khuất, trên đường cao tốc, trong hầm đường bộ, trên đường một chiều.",
                    dapAn3 = "Cả hai ý trên.",
                    dapAn4 = "",
                    dapAnDung = "3",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Trước khi cho xe chuyển hướng, người lái xe phải làm gì để bảo đảm an toàn giao thông?",
                    dapAn1 = "Phải quan sát, bảo đảm khoảng cách an toàn với xe phía sau.",
                    dapAn2 = "Giảm tốc độ và có tín hiệu báo hướng rẽ.",
                    dapAn3 = "Chuyển dần sang làn gần nhất với hướng rẽ. Khi bảo đảm an toàn, không gây trở ngại cho người và phương tiện khác mới được chuyển hướng.",
                    dapAn4 = "Cả ba ý trên.",
                    dapAnDung = "4",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Khi chuyển làn đường, người lái xe phải bật đèn tín hiệu báo rẽ như thế nào là đúng quy tắc giao thông?",
                    dapAn1 = "Khi bắt đầu chuyển làn đường.",
                    dapAn2 = "Trước khi thay đổi làn đường.",
                    dapAn3 = "Sau khi thay đổi làn đường.",
                    dapAn4 = "",
                    dapAnDung = "2",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Người điều khiển phương tiện tham gia giao thông không được dừng xe, đỗ xe ở những vị trí nào sau đây?",
                    dapAn1 = "Trên miệng cống thoát nước, miệng hầm của đường điện thoại, điện cao thế, chỗ dành riêng cho xe chữa cháy lấy nước.",
                    dapAn2 = "Trong phạm vi an toàn của đường sắt.",
                    dapAn3 = "Cả hai ý trên.",
                    dapAn4 = "",
                    dapAnDung = "3",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Người điều khiển xe mô tô hai bánh, xe mô tô ba bánh, xe gắn máy có được phép sử dụng xe để kéo hoặc đẩy các phương tiện khác khi tham gia giao thông không? (Câu liệt)",
                    dapAn1 = "Được phép.",
                    dapAn2 = "Nếu phương tiện được kéo, đẩy có khối lượng nhỏ hơn phương tiện của mình.",
                    dapAn3 = "Tùy trường hợp.",
                    dapAn4 = "Không được phép.",
                    dapAnDung = "4",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Khi điều khiển xe mô tô hai bánh, xe mô tô ba bánh, xe gắn máy, những hành vi nào sau đây không được phép? (Câu liệt)",
                    dapAn1 = "Buông cả hai tay; đứng, nằm trên xe điều khiển xe; sử dụng chân chống hoặc vật khác quệt xuống đường khi xe đang chạy.",
                    dapAn2 = "Chở tối đa hai người phía sau khi chở người bệnh đi cấp cứu, áp giải người có hành vi vi phạm pháp luật, trẻ em dưới 12 tuổi và người già yếu hoặc người khuyết tật.",
                    dapAn3 = "",
                    dapAn4 = "",
                    dapAnDung = "1",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Khi điều khiển xe mô tô hai bánh, xe mô tô ba bánh, xe gắn máy, những hành vi nào sau đây không được phép? (Câu liệt)",
                    dapAn1 = "Buông cả hai tay; sử dụng xe để kéo, đẩy xe khác, vật khác; sử dụng chân chống hoặc vật khác quệt xuống đường khi xe đang chạy.",
                    dapAn2 = "Sử dụng xe để chở người hoặc hàng hóa; để chân chạm xuống đất khi khởi hành.",
                    dapAn3 = "Đội mũ bảo hiểm; chạy xe đúng tốc độ quy định và chấp hành đúng quy tắc giao thông đường bộ.",
                    dapAn4 = "Chở người ngồi sau dưới 16 tuổi.",
                    dapAnDung = "1",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Người được chở trên xe mô tô hai bánh, xe mô tô ba bánh, xe gắn máy khi tham gia giao thông đường bộ không được thực hiện hành vi nào sau đây?",
                    dapAn1 = "Mang, vác vật cồng kềnh.",
                    dapAn2 = "Bám, kéo hoặc đẩy các phương tiện khác.",
                    dapAn3 = "Dùng tay cầm điện thoại hoặc các thiết bị điện tử khác.",
                    dapAn4 = "Ý 1 và ý 2.",
                    dapAnDung = "4",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Người được chở trên xe mô tô hai bánh, xe mô tô ba bánh, xe gắn máy khi tham gia giao thông đường bộ có được bám, kéo hoặc đẩy các phương tiện khác không?",
                    dapAn1 = "Được phép.",
                    dapAn2 = "Được bám trong trường hợp phương tiện của mình bị hỏng.",
                    dapAn3 = "Được kéo, đẩy trong trường hợp phương tiện khác bị hỏng.",
                    dapAn4 = "Không được phép.",
                    dapAnDung = "4",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Người lái xe, người được chở trên xe mô tô hai bánh, xe mô tô ba bánh, xe gắn máy phải thực hiện quy định nào dưới đây? (Câu liệt)",
                    dapAn1 = "Đội mũ bảo hiểm theo đúng quy chuẩn kỹ thuật quốc gia và cài quai đúng quy cách.",
                    dapAn2 = "Người lái xe phải đội mũ bảo hiểm, người được chở trên xe không nhất thiết phải đội mũ bảo hiểm.",
                    dapAn3 = "Phải đội mũ bảo hiểm nhưng không nhất thiết phải cài quai.",
                    dapAn4 = "",
                    dapAnDung = "1",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Người lái xe mô tô hai bánh, xe gắn máy được phép chở tối đa hai người trong những trường hợp nào?",
                    dapAn1 = "Chở người bệnh đi cấp cứu; áp giải người có hành vi vi phạm pháp luật; trẻ em dưới 12 tuổi; người già yếu hoặc người khuyết tật.",
                    dapAn2 = "Người đã uống rượu, bia; người trong cơ thể có chất ma tuý.",
                    dapAn3 = "Cả hai ý trên.",
                    dapAn4 = "",
                    dapAnDung = "1",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Người lái xe mô tô hai bánh, xe mô tô ba bánh, xe gắn máy không được thực hiện các hành vi nào dưới đây? (Câu liệt)",
                    dapAn1 = "Đi xe dàn hàng ngang; buông cả hai tay.",
                    dapAn2 = "Sử dụng xe để kéo, đẩy xe khác, vật khác, dẫn dắt vật nuôi, mang, vác và chở vật cồng kềnh; chở người đứng trên xe, giá đèo hàng hoặc ngồi trên tay lái; xếp hàng hóa trên xe quá giới hạn quy định.",
                    dapAn3 = "Ngồi về một bên điều khiển xe; đứng, nằm trên xe điều khiển xe; thay người lái xe khi xe đang chạy; quay người về phía sau để điều khiển xe hoặc bịt mắt điều khiển xe; sử dụng chân chống hoặc vật khác quệt xuống đường khi xe đang chạy.",
                    dapAn4 = "Cả ba ý trên.",
                    dapAnDung = "4",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Người lái xe mô tô hai bánh, xe mô tô ba bánh, xe gắn máy không được thực hiện các hành vi nào sau đây? (Câu liệt)",
                    dapAn1 = "Đi xe dàn hàng ngang; đi xe vào phần đường dành cho người đi bộ và phương tiện khác.",
                    dapAn2 = "Sử dụng ô, thiết bị âm thanh, trừ thiết bị trợ thính.",
                    dapAn3 = "Cả hai ý trên.",
                    dapAn4 = "",
                    dapAnDung = "3",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Người lái xe mô tô hai bánh, xe mô tô ba bánh, xe gắn máy không được thực hiện hành vi nào sau đây? (Câu liệt)",
                    dapAn1 = "Đi trên phần đường, làn đường quy định, chấp hành hiệu lệnh của người điều khiển giao thông, đèn tín hiệu giao thông.",
                    dapAn2 = "Đi xe dàn hàng ngang, đi xe vào phần đường dành cho người đi bộ.",
                    dapAn3 = "Cả hai ý trên.",
                    dapAn4 = "",
                    dapAnDung = "2",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Người được chở trên xe mô tô hai bánh, xe mô tô ba bánh, xe gắn máy khi tham gia giao thông đường bộ có được sử dụng ô khi trời mưa hay không?",
                    dapAn1 = "Được sử dụng.",
                    dapAn2 = "Chỉ người ngồi sau được sử dụng.",
                    dapAn3 = "Không được sử dụng.",
                    dapAn4 = "Được sử dụng nếu không có áo mưa.",
                    dapAnDung = "3",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Người được chở trên xe mô tô có được kéo theo người đang điều khiển xe đạp hay không?",
                    dapAn1 = "Chỉ được phép nếu cả hai đội mũ bảo hiểm.",
                    dapAn2 = "Không được phép.",
                    dapAn3 = "Chỉ được thực hiện trên đường vắng.",
                    dapAn4 = "",
                    dapAnDung = "2",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Trường hợp người được chở trên xe mô tô, xe gắn máy, các loại xe tương tự xe mô tô và các loại xe tương tự xe gắn máy không đội 'mũ bảo hiểm cho người đi mô tô, xe máy' hoặc không cài quai đúng quy cách thì việc xử phạt vi phạm hành chính được quy định như thế nào?",
                    dapAn1 = "Không bị xử phạt chỉ bị nhắc nhở.",
                    dapAn2 = "Người được chở không bị xử phạt, chỉ xử phạt người điều khiển xe mô tô, xe gắn máy.",
                    dapAn3 = "Người được chở bị xử phạt, không xử phạt người điều khiển xe mô tô, xe gắn máy.",
                    dapAn4 = "Xử phạt cả người điều khiển và người được chở trên xe mô tô, xe gắn máy.",
                    dapAnDung = "4",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Trong các trường hợp dưới đây, để bảo đảm an toàn khi tham gia giao thông, người lái xe mô tô cần thực hiện như thế nào?",
                    dapAn1 = "Phải đội mũ bảo hiểm theo đúng quy chuẩn kỹ thuật quốc gia và cài quai đúng quy cách, không sử dụng ô, điện thoại di động, thiết bị âm thanh (trừ thiết bị trợ thính).",
                    dapAn2 = "Phải đội mũ bảo hiểm khi trời mưa gió hoặc trời quá nắng; có thể sử dụng ô, điện thoại di động, thiết bị âm thanh nhưng phải bảo đảm an toàn.",
                    dapAn3 = "Phải đội mũ bảo hiểm khi cảm thấy mất an toàn giao thông hoặc khi chuẩn bị di chuyển quãng đường xa.",
                    dapAn4 = "",
                    dapAnDung = "1",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Thứ tự xuống phà như thế nào là đúng quy tắc giao thông?",
                    dapAn1 = "Xe thô sơ, người đi bộ xuống trước, xe cơ giới, xe máy chuyên dùng xuống sau.",
                    dapAn2 = "Xe cơ giới, xe máy chuyên dùng xuống trước, xe thô sơ, người đi bộ xuống sau.",
                    dapAn3 = "Xe cơ giới, xe thô sơ xuống trước, xe máy chuyên dùng, người đi bộ xuống sau.",
                    dapAn4 = "",
                    dapAnDung = "2",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Khi lái xe trong đô thị và khu đông dân cư từ 22 giờ đến 05 giờ, nếu cần vượt một xe khác, người lái xe phải báo hiệu như thế nào là đúng quy tắc giao thông?",
                    dapAn1 = "Chỉ được báo hiệu bằng còi.",
                    dapAn2 = "Phải báo hiệu bằng cả còi và đèn.",
                    dapAn3 = "Chỉ được báo hiệu bằng đèn.",
                    dapAn4 = "",
                    dapAnDung = "3",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Khi điều khiển xe chạy trên đường, biết có xe sau xin vượt, nếu đủ điều kiện an toàn người điều khiển phương tiện phải làm gì?",
                    dapAn1 = "Tăng tốc độ và ra hiệu cho xe sau vượt, không được gây trở ngại cho xe xin vượt.",
                    dapAn2 = "Giảm tốc độ, có tín hiệu rẽ phải để báo hiệu cho xe phía sau biết được vượt và đi sát về bên phải của phần đường xe chạy cho đến khi xe sau đã vượt qua, không được cản trở đối với xe xin vượt.",
                    dapAn3 = "Cho xe đi sát về bên trái của phần đường xe chạy và ra hiệu cho xe sau vượt, không được gây trở ngại cho xe xin vượt.",
                    dapAn4 = "",
                    dapAnDung = "2",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Trên đường không phân chia thành hai chiều xe chạy riêng biệt, người điều khiển phương tiện phải tránh xe đi ngược chiều như thế nào để bảo đảm an toàn?",
                    dapAn1 = "Giảm tốc độ và cho xe đi về bên phải theo chiều xe chạy của mình.",
                    dapAn2 = "Một trong hai xe phải dừng lại cho xe kia đi qua mới được đi.",
                    dapAn3 = "Tăng tốc độ, cho xe đi về bên phải theo chiều xe chạy của mình để nhanh chóng vượt qua.",
                    dapAn4 = "",
                    dapAnDung = "1",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Khi tránh xe đi ngược chiều, các xe phải nhường đường như thế nào là đúng quy tắc giao thông?",
                    dapAn1 = "Nơi đường hẹp chỉ đủ cho một xe chạy và có chỗ tránh xe thì xe nào ở gần chỗ tránh hơn phải vào vị trí tránh, nhường đường cho xe đi ngược chiều.",
                    dapAn2 = "Xe xuống dốc phải nhường đường cho xe lên dốc.",
                    dapAn3 = "Xe có chướng ngại vật phía trước phải nhường đường cho xe không có chướng ngại vật phía trước.",
                    dapAn4 = "Cả ba ý trên.",
                    dapAnDung = "4",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Người lái xe phải làm gì để bảo đảm an toàn khi lái xe trên đường cong có tầm nhìn bị hạn chế?",
                    dapAn1 = "Quan sát, giảm tốc độ hoặc dừng lại để bảo đảm an toàn.",
                    dapAn2 = "Đi sang làn đường của xe ngược chiều để mở rộng tầm nhìn và vượt xe khác.",
                    dapAn3 = "Cho xe đi sát bên phải làn đường, bật tín hiệu báo hiệu để vượt bên phải xe khác.",
                    dapAn4 = "",
                    dapAnDung = "1",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Tại nơi đường giao nhau, người lái xe đang đi trên đường không ưu tiên, đường nhánh phải nhường đường như thế nào là đúng quy tắc giao thông?",
                    dapAn1 = "Nhường đường cho xe đi ở bên phải mình tới.",
                    dapAn2 = "Nhường đường cho xe đi ở bên trái mình tới.",
                    dapAn3 = "Nhường đường cho xe đi trên đường ưu tiên hoặc đường chính từ bất kỳ hướng nào tới.",
                    dapAn4 = "",
                    dapAnDung = "3",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Tại nơi đường giao nhau có báo hiệu đi theo vòng xuyến, người lái xe phải nhường đường như thế nào là đúng quy tắc giao thông?",
                    dapAn1 = "Nhường đường cho xe đi đến từ bên phải.",
                    dapAn2 = "Nhường đường cho xe đi đến từ bên trái.",
                    dapAn3 = "Không phải nhường đường.",
                    dapAn4 = "",
                    dapAnDung = "2",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Tại nơi đường giao nhau không có báo hiệu đi theo vòng xuyến, người điều khiển phương tiện phải nhường đường như thế nào là đúng quy tắc giao thông?",
                    dapAn1 = "Phải nhường đường cho xe đi đến từ bên phải.",
                    dapAn2 = "Xe báo hiệu xin đường trước, xe đó được đi trước.",
                    dapAn3 = "Phải nhường đường cho xe đi đến từ bên trái.",
                    dapAn4 = "",
                    dapAnDung = "1",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Người lái xe phải nhanh chóng giảm tốc độ, đi sát lề đường bên phải hoặc dừng lại để nhường đường cho các loại xe nào dưới đây?",
                    dapAn1 = "Xe chữa cháy, quân sự, công an và kiểm sát; đoàn xe có xe Cảnh sát giao thông dẫn đường; xe cứu thương; xe hộ đê không có tín hiệu ưu tiên.",
                    dapAn2 = "Xe ưu tiên gồm xe chữa cháy, quân sự, công an, cứu thương, hộ đê, cứu nạn cứu hộ, đoàn xe tang...",
                    dapAn3 = "Xe ô tô, xe máy, đoàn xe đang diễu hành có tín hiệu xin vượt.",
                    dapAn4 = "",
                    dapAnDung = "2",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Khi có tín hiệu của xe ưu tiên, người và phương tiện tham gia giao thông đường bộ phải tuân thủ quy định nào dưới đây?",
                    dapAn1 = "Giảm tốc độ, đi sát lề đường bên phải hoặc dừng lại để nhường đường.",
                    dapAn2 = "Tăng tốc độ và đi sát lề đường bên phải để nhường đường.",
                    dapAn3 = "Giảm tốc độ, đi sát lề đường bên trái để nhường đường.",
                    dapAn4 = "",
                    dapAnDung = "1",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Khi đang lái xe, phía trước có một xe Cảnh sát giao thông không phát tín hiệu ưu tiên, người lái xe có được phép vượt hay không?",
                    dapAn1 = "Không được vượt.",
                    dapAn2 = "Được phép vượt ở phần đường dành cho người đi bộ qua đường.",
                    dapAn3 = "Được vượt khi bảo đảm an toàn.",
                    dapAn4 = "",
                    dapAnDung = "3",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Khi đang lái xe, phía trước có một xe cứu thương đang phát tín hiệu ưu tiên, người lái xe có được phép vượt hay không?",
                    dapAn1 = "Không được vượt.",
                    dapAn2 = "Được vượt khi đang đi trên cầu.",
                    dapAn3 = "Được phép vượt khi đi qua nơi giao nhau có ít phương tiện cùng tham gia giao thông.",
                    dapAn4 = "Được vượt khi bảo đảm an toàn.",
                    dapAnDung = "1",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Khi tới đường ngang không có người gác, chắn đường bộ, chuông, đèn tín hiệu, người tham gia giao thông đường bộ phải làm gì để bảo đảm an toàn?",
                    dapAn1 = "Dừng lại về bên phải đường của mình, trước vạch dừng xe và quan sát hai phía, khi không có phương tiện giao thông đường sắt tới mới được đi qua.",
                    dapAn2 = "Quan sát hai phía, khi không có phương tiện giao thông đường sắt tới thì nhanh chóng đi qua.",
                    dapAn3 = "Dừng lại khoảng cách tối thiểu 3 mét tính từ ray đường sắt gần nhất, khi không có phương tiện giao thông đường sắt tới thì nhanh chóng đi qua.",
                    dapAn4 = "",
                    dapAnDung = "1",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Tại đường ngang, cầu chung đường sắt, khi có hiệu lệnh của nhân viên gác chắn, đèn đỏ sáng nhấp nháy, chuông kêu, chắn đường bộ đang dịch chuyển hoặc đã đóng, người tham gia giao thông đường bộ phải làm gì để bảo đảm an toàn?",
                    dapAn1 = "Dừng lại về bên trái đường của mình, trước vạch dừng xe.",
                    dapAn2 = "Dừng lại giữa đường của mình, trước vạch dừng xe.",
                    dapAn3 = "Dừng lại về bên phải đường của mình, trước vạch dừng xe.",
                    dapAn4 = "",
                    dapAnDung = "3",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Người tham gia giao thông đường bộ phải dừng lại về bên phải đường của mình trước vạch dừng xe tại đường ngang, cầu chung đường sắt khi có báo hiệu nào dưới đây?",
                    dapAn1 = "Hiệu lệnh của nhân viên gác chắn.",
                    dapAn2 = "Đèn đỏ sáng nhấp nháy, chuông kêu.",
                    dapAn3 = "Chắn đường bộ đang dịch chuyển hoặc đã đóng.",
                    dapAn4 = "Cả ba ý trên.",
                    dapAnDung = "4",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Người điều khiển phương tiện tham gia giao thông trong hầm đường bộ ngoài việc phải tuân thủ các quy tắc giao thông còn phải thực hiện những quy định nào dưới đây?",
                    dapAn1 = "Xe cơ giới, xe máy chuyên dùng phải bật đèn chiếu gần; không dừng, đỗ; nếu gặp sự cố phải bật đèn khẩn cấp và đặt biển cảnh báo phía sau xe.",
                    dapAn2 = "Xe cơ giới, xe máy chuyên dùng phải bật đèn chiếu xa; được dừng, đỗ khi cần thiết.",
                    dapAn3 = "Phải cho xe chạy trên một làn đường và chỉ chuyển làn ở nơi được phép; được quay đầu xe, lùi xe khi cần thiết.",
                    dapAn4 = "",
                    dapAnDung = "1",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Người điều khiển phương tiện tham gia giao thông đường bộ phải quan sát, giảm tốc độ hoặc dừng lại để bảo đảm an toàn trong các trường hợp nào dưới đây?",
                    dapAn1 = "Có báo hiệu cảnh báo nguy hiểm hoặc có chướng ngại vật trên đường; chuyển hướng xe chạy hoặc tầm nhìn bị hạn chế.",
                    dapAn2 = "Nơi cầu, cống hẹp, đập tràn, đường ngầm, hầm chui, hầm đường bộ; có vật nuôi đi trên đường hoặc chăn thả ở ven đường.",
                    dapAn3 = "Điểm dừng xe, đỗ xe trên đường bộ có khách đang lên, xuống xe.",
                    dapAn4 = "Cả ba ý trên.",
                    dapAnDung = "4",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Người lái xe được phép vượt xe khác về bên phải trong trường hợp nào dưới đây?",
                    dapAn1 = "Xe phía trước có tín hiệu rẽ trái hoặc đang rẽ trái hoặc khi xe chuyên dùng đang làm việc trên đường mà không thể vượt bên trái.",
                    dapAn2 = "Xe phía trước đang đi sát lề đường bên trái.",
                    dapAn3 = "Cả hai ý trên.",
                    dapAn4 = "",
                    dapAnDung = "1",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Khi có xe xin vượt, người lái xe mô tô xử lý như thế nào nếu đủ điều kiện an toàn cho xe phía sau vượt?",
                    dapAn1 = "Giảm tốc độ, có tín hiệu rẽ phải để báo hiệu cho người điều khiển phương tiện tham gia giao thông đường bộ phía sau biết được vượt và đi sát về bên phải của phần đường xe chạy cho đến khi xe sau đã vượt qua, không được cản trở đối với xe xin vượt.",
                    dapAn2 = "Lái xe vào lề đường bên trái và giảm tốc độ để xe phía sau vượt qua, không được gây trở ngại đối với xe xin vượt.",
                    dapAn3 = "Tăng tốc độ, đi sát về bên phải của phần đường xe chạy cho đến khi xe sau đã vượt qua.",
                    dapAn4 = "",
                    dapAnDung = "1",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Những trường hợp nào dưới đây không được đi trên đường cao tốc, trừ người, phương tiện giao thông đường bộ và thiết bị phục vụ việc quản lý, bảo trì đường cao tốc?",
                    dapAn1 = "Xe máy chuyên dùng có tốc độ thiết kế nhỏ hơn tốc độ tối thiểu quy định đối với đường cao tốc, xe chở người bốn bánh có gắn động cơ, xe chở hàng bốn bánh có gắn động cơ, xe mô tô, xe gắn máy, các loại xe tương tự xe mô tô, xe gắn máy, xe thô sơ, người đi bộ.",
                    dapAn2 = "Xe máy chuyên dùng có tốc độ thiết kế lớn hơn tốc độ tối thiểu quy định đối với đường cao tốc.",
                    dapAn3 = "Xe ô tô và xe máy chuyên dùng có tốc độ thiết kế lớn hơn 80 km/h.",
                    dapAn4 = "",
                    dapAnDung = "1",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Theo quy định về độ tuổi, người đủ bao nhiêu tuổi trở lên thì được cấp giấy phép lái xe mô tô hai bánh có dung tích xi lanh đến 125 cm3 và xe ô tô chở người đến 8 chỗ (không kể chỗ của người lái xe); xe ô tô tải và ô tô chuyên dùng có khối lượng toàn bộ theo thiết kế đến 3.500 kg?",
                    dapAn1 = "16 tuổi.",
                    dapAn2 = "17 tuổi.",
                    dapAn3 = "18 tuổi.",
                    dapAn4 = "",
                    dapAnDung = "3",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Người đủ 16 tuổi đến dưới 18 tuổi chỉ được điều khiển các loại xe nào dưới đây?",
                    dapAn1 = "Xe mô tô hai bánh có dung tích xi-lanh đến 125 cm3.",
                    dapAn2 = "Xe gắn máy.",
                    dapAn3 = "Xe ô tô chở người đến 08 chỗ (không kể chỗ của người lái xe); xe ô tô tải và ô tô chuyên dùng có khối lượng toàn bộ theo thiết kế đến 3.500 kg; các loại xe ô tô quy định cho giấy phép lái xe hạng B kéo rơ moóc có khối lượng toàn bộ theo thiết kế đến 750 kg.",
                    dapAn4 = "Cả ba ý trên.",
                    dapAnDung = "2",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Người có Giấy phép lái xe mô tô hạng A1 không được phép điều khiển loại xe nào dưới đây?",
                    dapAn1 = "Xe mô tô hai bánh có dung tích xi-lanh 125 cm3 hoặc có công suất động cơ điện đến 11 kW.",
                    dapAn2 = "Xe mô tô ba bánh.",
                    dapAn3 = "Cả hai ý trên.",
                    dapAn4 = "",
                    dapAnDung = "2",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Người có Giấy phép lái xe mô tô hạng A1 được cấp sau ngày 01/01/2025 được phép điều khiển loại xe nào dưới đây?",
                    dapAn1 = "Xe mô tô hai bánh có dung tích xi-lanh đến 125 cm3 hoặc có công suất động cơ điện đến 11 kW.",
                    dapAn2 = "Xe mô tô ba bánh.",
                    dapAn3 = "Cả hai ý trên.",
                    dapAn4 = "",
                    dapAnDung = "1",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Người có Giấy phép lái xe mô tô hạng A được phép điều khiển loại xe nào dưới đây?",
                    dapAn1 = "Xe mô tô hai bánh có dung tích xi-lanh đến 125 cm3 hoặc có công suất động cơ điện đến 11 kW.",
                    dapAn2 = "Xe mô tô hai bánh có dung tích xi-lanh trên 125 cm3 hoặc có công suất động cơ điện trên 11 kW.",
                    dapAn3 = "Cả hai ý trên.",
                    dapAn4 = "",
                    dapAnDung = "3",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Người lái xe khi tham gia giao thông đường bộ phải đảm bảo các điều kiện nào dưới đây?",
                    dapAn1 = "Phải đủ tuổi, sức khỏe theo quy định của pháp luật; có giấy phép lái xe đang còn điểm, còn hiệu lực phù hợp với loại xe đang điều khiển do cơ quan có thẩm quyền cấp (trừ người lái xe gắn máy).",
                    dapAn2 = "Phải là người đứng tên trong đăng ký xe.",
                    dapAn3 = "Cả hai ý trên.",
                    dapAn4 = "",
                    dapAnDung = "1",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Khi tham gia giao thông đường bộ, người lái xe phải mang theo các giấy tờ gì?",
                    dapAn1 = "Chứng nhận đăng ký xe hoặc bản sao Chứng nhận đăng ký xe có chứng thực kèm bản gốc giấy tờ xác nhận của tổ chức tín dụng, chi nhánh ngân hàng nước ngoài còn hiệu lực trong trường hợp xe đang được thế chấp tại tổ chức tín dụng, chi nhánh ngân hàng nước ngoài.",
                    dapAn2 = "Giấy phép lái xe phù hợp với loại xe đang điều khiển; chứng nhận kiểm định an toàn kỹ thuật và bảo vệ môi trường đối với xe cơ giới theo quy định của pháp luật; chứng nhận bảo hiểm bắt buộc trách nhiệm dân sự của chủ xe cơ giới.",
                    dapAn3 = "Trường hợp các giấy tờ nêu trên đã được tích hợp vào tài khoản định danh điện tử thì việc xuất trình, kiểm tra có thể thực hiện thông qua tài khoản định danh điện tử.",
                    dapAn4 = "Cả ba ý trên.",
                    dapAnDung = "4",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Người có giấy phép lái xe chưa bị trừ hết 12 điểm, được phục hồi điểm giấy phép lái xe trong trường hợp nào sau đây?",
                    dapAn1 = "Không được phục hồi.",
                    dapAn2 = "Được phục hồi đủ 12 điểm, nếu không bị trừ điểm trong thời hạn 12 tháng từ ngày bị trừ điểm gần nhất.",
                    dapAn3 = "",
                    dapAn4 = "",
                    dapAnDung = "2",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Người có giấy phép lái xe đã bị trừ hết điểm phải làm gì để phục hồi điểm giấy phép lái xe?",
                    dapAn1 = "Không vi phạm pháp luật trật tự, an toàn giao thông đường bộ trong thời gian 12 tháng kể từ ngày bị trừ hết điểm.",
                    dapAn2 = "Sau thời hạn ít nhất là 06 tháng kể từ ngày bị trừ hết điểm, người có phép lái xe được tham gia kiểm tra nội dung kiến thức pháp luật về trật tự, an toàn giao thông đường bộ theo quy định, có kết quả đạt yêu cầu thì được phục hồi đủ 12 điểm.",
                    dapAn3 = "Cả hai ý trên.",
                    dapAn4 = "",
                    dapAnDung = "2",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Trách nhiệm của tổ chức, cá nhân đứng tên trong giấy chứng nhận đăng ký xe khi chưa thực hiện thu hồi chứng nhận đăng ký xe, biển số xe được quy định như thế nào?",
                    dapAn1 = "Tiếp tục chịu trách nhiệm của chủ xe.",
                    dapAn2 = "Không chịu trách nhiệm sau khi đã chuyển nhượng, trao đổi, tặng, cho.",
                    dapAn3 = "",
                    dapAn4 = "",
                    dapAnDung = "1",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Trên đường bộ, trong khu vực đông dân cư, đường đôi hoặc đường một chiều có từ hai làn xe cơ giới trở lên, xe mô tô hai bánh, ô tô chở người đến 28 chỗ không kể chỗ của người lái xe tham gia giao thông với tốc độ khai thác tối đa cho phép là bao nhiêu?",
                    dapAn1 = "60 km/h.",
                    dapAn2 = "50 km/h.",
                    dapAn3 = "40 km/h.",
                    dapAn4 = "",
                    dapAnDung = "1",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Trên đường bộ (trừ đường cao tốc) trong khu vực đông dân cư, đường hai chiều hoặc đường một chiều có một làn xe cơ giới, xe mô tô hai bánh, ô tô chở người đến 28 chỗ không kể chỗ của người lái xe tham gia giao thông với tốc độ khai thác tối đa cho phép là bao nhiêu?",
                    dapAn1 = "60 km/h.",
                    dapAn2 = "50 km/h.",
                    dapAn3 = "40 km/h.",
                    dapAn4 = "",
                    dapAnDung = "2",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Trên đường bộ ngoài khu vực đông dân cư, đường đôi hoặc đường một chiều có từ hai làn xe cơ giới trở lên (trừ đường cao tốc) loại xe nào dưới đây được tham gia giao thông với tốc độ khai thác tối đa cho phép là 70 km/h?",
                    dapAn1 = "Xe ô tô chở người đến 28 chỗ không kể chỗ của người lái xe (trừ xe buýt); ô tô tải có trọng tải không lớn hơn 3,5 tấn.",
                    dapAn2 = "Xe ô tô chở người trên 28 chỗ không kể chỗ người lái xe (trừ xe buýt); ô tô tải có trọng tải trên 3,5 tấn (trừ ô tô xi téc).",
                    dapAn3 = "Xe buýt; ô tô đầu kéo kéo sơ mi rơ moóc (trừ ô tô đầu kéo kéo sơ mi rơ moóc xi téc); xe mô tô; ô tô chuyên dùng (trừ ô tô trộn vữa, ô tô trộn bê tông lưu động).",
                    dapAn4 = "Ô tô kéo rơ moóc; ô tô kéo xe khác; ô tô trộn vữa, ô tô trộn bê tông lưu động, ô tô xi téc, ô tô đầu kéo kéo sơ mi rơ moóc xi téc, ô tô kéo theo rơ moóc xi téc.",
                    dapAnDung = "3",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Trên đường bộ ngoài khu vực đông dân cư, đường hai chiều hoặc đường một chiều có một làn xe cơ giới (trừ đường cao tốc), loại xe nào dưới đây được tham gia giao thông với tốc độ khai thác tối đa cho phép là 60 km/h?",
                    dapAn1 = "Xe ô tô chở người đến 28 chỗ không kể chỗ của người lái xe (trừ xe buýt); ô tô tải có trọng tải không lớn hơn 3,5 tấn.",
                    dapAn2 = "Xe ô tô chở người trên 28 chỗ không kể chỗ người lái xe (trừ xe buýt); ô tô tải có trọng tải trên 3,5 tấn (trừ ô tô xi téc).",
                    dapAn3 = "Xe buýt; ô tô đầu kéo kéo sơ mi rơ moóc (trừ ô tô đầu kéo kéo sơ mi rơ moóc xi téc); xe mô tô; ô tô chuyên dùng (trừ ô tô trộn vữa, ô tô trộn bê tông lưu động).",
                    dapAn4 = "Ô tô kéo rơ moóc; ô tô kéo xe khác; ô tô trộn vữa, ô tô trộn bê tông lưu động, ô tô xi téc, ô tô đầu kéo kéo sơ mi rơ moóc xi téc, ô tô kéo theo rơ moóc xi téc.",
                    dapAnDung = "3",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Người lái xe phải giảm tốc độ thấp hơn tốc độ tối đa cho phép đến mức cần thiết, chú ý quan sát và chuẩn bị sẵn sàng những tình huống có thể xảy ra để phòng ngừa tai nạn trong các trường hợp nào dưới đây?",
                    dapAn1 = "Gặp biển báo nguy hiểm và cảnh báo trên đường.",
                    dapAn2 = "Gặp biển chỉ dẫn trên đường.",
                    dapAn3 = "Gặp biển báo hết mọi lệnh cấm.",
                    dapAn4 = "Gặp biển báo hết hạn chế tốc độ tối đa cho phép.",
                    dapAnDung = "1",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Khi gặp xe buýt đang dừng đón, trả khách, người điều khiển xe mô tô phải xử lý như thế nào dưới đây?",
                    dapAn1 = "Tăng tốc độ để nhanh chóng vượt qua xe buýt.",
                    dapAn2 = "Quan sát, giảm tốc độ đi qua xe buýt hoặc dừng lại để bảo đảm an toàn.",
                    dapAn3 = "",
                    dapAn4 = "",
                    dapAnDung = "2",
                    chuDe = "Khái niệm và Quy tắc"
                ),
                LyThuyet(
                    cauHoi = "Việc sử dụng xe mô tô, xe gắn máy, xe thô sơ để vận chuyển hành khách, hàng hóa phải thực hiện các quy định nào dưới đây để đảm bảo an toàn giao thông?",
                    dapAn1 = "Kiểm tra điều kiện bảo đảm an toàn của xe trước khi tham gia giao thông đường bộ; mang đủ giấy tờ theo quy định của pháp luật.",
                    dapAn2 = "Kiểm tra việc sắp xếp hàng hóa bảo đảm an toàn; không chở quá số người, chở hành lý, hàng hoá vượt quá khối lượng cho phép hoặc vượt quá khổ giới hạn của xe.",
                    dapAn3 = "Cả hai ý trên.",
                    dapAn4 = "",
                    dapAnDung = "3",
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

# 🚦 ỨNG DỤNG ÔN THI LÝ THUYẾT GPLX HẠNG A, A1

## 📖 1. Lý do chọn đề tài
Hiện nay, việc ôn luyện lý thuyết lái xe mô tô hạng A1, A chủ yếu dựa vào sách giấy hoặc các nguồn thông tin rời rạc trên internet. Điều này khiến người học gặp nhiều khó khăn trong việc tra cứu, ghi nhớ và luyện tập hiệu quả.

Ứng dụng **Ôn thi GPLX A1, A** được xây dựng với mục tiêu mang đến **trải nghiệm học tập thông minh – tiện lợi – linh hoạt**, giúp người học có thể:
- Ôn tập mọi lúc, mọi nơi.
- Theo dõi tiến độ học tập.
- Ghi nhớ nhanh hơn nhờ giao diện trực quan và tính năng hỗ trợ học tập hiện đại.

Đây là một dự án phù hợp với **xu hướng số hóa giáo dục** và **phát triển ứng dụng di động hiện đại**, mang lại giá trị thực tế cho người dùng.

---

## 💡 2. Ý tưởng
Ứng dụng hoạt động như một **trợ lý học tập cá nhân**, hỗ trợ người dùng trong quá trình ôn luyện lý thuyết lái xe.  
Người dùng có thể:

- Chọn **chủ đề** để ôn tập và trả lời câu hỏi, kèm **gợi ý chi tiết và giải thích đáp án**.  
- **Theo dõi và lưu tiến độ học tập**, giúp dễ dàng tiếp tục ôn luyện ở lần sau.  
- **Nhấn mạnh câu hỏi điểm liệt**, giúp người học nhận diện và ghi nhớ kiến thức quan trọng.  
- **Tra cứu nhanh biển báo giao thông** với hình ảnh minh họa rõ ràng.  
- **Xem mẹo ôn thi** giúp ghi nhớ nhanh và đạt điểm cao trong bài thi chính thức.

---

## 🔍 3. Nghiên cứu và phân tích

### 3.1. Nhu cầu người dùng
- Ứng dụng cần có **giao diện thân thiện**, dễ nhìn, dễ sử dụng.  
- Người **không rành công nghệ** vẫn có thể thao tác dễ dàng, không cần tra cứu phức tạp.  
- Có thể **học tập linh hoạt mọi lúc rảnh rỗi** mà không phụ thuộc vào kết nối internet.

### 3.2. Đối tượng sử dụng
- Người **từ 18 tuổi trở lên**, chưa có giấy phép lái xe mô tô hạng A1 hoặc A.  

---

## ⚙️ 4. Tính năng chính

### 1️⃣ Chọn hạng thi
- Lựa chọn **hạng A1 hoặc A** để cá nhân hóa nội dung câu hỏi và bộ đề thi phù hợp.

### 2️⃣ Ôn tập lý thuyết
- Ôn tập theo từng **chủ đề**:
  - Khái niệm & Quy tắc giao thông  
  - Văn hoá & Đạo đức người lái xe  
  - Kỹ thuật lái xe  
  - Biển báo đường bộ  
  - Sa hình  
  - Tổng hợp câu **điểm liệt**  
- Mỗi câu hỏi đều có **gợi ý và lời giải chi tiết** giúp hiểu rõ bản chất.

### 3️⃣ Làm đề thi thử
- Cung cấp nhiều **bộ đề thi thử** mô phỏng bài thi thật.  
- Hiển thị **kết quả ngay sau khi hoàn thành**, bao gồm số câu đúng, sai, và đánh giá đạt/không đạt.

### 4️⃣ Tra cứu biển báo
- Hiển thị danh sách **biển báo giao thông** với hình ảnh minh họa, tên và mô tả chi tiết.

### 5️⃣ Mẹo ôn thi
- Tổng hợp các **mẹo học nhanh – nhớ lâu**, giúp đạt kết quả cao trong kỳ thi lý thuyết.

### 6️⃣ Cài đặt
- Cho phép:
  - **Chọn lại hạng thi** khác.  
  - **Xoá dữ liệu** để bắt đầu ôn lại từ đầu.  

---

## 🧩 5. Công nghệ sử dụng
- **Ngôn ngữ:** Kotlin  
- **Giao diện:** Jetpack Compose (Material Design 3)  
- **Cơ sở dữ liệu:** SQLite (qua `SQLiteOpenHelper`)  
- **Kiến trúc:** Navigation Component + ViewModel (tuỳ chọn mở rộng)  
- **Hình ảnh:** Lưu trong thư mục `drawable/`  
- **Tương thích:** Android 8.0 (API 26) trở lên  

---

## 📱 6. Giao diện minh hoạ (dự kiến)
- **Màn hình chính:** Chọn chức năng (Ôn tập – Thi thử – Biển báo – Mẹo ôn thi – Cài đặt)  
- **Màn hình ôn tập:** Hiển thị câu hỏi, lựa chọn đáp án, gợi ý và lời giải.  
- **Màn hình kết quả:** Thống kê chi tiết kết quả làm bài.  
- **Màn hình biển báo:** Danh sách kèm hình ảnh và mô tả từng biển.  

---

## 👥 7. Nhóm thực hiện
- **Tên dự án:** Ứng dụng Ôn Thi GPLX A, A1  
- **Thành viên:** *Võ Nguyễn Phong Phú*, *Nguyễn Phạm Thanh Duy*, *Huỳnh Hồng Thịnh*, *Lê Văn Giang* 
- **Giảng viên hướng dẫn:** *Trương Quang Tuấn*  

---

### 🏁 Kết luận
Ứng dụng **Ôn Thi GPLX A1, A** không chỉ giúp người dùng học nhanh – nhớ lâu mà còn góp phần hiện đại hoá phương pháp ôn tập truyền thống, hướng tới mục tiêu **“Học hiệu quả – Thi dễ dàng – Đạt kết quả cao”**.

---

📦 *Repo được xây dựng bằng Android Studio – Jetpack Compose.*

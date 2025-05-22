# Cinema Ticket Web App 



## Mục lục
- Giới thiệu
- Tính năng
- Công nghệ
- Cài đặt


## 1.Giới thiệu
Trong thời đại công nghệ kỹ xảo ngày càng phát triển, nhu cầu xem phim của khán giả cũng không ngừng 
tăng cao, đặc biệt là với những trải nghiệm chân thực, sống động tại rạp chiếu. Điều này thúc đẩy
sự cần thiết của một hệ thống đặt vé tiện lợi, nhanh chóng và thân thiện với người dùng. Dự án xây 
dựng một hệ thống không chỉ tiện lợi đối với người dùng mà còn giúp ích rất nhiều công việc quản lý 
của rạp chiếu, giảm chi phí thuê nhân công mà hiệu suất công việc lại vượt trội.


## 2.Tính năng
**Khách hàng:**
- Đăng nhập, đăng ký, đặt lại mật khẩu.
- Quản lý thông tin cá nhân.
- Tìm kiếm phim
- Xem thông tin giới thiệu về phim 
- Đặt vé xem phim và đồ ăn theo từng rạp, từng ghế và suất chiếu.


**Quản trị viên**
- Quản lý tài khoản khách hàng (thay đổi một số thông tin cho phép, tích điểm, tạo xóa tài khoản...)
- Quản lý thông tin rạp (Địa chỉ, phòng chiếu, ghế ngồi)
- Quản lý phim, suất chiếu theo rạp


## 3.Công nghệ
- **Backend:** Spring boot, spring security, JPA, Hibernate,...
- **Frontend:** Reactjs, javascript, html,...
- **Database:** MySQL
- **Một số công cụ hỗ trợ khác:** Postman, Intellij, MySQL Workbench, Github...



## 4.Kiến trúc hệ thống
**Hệ thống chia làm hai phần:**
- Front-End (FE): Giao diện người dùng, gọi API đến server.
- Back-End (BE): Server Spring Boot xác thực người dùng, xử lý logic nghiệp vụ, kết nối CSDL 
và cung cấp API cho FE.
- BE sử dụng RESTful API và bảo mật thông qua JWT. Người dùng được phân quyền theo mô hình role-permission.
  Dữ liệu được lưu trữ trong hệ quản trị cơ sở dữ liệu MySQL.


  
## 5.Cài đặt

### Bước 1 :Clone repository 
### Bước 2: Chạy đồng thời cả BE và FE

### 🔹 Back-End 

```bash
# Move to the backend folder
cd backendFoderInYourPC

# Build the project
./mvnw clean install

# Run the application
./mvnw spring-boot:run

```

### 🔹 Front-End (Spring Boot)

```bash
# Move to the frontend folder
cd frontendFoderInYourPC

# Install dependencies
npm install

# Run the app
npm start




















# Authorization Server
Authorization Server adalah backend server yang digunakan untuk otorisasi dan databse user.
Silahkan baca [API Reference]() dan [Dokumentasi]() yang tersedia. 

## Stack 
- Spring Boot
- Oauth 2
- JWT
- MySQL

## System Reqirement
Untuk dapat mengeksekusi program ini diderlukan beberapa tools, diantaranya adalah :
- JDK 1.8
- Apache Maven
- Apache Tomcat
- MySQL

# Fitur
- User, Role, Permission Pattern.
- RESTfull API Service.
- CSRF Protection for Website
- JWT Filter
- SSO github dan Facebook
 
## Menjalankan program
- Buat database mysql dengan username `auth`. Kemudian Berikan hak akses menggunakan `grant all on auth.* to user@localhost identified by 'pass'`   
- Gunakan perintah berikut untuk mengeksekusi program Authorization Server
```
mvn clean spring-boot:run
```
aplikasi akan berjalan di browser anda pada port 8080.

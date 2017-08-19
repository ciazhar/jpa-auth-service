# Authorization Server
Authorization Server adalah backend server yang digunakan untuk otorisasi.

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
- API. Url API bisa dilihat di controller bagian `Request Mapping` saya capek nulis daftar API nya soalnya banyak banget. Isinya
    - Login/Register
    - Verifikasi dengan Email
    - CRUD + Searching Data
    - Upload Foto
- CSRF Protection
- JWT Filter
- SSO github dan Facebook


## Menjalankan program
- Buat database mysql dengan username `belajar_oauth`. Kemudian Berikan hak akses menggunakan `grant all on pelatihan.* to pelatihanuser@localhost identified by 'pelatihanpasswd'`
- Copy data dummy yang ada pada `src/main/resource/db/migration` ke database
- Daftarkan aplikasi anda ke `src/main/java/com/ciazhar/authserver/config/security/OAuth2Config` pada methode `public void configure(ClientDetailsServiceConfigurer clients)`. Anda akan diminta untuk mencantumkan client id, client secret, grant type, authorities dan scope.   
- Gunakan perintah berikut untuk mengeksekusi program Authorization Server
```
mvn clean spring-boot:run
```
aplikasi akan berjalan di browser anda pada port 8080.

Selanjutnya proses dibawah ini akan berjalan di system, tetapi apabila ingin melihat prosesnya dapat mengikut langkah berikut :
- Gunakan url berikut 
```
http://localhost:8080/oauth/authorize?client_id=nama-service&response_type=code&redirect_uri=http://example.com

```
client-id disesuaikan nama client id yang anda cantumkan pada `src/main/java/com/ciazhar/authserver/config/security/OAuth2Config`.
- Masukkan username admin dan password 123. Daftar user dapat dilihat di data dummy sql tadi. 
- Kemudian anda akan mendapatkan code. Gunakakan code tersebut dengan menggunakan `curl`
```
curl -X POST -vu nama-service:123456 http://localhost:8080/oauth/token -H "Accept: application/json" -d "grant_type=authorization_code&code=v2SWjN&redirect_uri=http://example.com"
```
client-id disesuaikan nama client id yang anda cantumkan pada `src/main/java/com/ciazhar/authserver/config/security/OAuth2Config`. code disesuaikan dengan code yang anda dapat tadi.
- Kemudian anda akan mendapatkan `acces_token`. Gunakan access token tersebut untuk setiap request ke backend anda. Apabila anda ingin melihat enkripsi dari access token tersebut anda dapat mengunjungi website `jwt.io`.

### JWT Filter
Kunci dari jwt filter adalah memfilter request dari user terhadap API tertentu menggunakan hak akses. Hak akses dapat dilihat dari mendekrip JWT token yang dikirim user saat mengakses API. Di Spring Boot sendiri API dapat di filter menggunakan anotasi @PreAuthorize yang diletakkan di atas methode API. 
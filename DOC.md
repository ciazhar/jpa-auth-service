# Spring Security

# OAuth 2
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

# JWT Filter
Kunci dari jwt filter adalah memfilter request dari user terhadap API tertentu menggunakan hak akses. Hak akses dapat dilihat dari mendekrip JWT token yang dikirim user saat mengakses API. Di Spring Boot sendiri API dapat di filter menggunakan anotasi @PreAuthorize yang diletakkan di atas methode API.

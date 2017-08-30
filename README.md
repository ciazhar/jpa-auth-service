# Authorization Server
Authorization Server adalah backend server yang digunakan untuk otorisasi, databse costumer, .

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

Method	| Path	| Description	| Common User	| Super User
------------- | ------------------------- | ------------- |:-------------:|:----------------:|
GET	| /api/user	| Get user session data	| v | v	
POST	| /api/user/register	| Register	| v | v
GET	| /api/user/single?id=	| Find single user by id	|   | 	v
GET	| /api/user/all| Get all user	|  | v
POST	| /api/user/update	| Update profile	| v | v
POST	| /api/user/change-username	| Change username	| v | v
POST	| /api/user/change-phone	| Change phone	|  v | v
POST	| /api/user/change-username	| Change username	| v | v
POST	| /api/user/change-birthdate	| Change birthdate	| v | v
POST	| /api/user/change-password	| Change password	| v | v
POST	| /api/user/change-email	| Change email	| v | v
POST	| /api/user/change-role	| Change role	|   | v
POST	| /api/user/upload/avatar	| Change avatar	| v | v
POST	| /api/user/device/android?id=&photo=	| Change android device id	| v | v
POST	| /api/user/delete	| Delete user	|   | v
POST	| /api/role/create	| Create role	|   | v
GET	| /api/role/single?id= | Find single role by id	|   | v
GET	| /api/role/all	| Find all role	|   | v
POST	| /api/role/delete	| Delete role	|   | v
POST	| /api/permission/create	| Create permission	|   | v
GET	| /api/permission/single?id= | Find single permission by id	|   | v
GET	| /api/permission/all	| Find all permission|   | v
POST	| /api/permission/delete	| Delete permission	|   | v
GET	| /activate?email=	| Activate Account	|  v  |  v 

- CSRF Protection for Website
- JWT Filter
- SSO github dan Facebook

### JWT Filter
Kunci dari jwt filter adalah memfilter request dari user terhadap API tertentu menggunakan hak akses. Hak akses dapat dilihat dari mendekrip JWT token yang dikirim user saat mengakses API. Di Spring Boot sendiri API dapat di filter menggunakan anotasi @PreAuthorize yang diletakkan di atas methode API.
 
## Sample JSON
Sample JSON file untuk testing dapat dilihat di direktori `/src/main/resource/json`

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

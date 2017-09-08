# Flow Spring Security
Ketika user ingin melakukan login, user dapat melakukan request ke `/login`. Kemudian user akan dihadapkan dengan form login. Data yang dikirim dari form tersebut berupa username, password dan CSRF token. Data tersebut adalah default dan dapat diganti, semisal username diganti email. Apabila login berhasil, user akan mendapatkan session bernama `AUTHSERVER` dan `JSESSIONID`. Session ini yang menandakan bahwa user tersebut telah authenticated. Apabila request gagal maka akan redirect ke `/login?error` dan meminta user untuk mengisi kembali form login.  



# Flow OAuth 2 
OAuth2 memiliki beberapa Grant Type. Grant type merupakan metode yang digunakan untuk mendapatkan akses token. Akses token tersebut yang akan digunakan untuk mengkses API. Grant type tersebut diantaranya yaitu :
- Authorization Code
- Implicit
- Password
- Client Credentials

### Authorization Code
Grant type ini disarankan apabila client berupa backend. Dimana backend dapat menyimpan secret. 

### Implicit
Grant type ini disarankan apabila client merupakan frontend third party atau untrusted client. Yang dimaksud untrusted client adalah client yang berasal dari vendor yang berbeda, dimana belum dapat dipercaya sepenuhnya.

### Password
Grant type ini disarankan apabila client merupakan frontend first party atau trusted client.

### Client Credentials
Grant type ini disarankan apabila client berupa mesin. Dimana client tidak memerlukan otorisasi sebagai user tertentu.  



# JWT (JSON WEB TOKEN)
JWT merupakan jenis akses token yang akan digunakan untuk merequest API. API sebelumnya telah difilter menggunakan menggunakan JWT Filter. Filter dapat berupa hak akses seperti Role, Permission, IP address dll. Hak akses dapat dilihat dari mendekrip JWT token yang dikirim user saat mengakses API, atau melalui [Official JWT](https://jwt.io). 

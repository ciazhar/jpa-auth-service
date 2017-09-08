# Auth Server API Reference
Selamat datang ke Auth Server API Reference. Berikut adalah tutorial untuk menggunakan Auth Server. 
Anda juga dapat menggunakan API kami untuk mengakses endpoint di Auth Server. 
Kami menyediakan 2 prefix yaitu `/mobile` untuk tanpa CSRF protection dan `/api` untuk dengan CSRF protection. 

Method	| Path	| Description	| Permission	
------------- | ------------------------- | ------------- |:-------------:
POST	| /login	| Activate Account	|  Login
GET	    | /{PREFIX}/user	| Get user session data	| Login
POST	| /{PREFIX}/user/register	| Register	| Permit All
GET	    | /activate?email=	| Activate Account	|  Permit All
GET 	| /{PREFIX}/user/single?id=	| Find single user by id	| Super User   
GET	    | /{PREFIX}/user/all| Get all user	|  Super User
POST	| /{PREFIX}/user/update	| Update profile	| Anonymous
POST	| /{PREFIX}/user/change-username	| Change username	| Anonymous 
POST	| /{PREFIX}/user/change-phone	| Change phone	|  Anonymous
POST	| /{PREFIX}/user/change-username	| Change username	| Anonymous
POST	| /{PREFIX}/user/change-birthdate	| Change birthdate	| Anonymous
POST	| /{PREFIX}/user/change-password	| Change password	| Anonymous
POST	| /{PREFIX}/user/change-email	| Change email	| Anonymous
POST	| /{PREFIX}/user/change-role	| Change role	| Super User  
POST	| /{PREFIX}/user/upload/avatar	| Change avatar	| Not Available
POST	| /{PREFIX}/user/device/android?id=	| Change android device id	| Not Available  
POST	| /{PREFIX}/user/delete	| Delete user	| Super User
POST	| /{PREFIX}/role/create	| Create role	| Super User
GET	    | /{PREFIX}/role/single?id= | Find single role by id	| Super User
GET	    | /{PREFIX}/role/all	| Find all role	|   Super User
POST	| /{PREFIX}/role/delete	| Delete role	|   Super User
POST	| /{PREFIX}/permission/create	| Create permission	| Super User  
GET	    | /{PREFIX}/permission/single?id= | Find single permission by id	|   Super User
GET	    | /{PREFIX}/permission/all	| Find all permission|   Super User
POST	| /{PREFIX}/permission/delete	| Delete permission	|   Super User


## Register Akun
### Format Request
```
curl -d '{"username":"VALUE","email":"VALUE", "password":"VALUE"}' \
-H "Content-Type: application/json" \
POST http://{DOMAIN}/{PREFIX}/user/register;
```
### Contoh Request
```
curl -d '{"username":"a","email":"a@gmail.com", "password":"123"}' \
-H "Content-Type: application/json" \
POST http://localhost:8080/mobile/user/register;
```
### Contoh Response
```
{
  "status" : "success",
  "message" : "Successfully updated your request.",
  "data" : {
    "username" : "ciazhar",
    "email" : "daisetsunakama@gmail.com",
    "password" : "123"
  }
}
```
Kemudian akan dikirim email verifikasi ke kotak masuk email anda. Silahkan ikuti petunjuk yang tertera pada email tersebut untuk melakukan aktivasi.

## Register Apps ke Auth Zharver
Belum

## Get Access Token
Access Token merupakan prasyarat untuk mengakses setiap API di Auth Zharver. Untuk mendapatkan access token tersebut, terdapat beberapa cara sesuai dengan `grant_type` masing masing service yang sudah didaftarkan pada Auth Zharver.  
### Contoh Request Grant Type Authorization Code
```

```
### Format Request Grant Type User Password
```
curl -vu {CLIENT_ID}:{CLIENT_SECRET} \
-d "username={VALUE}&password={VALUE}&grant_type=password" \
POST http://{DOMAIN}/oauth/token;
```
### Contoh Request Grant Type User Password
```
curl -vu web:123456 \
-d "username=ciazhar&password=123&grant_type=password" \
POST http://localhost:8080/oauth/token;
```
### Contoh Request Grant Type Implicit Client
```

```
### Contoh Request Grant Type Client Credential
```

```
### Contoh Response
```json
{
    "access_token": "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1MDQ0OTA0MDgsInVzZXJfbmFtZSI6ImFkbWluIiwiYXV0aG9yaXRpZXMiOlsiQ1JFREVOVElBTF9VU0VSIiwiQkFTSUNfVVNFUiIsIlNVUEVSX1VTRVIiXSwianRpIjoiMWFkMzdmMzMtYzZiZi00ZjRlLThlMDItZTkzODk1NGNlY2FlIiwiY2xpZW50X2lkIjoid2ViIiwic2NvcGUiOlsicmVhZCIsIndyaXRlIl19.SMBVUheinmQG6BOXtkEACdm5aJmn2c41oRioX4VJ-LhP0NA42fpjgj5_q9wLLqy5EbaQ3hJYG2659s7wAltv33YxT3DroSxdxaM2k784IkpsyKaZeLYRKYzp6BtzYC02LsKFhEiva2iANi0wXxYTZUT3DS3aZmSXyv0e1GirfDkyCAIv9baGf8XSF7mCCWSZtcy6doEPImxBao9kduHf8J53O92GecTU_gZNLxon1Sx0BUPWa0A5zLq5Hff5cG5YsPWN-UNypHrOah55YDbMptXgx47dLu_7mpJjk4TnQsKtZCErP2nAGfPdMNy3RYvyXWCxnX_iLfLByWD6yWmJhA",
    "token_type": "bearer",
    "expires_in": 43199,
    "scope": "read write",
    "jti": "1ad37f33-c6bf-4f4e-8e02-e938954cecae"
}
```
Access token diatas dapat anda gunakan seperti cara berikut : 
```
curl -H "Content-Type: application/json" \
-H "Accept: application/json" \
-H "Authorization: Bearer {ACCESS_TOKEN}" \
-d '{YOUR_JSON_DATA}'
{HTTP_METHODE} {URL_API};
```

## Auth Zharver API Reference

## Get User Session Data
### Contoh Request
```
curl -X GET http://localhost:8080/api/user;
```
### Contoh Response
```
{
  "status" : "success",
  "message" : "Successfully updated your request.",
  "data" : {
    "authorities" : [ {
      "authority" : "BASIC_USER"
    }, {
      "authority" : "CREDENTIAL_USER"
    } ],
    "details" : {
      "remoteAddress" : "0:0:0:0:0:0:0:1",
      "sessionId" : "5172CE37887B4506FB397056290802C4"
    },
    "authenticated" : true,
    "principal" : {
      "password" : null,
      "username" : "ciazhar",
      "authorities" : [ {
        "authority" : "BASIC_USER"
      }, {
        "authority" : "CREDENTIAL_USER"
      } ],
      "accountNonExpired" : true,
      "accountNonLocked" : true,
      "credentialsNonExpired" : true,
      "enabled" : true
    },
    "credentials" : null,
    "name" : "ciazhar"
  }
}
```

## Find One User
### Contoh Request
```
curl -H "Authorization: Bearer token" \
-X GET http://localhost:8080/mobile/user/one?id=1;
```
### Contoh Response
```
{
  "status" : "success",
  "message" : "Successfully updated your request.",
  "data" : {
    "id" : "3",
    "enabled" : true,
    "dateCreated" : null,
    "role" : {
      "id" : "3",
      "nama" : "CREDENTIAL_USER",
      "label" : "Credential User"
    },
    "username" : "credential",
    "email" : "credential@mail.com",
    "password" : "$2a$04$v1UZ4q0SCMTX695Wyowu/eVW9pT3giQivAElwH0A9BuvXktpd/qTq",
    "firstName" : null,
    "lastName" : null,
    "dateOfBirth" : null,
    "phoneNumber" : null,
    "avatar" : null
  }
}
```

## Find All User
### Contoh Request
```
curl -H "Authorization: Bearer token" \ 
-X GET http://localhost:8080/mobile/user/all;
```
### Contoh Response
```
{
  "status" : "success",
  "message" : "Successfully updated your request.",
  "data" : [ {
    "id" : "9d01b818-accb-4b7f-ad3d-8da8cae7cbf8",
    "enabled" : false,
    "dateCreated" : "2017-09-06",
    "role" : {
      "id" : "2",
      "nama" : "BASIC_USER",
      "label" : "Basic User"
    },
    "username" : "cz",
    "email" : "aaa@gmail.com",
    "password" : "$2a$10$LhzUN.vh2Xhu42WzU8fZ/uK/qs.sodeXjIuCudUFxmWucF53AcQq.",
    "firstName" : null,
    "lastName" : null,
    "dateOfBirth" : null,
    "phoneNumber" : null,
    "avatar" : null
  }, {
    "id" : "1",
    "enabled" : true,
    "dateCreated" : null,
    "role" : {
      "id" : "1",
      "nama" : "ADMIN",
      "label" : "Admin"
    },
    "username" : "admin",
    "email" : "admin@mail.com",
    "password" : "$2a$04$v1UZ4q0SCMTX695Wyowu/eVW9pT3giQivAElwH0A9BuvXktpd/qTq",
    "firstName" : null,
    "lastName" : null,
    "dateOfBirth" : null,
    "phoneNumber" : null,
    "avatar" : null
  }, {
    "id" : "3",
    "enabled" : true,
    "dateCreated" : null,
    "role" : {
      "id" : "3",
      "nama" : "CREDENTIAL_USER",
      "label" : "Credential User"
    },
    "username" : "credential",
    "email" : "credential@mail.com",
    "password" : "$2a$04$v1UZ4q0SCMTX695Wyowu/eVW9pT3giQivAElwH0A9BuvXktpd/qTq",
    "firstName" : null,
    "lastName" : null,
    "dateOfBirth" : null,
    "phoneNumber" : null,
    "avatar" : null
  }, {
    "id" : "c60bd494-1e7b-4dab-bf8c-ee34affad30c",
    "enabled" : true,
    "dateCreated" : "2017-09-04",
    "role" : {
      "id" : "2",
      "nama" : "BASIC_USER",
      "label" : "Basic User"
    },
    "username" : "ciazhar",
    "email" : "daisetsunakama@gmail.com",
    "password" : "$2a$10$4vQ0MZZFGW/KumcxHuvKyOlF8lvat3V8ATBIHlwWzc7zaEE4wLgsu",
    "firstName" : null,
    "lastName" : null,
    "dateOfBirth" : null,
    "phoneNumber" : null,
    "avatar" : null
  }, {
    "id" : "9e2e7a16-8432-47ee-b4bf-8ca6e094cf4d",
    "enabled" : false,
    "dateCreated" : "2017-09-06",
    "role" : {
      "id" : "2",
      "nama" : "BASIC_USER",
      "label" : "Basic User"
    },
    "username" : "some",
    "email" : "url@gmail.com",
    "password" : "$2a$10$LNHa4XB0Iv6pPnU4R7/OE.OGyfs86oP9A5uKYRy1.AEu0AdZKDtBm",
    "firstName" : null,
    "lastName" : null,
    "dateOfBirth" : null,
    "phoneNumber" : null,
    "avatar" : null
  }, {
    "id" : "2",
    "enabled" : true,
    "dateCreated" : null,
    "role" : {
      "id" : "2",
      "nama" : "BASIC_USER",
      "label" : "Basic User"
    },
    "username" : "user",
    "email" : "user@mail.com",
    "password" : "$2a$04$v1UZ4q0SCMTX695Wyowu/eVW9pT3giQivAElwH0A9BuvXktpd/qTq",
    "firstName" : null,
    "lastName" : null,
    "dateOfBirth" : null,
    "phoneNumber" : null,
    "avatar" : null
  } ]
}
```

## Update Profile
### Contoh Request
```
curl -d '{
    "id" : "c60bd494-1e7b-4dab-bf8c-ee34affad30c",
    "username" : "ciazhar",
    "email" : "daisetsunakama@mail.com",
    "password" : "123",
    "firstName" : "Muhammad",
    "lastName" : "Hafidz",
    "dateOfBirth" : "1997-08-28",
    "phoneNumber" : "083838718716",
    "avatar" : "INI" 
}' \
-H "Authorization: Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1MDQ4MjEwNDIsInVzZXJfbmFtZSI6ImFkbWluIiwiYXV0aG9yaXRpZXMiOlsiU1VQRVJfVVNFUiJdLCJqdGkiOiIwYWJlODVmOC04YjRiLTQ3ZjUtYmM4Mi0xZjI1NzIwZmIyNzciLCJjbGllbnRfaWQiOiJ3ZWIiLCJzY29wZSI6WyJyZWFkIiwid3JpdGUiXX0.RCOwVlrlmArpJDblFXEIOkFL45HkZkAaqhxdgzaJRB6VeP53zl1ggci1myDN1xJ0Wb14egePt9fKaZZhYwxMcxyt8IkK4GZDbHDY_bY8rlm1LNkACS7vNSRJiw_ChrYKzL5liKZxpQtJ9x79XhSmm0Oi8nMeyiLTjUw2MaxHScpMWG6wITtjjPIX9JDsuPmH4zqMA-UyAoZxFRmzla6Kf83LuXAQYz-00i1miXTn7YOKTlyptwzAPhrUtO45os7htJVirLpUBWp4n05CRnxkWRUjDVO_jwElsaayssYcyE1tVdavy_ixqtn6rSXwJCZ_phD6G0Vj0DnJcA9K2SdaFA" \
-H "Content-Type: application/json" \
-X POST http://localhost:8080/mobile/user/update;
```
### Contoh Response
```
{
  "status" : "success",
  "message" : "Successfully updated your request.",
  "data" : {
    "id" : "c60bd494-1e7b-4dab-bf8c-ee34affad30c",
    "enabled" : false,
    "dateCreated" : 1504778058116,
    "role" : {
      "id" : "2",
      "nama" : "BASIC_USER",
      "label" : "Basic User"
    },
    "username" : "ciazhar",
    "email" : "daisetsunakama@mail.com",
    "password" : "$2a$10$a/n/NVBmHjiScP1zEJpas.OQ7tk1Ce/b3DYpzLFnQgca7QXoxn5.m",
    "firstName" : "Muhammad",
    "lastName" : "Hafidz",
    "dateOfBirth" : 872726400000,
    "phoneNumber" : null,
    "avatar" : "INI"
  }
```

## Change Username
### Contoh Request
```
curl -d '{
    "id":"c60bd494-1e7b-4dab-bf8c-ee34affad30c",
    "username":"ciazhar_"
 }'\
-H "Authorization: Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1MDQ4MjE2ODAsInVzZXJfbmFtZSI6ImFkbWluIiwiYXV0aG9yaXRpZXMiOlsiU1VQRVJfVVNFUiJdLCJqdGkiOiJlMmVkNTQ5Ny1jY2IyLTRiOGQtYWM4OS0yNGVkMWVkNDg4NTkiLCJjbGllbnRfaWQiOiJ3ZWIiLCJzY29wZSI6WyJyZWFkIiwid3JpdGUiXX0.Qj7i08_ekkRWp1zD1UpqZQ_cJ9_IAxbUbAXeSHEOuVw1a4lTzBmQHyWfQEpCGUzSOUHE4-UDEZGmaK-XILJjJXQrBXpEZgzzb_CzCAQJpmhg7tbDpOBYseM8iF-tsXWYQrFJ1p74mvVUlQ5_TPjc2Dz_I_BVqMRZ1_o6iJ6i7eN28OFjL0_cabtLYcEIkwFJKbVYEMd7kLAy4-2HTo5nctD41zqJWr0Ah8r0Vai633dEirmtARMk2fT7BtwIwZKNvsGvGKTZDcLeYEqHFYufmXE6dKDLY6XlrOs61951AhfAIo3jZElFjfqHpVa20paakSDJRXAZxSOCeCSdFCV9iA" \
-H "Content-Type: application/json" \
-X POST http://localhost:8080/mobile/user/change-username;
```
### Contoh Response
```
{
  "status" : "success",
  "message" : "Successfully updated your request.",
  "data" : {
    "id" : "c60bd494-1e7b-4dab-bf8c-ee34affad30c",
    "enabled" : false,
    "dateCreated" : "2017-09-07",
    "role" : {
      "id" : "2",
      "nama" : "BASIC_USER",
      "label" : "Basic User"
    },
    "username" : "ciazhar_",
    "email" : "daisetsunakama@mail.com",
    "password" : "$2a$10$a/n/NVBmHjiScP1zEJpas.OQ7tk1Ce/b3DYpzLFnQgca7QXoxn5.m",
    "firstName" : "Muhammad",
    "lastName" : "Hafidz",
    "dateOfBirth" : "1997-08-28",
    "phoneNumber" : null,
    "avatar" : "INI"
  }
}
```

## Change Phone Number
### Contoh Request
```
curl -d '{
    "id":"c60bd494-1e7b-4dab-bf8c-ee34affad30c",
    "phone":"083838711"
 }'\
-H "Authorization: Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1MDQ4MjE2ODAsInVzZXJfbmFtZSI6ImFkbWluIiwiYXV0aG9yaXRpZXMiOlsiU1VQRVJfVVNFUiJdLCJqdGkiOiJlMmVkNTQ5Ny1jY2IyLTRiOGQtYWM4OS0yNGVkMWVkNDg4NTkiLCJjbGllbnRfaWQiOiJ3ZWIiLCJzY29wZSI6WyJyZWFkIiwid3JpdGUiXX0.Qj7i08_ekkRWp1zD1UpqZQ_cJ9_IAxbUbAXeSHEOuVw1a4lTzBmQHyWfQEpCGUzSOUHE4-UDEZGmaK-XILJjJXQrBXpEZgzzb_CzCAQJpmhg7tbDpOBYseM8iF-tsXWYQrFJ1p74mvVUlQ5_TPjc2Dz_I_BVqMRZ1_o6iJ6i7eN28OFjL0_cabtLYcEIkwFJKbVYEMd7kLAy4-2HTo5nctD41zqJWr0Ah8r0Vai633dEirmtARMk2fT7BtwIwZKNvsGvGKTZDcLeYEqHFYufmXE6dKDLY6XlrOs61951AhfAIo3jZElFjfqHpVa20paakSDJRXAZxSOCeCSdFCV9iA" \
-H "Content-Type: application/json" \
-X POST http://localhost:8080/mobile/user/change-phone;
```
### Contoh Response
```
{
  "status" : "success",
  "message" : "Successfully updated your request.",
  "data" : {
    "id" : "c60bd494-1e7b-4dab-bf8c-ee34affad30c",
    "enabled" : false,
    "dateCreated" : "2017-09-07",
    "role" : {
      "id" : "2",
      "nama" : "BASIC_USER",
      "label" : "Basic User"
    },
    "username" : "ciazhar_",
    "email" : "daisetsunakama@mail.com",
    "password" : "$2a$10$a/n/NVBmHjiScP1zEJpas.OQ7tk1Ce/b3DYpzLFnQgca7QXoxn5.m",
    "firstName" : "Muhammad",
    "lastName" : "Hafidz",
    "dateOfBirth" : "2015-01-01",
    "phoneNumber" : "083838711",
    "avatar" : "INI"
  }
}
```

## Change Date Of Birth
### Contoh Request
```
curl -d '{
    "id":"c60bd494-1e7b-4dab-bf8c-ee34affad30c",
    "birthDate":"1445-11-11"
 }'\
-H "Authorization: Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1MDQ4MjI1ODAsInVzZXJfbmFtZSI6ImFkbWluIiwiYXV0aG9yaXRpZXMiOlsiU1VQRVJfVVNFUiJdLCJqdGkiOiJlNzk3ZTQ3NC03YmRhLTRiN2YtOGYxZC02YWRkZDkzNzJkZTMiLCJjbGllbnRfaWQiOiJ3ZWIiLCJzY29wZSI6WyJyZWFkIiwid3JpdGUiXX0.CrN3ZgfI4gzOl0oU2yLB5OkojgYizURn8zjtfSh78DthGHCDkDs1R9KpkYv59nmaq6setAIyuqxCJoExhPPXgDhNgtNDqS9XComrvQWwzA8zNZD438RmUe7CA0poted-B-ef7Urx2zanSajVrAC3V4h2AdItqJAYe3sW1K3YK5f6s3Jb5m12NNWXMU5dFjT65sG180S3YylOBXcIf6qMLjKnp_z33bU5NAt3CgBhcPWqCQtgM5tPHu7rw9Sy-wja6WAFxnp0xGg9NlmzaojUVMFIR2_lmtzN8dyiOEGNAePTQiMEXJ0S-zXOykFQQ84yzlX_l_34y_LUbOAbCU1U2A" \
-H "Content-Type: application/json" \
-X POST http://localhost:8080/mobile/user/change-birthdate;
```
### Contoh Response
```
{
  "status" : "success",
  "message" : "Successfully updated your request.",
  "data" : {
    "id" : "c60bd494-1e7b-4dab-bf8c-ee34affad30c",
    "enabled" : false,
    "dateCreated" : "2017-09-07",
    "role" : {
      "id" : "2",
      "nama" : "BASIC_USER",
      "label" : "Basic User"
    },
    "username" : "ciazhar_",
    "email" : "daisetsunakama@mail.com",
    "password" : "$2a$10$a/n/NVBmHjiScP1zEJpas.OQ7tk1Ce/b3DYpzLFnQgca7QXoxn5.m",
    "firstName" : "Muhammad",
    "lastName" : "Hafidz",
    "dateOfBirth" : -16539465600000,
    "phoneNumber" : "083838711",
    "avatar" : "INI"
  }
}
```

## Change Password
### Contoh Request
```
curl -d '{
    "id":"c60bd494-1e7b-4dab-bf8c-ee34affad30c",
    "oldPassword":"123",
    "newPassword":"14045"
 }'\
-H "Authorization: Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1MDQ4MjE2ODAsInVzZXJfbmFtZSI6ImFkbWluIiwiYXV0aG9yaXRpZXMiOlsiU1VQRVJfVVNFUiJdLCJqdGkiOiJlMmVkNTQ5Ny1jY2IyLTRiOGQtYWM4OS0yNGVkMWVkNDg4NTkiLCJjbGllbnRfaWQiOiJ3ZWIiLCJzY29wZSI6WyJyZWFkIiwid3JpdGUiXX0.Qj7i08_ekkRWp1zD1UpqZQ_cJ9_IAxbUbAXeSHEOuVw1a4lTzBmQHyWfQEpCGUzSOUHE4-UDEZGmaK-XILJjJXQrBXpEZgzzb_CzCAQJpmhg7tbDpOBYseM8iF-tsXWYQrFJ1p74mvVUlQ5_TPjc2Dz_I_BVqMRZ1_o6iJ6i7eN28OFjL0_cabtLYcEIkwFJKbVYEMd7kLAy4-2HTo5nctD41zqJWr0Ah8r0Vai633dEirmtARMk2fT7BtwIwZKNvsGvGKTZDcLeYEqHFYufmXE6dKDLY6XlrOs61951AhfAIo3jZElFjfqHpVa20paakSDJRXAZxSOCeCSdFCV9iA" \
-H "Content-Type: application/json" \
-X POST http://localhost:8080/mobile/user/change-password;
```
### Contoh Response
```
{
  "status" : "success",
  "message" : "Successfully updated your request.",
  "data" : {
    "id" : "c60bd494-1e7b-4dab-bf8c-ee34affad30c",
    "enabled" : false,
    "dateCreated" : "2017-09-07",
    "role" : {
      "id" : "2",
      "nama" : "BASIC_USER",
      "label" : "Basic User"
    },
    "username" : "ciazhar_",
    "email" : "daisetsunakama@mail.com",
    "password" : "$2a$10$z.WFxXUZhlhXn2tZCJsBfO.ku2iTg08EgJNcbW352kTs4SoNThYse",
    "firstName" : "Muhammad",
    "lastName" : "Hafidz",
    "dateOfBirth" : "1445-11-11",
    "phoneNumber" : "083838711",
    "avatar" : "INI"
  }
}
```

## Change Email
### Contoh Request
```
curl -d '{
    "id":"c60bd494-1e7b-4dab-bf8c-ee34affad30c",
    "email":"ciazhar@gmail.com"
 }'\
-H "Authorization: Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1MDQ4MjY1MjksInVzZXJfbmFtZSI6InVzZXIiLCJhdXRob3JpdGllcyI6WyJDUkVERU5USUFMX1VTRVIiLCJCQVNJQ19VU0VSIl0sImp0aSI6Ijg2Nzc5NDE1LTU1MzYtNGQ5NC1iZDcwLTFhMmU2ODk0ZmZhMyIsImNsaWVudF9pZCI6IndlYiIsInNjb3BlIjpbInJlYWQiLCJ3cml0ZSJdfQ.AqwuDSaPGzvRcIvOWWCsCWW3A15vXzzMht41dkV4tPUPHPT_p1u9tI20khjM9Qic-KbXztiA11ylBEs7TY6_mtqP7C5Lag7E-7L47x3GKlXp4aVeiFOV2J6XisfYcFO0LP-5om2H6hBCxOpF1PBgFDhKzZ_5FeG0fydaTBKOUH-ulhgQ88FnxUsmJ83Zl8rFxU3j7u-QvK4kHuvjdr7bjhpvFHfCXOj9SHwTcuGQPFAWTH2T0SxfVKf5y1v0-MVtlrIbr8Dbz_1cG2O0BoDG44wSp5HLynCvsT2gzROhjBb8-iBKl2Jj55u1rcaUMnRhhJx-MvgNT8noPYqjCErgnQ" \
-H "Content-Type: application/json" \
-X POST http://localhost:8080/mobile/user/change-email;
```
### Contoh Response
```
{
  "status" : "success",
  "message" : "Successfully updated your request.",
  "data" : {
    "id" : "c60bd494-1e7b-4dab-bf8c-ee34affad30c",
    "enabled" : false,
    "dateCreated" : "2017-09-07",
    "role" : {
      "id" : "2",
      "nama" : "BASIC_USER",
      "label" : "Basic User"
    },
    "username" : "ciazhar_",
    "email" : "ciazhar@gmail.com",
    "password" : "$2a$10$z.WFxXUZhlhXn2tZCJsBfO.ku2iTg08EgJNcbW352kTs4SoNThYse",
    "firstName" : "Muhammad",
    "lastName" : "Hafidz",
    "dateOfBirth" : "1445-11-11",
    "phoneNumber" : "083838711",
    "avatar" : "INI"
  }
}
```

## Change Role
### Contoh Request
```
curl -d '{
    "id":"c60bd494-1e7b-4dab-bf8c-ee34affad30c",
    "role":"1"
 }'\
-H "Authorization: Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1MDQ4MjY2MzMsInVzZXJfbmFtZSI6ImFkbWluIiwiYXV0aG9yaXRpZXMiOlsiU1VQRVJfVVNFUiJdLCJqdGkiOiI5MGEzMzNhMy03ZDczLTRiMWMtOTUzZi1kZTdkMTQ1NWJiMGYiLCJjbGllbnRfaWQiOiJ3ZWIiLCJzY29wZSI6WyJyZWFkIiwid3JpdGUiXX0.HIMH38EnRCqBFXRLpr4z2nPh1uIlIjUcb-AnsmOTfbNcX5IyV16w5LzYJCNbo7l6HUYMXDSmpICYfuCUnp74KbPS-jYfIfjwiMeDZgFOF74ORyiTTCDxbLgNlFThT4Kaa1A-8l68SasLr7h_PPZiueU1u3VYCsrtL1tempbOMak4vMCx44KNGBeT5c6zLrxfWBN2U1Ii5dn_14EFKFHYZgMeE9039wPfolA0aKpM7Zxjo6nkUTpb2OJoMU97Dp6t7hRWKb8k0Vy8C4OLPTpc6DQ6T72MX07rGYJX3c147DNVot5jGgtPEAX5HCERMaZ2yi5aXkWs-rZXb5NYfBJa4A" \
-H "Content-Type: application/json" \
-X POST http://localhost:8080/mobile/user/change/role;
```
### Contoh Response
```
{
  "status" : "success",
  "message" : "Successfully updated your request.",
  "data" : {
    "id" : "c60bd494-1e7b-4dab-bf8c-ee34affad30c",
    "enabled" : false,
    "dateCreated" : "2017-09-07",
    "role" : {
      "id" : "2",
      "nama" : "BASIC_USER",
      "label" : "Basic User"
    },
    "username" : "ciazhar_",
    "email" : "ciazhar@gmail.com",
    "password" : "$2a$10$z.WFxXUZhlhXn2tZCJsBfO.ku2iTg08EgJNcbW352kTs4SoNThYse",
    "firstName" : "Muhammad",
    "lastName" : "Hafidz",
    "dateOfBirth" : "1445-11-11",
    "phoneNumber" : "083838711",
    "avatar" : "INI"
  }
}
```

## Change Avatar
### Contoh Request
```

```
### Contoh Response
```

```


## Delete Account
### Contoh Request
```
curl \
-H "Authorization: Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1MDQ4MjY2MzMsInVzZXJfbmFtZSI6ImFkbWluIiwiYXV0aG9yaXRpZXMiOlsiU1VQRVJfVVNFUiJdLCJqdGkiOiI5MGEzMzNhMy03ZDczLTRiMWMtOTUzZi1kZTdkMTQ1NWJiMGYiLCJjbGllbnRfaWQiOiJ3ZWIiLCJzY29wZSI6WyJyZWFkIiwid3JpdGUiXX0.HIMH38EnRCqBFXRLpr4z2nPh1uIlIjUcb-AnsmOTfbNcX5IyV16w5LzYJCNbo7l6HUYMXDSmpICYfuCUnp74KbPS-jYfIfjwiMeDZgFOF74ORyiTTCDxbLgNlFThT4Kaa1A-8l68SasLr7h_PPZiueU1u3VYCsrtL1tempbOMak4vMCx44KNGBeT5c6zLrxfWBN2U1Ii5dn_14EFKFHYZgMeE9039wPfolA0aKpM7Zxjo6nkUTpb2OJoMU97Dp6t7hRWKb8k0Vy8C4OLPTpc6DQ6T72MX07rGYJX3c147DNVot5jGgtPEAX5HCERMaZ2yi5aXkWs-rZXb5NYfBJa4A" \
-H "Content-Type: application/json" \
-X POST http://localhost:8080/mobile/user/delete?id=;
```
### Contoh Response
```
{
  "status" : "success",
  "message" : "Successfully updated your request.",
  "data" : {
    "id" : "c60bd494-1e7b-4dab-bf8c-ee34affad30c",
    "enabled" : false,
    "dateCreated" : "2017-09-07",
    "role" : {
      "id" : "2",
      "nama" : "BASIC_USER",
      "label" : "Basic User"
    },
    "username" : "ciazhar_",
    "email" : "ciazhar@gmail.com",
    "password" : "$2a$10$z.WFxXUZhlhXn2tZCJsBfO.ku2iTg08EgJNcbW352kTs4SoNThYse",
    "firstName" : "Muhammad",
    "lastName" : "Hafidz",
    "dateOfBirth" : "1445-11-11",
    "phoneNumber" : "083838711",
    "avatar" : "INI"
  }
}
```

## Create Role
### Contoh Request
```
curl -d '{
    "nama":"baruaaa",
    "label":"baru banget"
 }'\
-H "Authorization: Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1MDQ4MjY2MzMsInVzZXJfbmFtZSI6ImFkbWluIiwiYXV0aG9yaXRpZXMiOlsiU1VQRVJfVVNFUiJdLCJqdGkiOiI5MGEzMzNhMy03ZDczLTRiMWMtOTUzZi1kZTdkMTQ1NWJiMGYiLCJjbGllbnRfaWQiOiJ3ZWIiLCJzY29wZSI6WyJyZWFkIiwid3JpdGUiXX0.HIMH38EnRCqBFXRLpr4z2nPh1uIlIjUcb-AnsmOTfbNcX5IyV16w5LzYJCNbo7l6HUYMXDSmpICYfuCUnp74KbPS-jYfIfjwiMeDZgFOF74ORyiTTCDxbLgNlFThT4Kaa1A-8l68SasLr7h_PPZiueU1u3VYCsrtL1tempbOMak4vMCx44KNGBeT5c6zLrxfWBN2U1Ii5dn_14EFKFHYZgMeE9039wPfolA0aKpM7Zxjo6nkUTpb2OJoMU97Dp6t7hRWKb8k0Vy8C4OLPTpc6DQ6T72MX07rGYJX3c147DNVot5jGgtPEAX5HCERMaZ2yi5aXkWs-rZXb5NYfBJa4A" \
-H "Content-Type: application/json" \
-X POST http://localhost:8080/mobile/role/save;
```
### Contoh Response
```
{
  "status" : "success",
  "message" : "Successfully updated your request.",
  "data" : {
    "id" : "79e8a7a5-71b1-4e39-a6c1-b78384a64f86",
    "nama" : "baru",
    "label" : "baru banget"
  }
}
```

## Update Role
### Contoh Request
```
curl -d '{
    "id":"79e8a7a5-71b1-4e39-a6c1-b78384a64f86",
    "nama":"baru",
    "label":"baru pake banget"
 }'\
-H "Authorization: Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1MDQ4MjY2MzMsInVzZXJfbmFtZSI6ImFkbWluIiwiYXV0aG9yaXRpZXMiOlsiU1VQRVJfVVNFUiJdLCJqdGkiOiI5MGEzMzNhMy03ZDczLTRiMWMtOTUzZi1kZTdkMTQ1NWJiMGYiLCJjbGllbnRfaWQiOiJ3ZWIiLCJzY29wZSI6WyJyZWFkIiwid3JpdGUiXX0.HIMH38EnRCqBFXRLpr4z2nPh1uIlIjUcb-AnsmOTfbNcX5IyV16w5LzYJCNbo7l6HUYMXDSmpICYfuCUnp74KbPS-jYfIfjwiMeDZgFOF74ORyiTTCDxbLgNlFThT4Kaa1A-8l68SasLr7h_PPZiueU1u3VYCsrtL1tempbOMak4vMCx44KNGBeT5c6zLrxfWBN2U1Ii5dn_14EFKFHYZgMeE9039wPfolA0aKpM7Zxjo6nkUTpb2OJoMU97Dp6t7hRWKb8k0Vy8C4OLPTpc6DQ6T72MX07rGYJX3c147DNVot5jGgtPEAX5HCERMaZ2yi5aXkWs-rZXb5NYfBJa4A" \
-H "Content-Type: application/json" \
-X POST http://localhost:8080/mobile/role/save;
```
### Contoh Response
```
{
  "status" : "success",
  "message" : "Successfully updated your request.",
  "data" : {
    "id" : "79e8a7a5-71b1-4e39-a6c1-b78384a64f86",
    "nama" : "baru",
    "label" : "baru pake banget"
  }
}
```

## Find ALl Role
### Contoh Request
```
curl -H "Authorization: Bearer token" \
-X GET http://localhost:8080/mobile/role/all;
```
### Contoh Response
```
{
  "status" : "success",
  "message" : "Successfully updated your request.",
  "data" : [ {
    "id" : "1",
    "nama" : "ADMIN",
    "label" : "Admin"
  }, {
    "id" : "79e8a7a5-71b1-4e39-a6c1-b78384a64f86",
    "nama" : "baru",
    "label" : "baru pake banget"
  }, {
    "id" : "2",
    "nama" : "BASIC_USER",
    "label" : "Basic User"
  }, {
    "id" : "3",
    "nama" : "CREDENTIAL_USER",
    "label" : "Credential User"
  } ]
}
```

## Find One Role
### Contoh Request
```
curl -H "Authorization: Bearer token" \
-X GET http://localhost:8080/mobile/role/one?id=1;
```
### Contoh Response
```
{
  "status" : "success",
  "message" : "Successfully updated your request.",
  "data" : {
    "id" : "1",
    "nama" : "ADMIN",
    "label" : "Admin"
  }
}
```

## Delete Role
### Contoh Request
```
curl -H "Authorization: Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1MDQ4ODQ5NTAsInVzZXJfbmFtZSI6ImFkbWluIiwiYXV0aG9yaXRpZXMiOlsiU1VQRVJfVVNFUiJdLCJqdGkiOiJjZWJiYzg2NC0yMzc4LTQ3MTctYTlhMS1hMDU4NTNhMDk4OTAiLCJjbGllbnRfaWQiOiJ3ZWIiLCJzY29wZSI6WyJyZWFkIiwid3JpdGUiXX0.d4NCf3rJTcYuZCORQIa46uG7K9My6a4Q_4Q-aNk-zVMrU8d4LlL8gkoI9Yy5gO3Ipre9KzvF5dCIcF9D-miUDoZdVrOKPgfJj7KDAQMduDzcqksi7K7_BQXiZ_BeRLpAsC9ne-16VxwaiBjn6H3YHg-RtwfjmmdmNlkO7OwWpjtwRRP5RxqxUf5CaCQb9vjx7EpyYqlhedqFl7qCROqFqepkf6iROkDXtaXTwD0SHNEBiUdubS8WfGHReYVAIz4S-IWGvgxrCbBdyws7Ndtd_sbrm7_YW86gI-obWjFOcbb1dm97na8HsZ8Sc-oqVZK0VZxtZRV1v3RHMX5-_CayiA" \
-X POST http://localhost:8080/mobile/role/delete?id=f6ac1b80-b428-42df-b813-90326f4e18b4;
```
### Contoh Response
```
{
  "status" : "success",
  "message" : "Successfully updated your request.",
  "data" : {
    "id" : "79e8a7a5-71b1-4e39-a6c1-b78384a64f86",
    "nama" : "baru",
    "label" : "baru pake banget"
  }
}
```

## Create Permission
### Contoh Request
```
curl -d '{
    "nama":"baru",
    "id_role":"2",
    "label":"baru banget"
 }'\
-H "Authorization: Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1MDQ4MjY2MzMsInVzZXJfbmFtZSI6ImFkbWluIiwiYXV0aG9yaXRpZXMiOlsiU1VQRVJfVVNFUiJdLCJqdGkiOiI5MGEzMzNhMy03ZDczLTRiMWMtOTUzZi1kZTdkMTQ1NWJiMGYiLCJjbGllbnRfaWQiOiJ3ZWIiLCJzY29wZSI6WyJyZWFkIiwid3JpdGUiXX0.HIMH38EnRCqBFXRLpr4z2nPh1uIlIjUcb-AnsmOTfbNcX5IyV16w5LzYJCNbo7l6HUYMXDSmpICYfuCUnp74KbPS-jYfIfjwiMeDZgFOF74ORyiTTCDxbLgNlFThT4Kaa1A-8l68SasLr7h_PPZiueU1u3VYCsrtL1tempbOMak4vMCx44KNGBeT5c6zLrxfWBN2U1Ii5dn_14EFKFHYZgMeE9039wPfolA0aKpM7Zxjo6nkUTpb2OJoMU97Dp6t7hRWKb8k0Vy8C4OLPTpc6DQ6T72MX07rGYJX3c147DNVot5jGgtPEAX5HCERMaZ2yi5aXkWs-rZXb5NYfBJa4A" \
-H "Content-Type: application/json" \
-X POST http://localhost:8080/mobile/permission/save;
```
### Contoh Response
```
{
  "status" : "success",
  "message" : "Successfully updated your request.",
  "data" : {
    "id" : "348e405a-d822-45ca-8b83-75d9147c41a9",
    "id_role" : {
      "id" : "2",
      "nama" : "BASIC_USER",
      "label" : "Basic User"
    },
    "nama" : "baru",
    "label" : "baru banget"
  }
}
```

## Update Permission
### Contoh Request
```
curl -d '{
    "id":"876adb81-d0e8-45e5-ac28-2cfb9ea02634",
    "id_role":"2",
    "nama":"baru aja",
    "label":"baru pake banget"
 }'\
-H "Authorization: Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1MDQ4MjY2MzMsInVzZXJfbmFtZSI6ImFkbWluIiwiYXV0aG9yaXRpZXMiOlsiU1VQRVJfVVNFUiJdLCJqdGkiOiI5MGEzMzNhMy03ZDczLTRiMWMtOTUzZi1kZTdkMTQ1NWJiMGYiLCJjbGllbnRfaWQiOiJ3ZWIiLCJzY29wZSI6WyJyZWFkIiwid3JpdGUiXX0.HIMH38EnRCqBFXRLpr4z2nPh1uIlIjUcb-AnsmOTfbNcX5IyV16w5LzYJCNbo7l6HUYMXDSmpICYfuCUnp74KbPS-jYfIfjwiMeDZgFOF74ORyiTTCDxbLgNlFThT4Kaa1A-8l68SasLr7h_PPZiueU1u3VYCsrtL1tempbOMak4vMCx44KNGBeT5c6zLrxfWBN2U1Ii5dn_14EFKFHYZgMeE9039wPfolA0aKpM7Zxjo6nkUTpb2OJoMU97Dp6t7hRWKb8k0Vy8C4OLPTpc6DQ6T72MX07rGYJX3c147DNVot5jGgtPEAX5HCERMaZ2yi5aXkWs-rZXb5NYfBJa4A" \
-H "Content-Type: application/json" \
-X POST http://localhost:8080/mobile/permission/save;
```
### Contoh Response
```
{
  "status" : "success",
  "message" : "Successfully updated your request.",
  "data" : {
    "id" : "876adb81-d0e8-45e5-ac28-2cfb9ea02634",
    "id_role" : {
      "id" : "2",
      "nama" : "BASIC_USER",
      "label" : "Basic User"
    },
    "nama" : "baru aja",
    "label" : "baru pake banget"
  }
}
```

## Find ALl Permission
### Contoh Request
```
curl -H "Authorization: Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1MDQ4MjY2MzMsInVzZXJfbmFtZSI6ImFkbWluIiwiYXV0aG9yaXRpZXMiOlsiU1VQRVJfVVNFUiJdLCJqdGkiOiI5MGEzMzNhMy03ZDczLTRiMWMtOTUzZi1kZTdkMTQ1NWJiMGYiLCJjbGllbnRfaWQiOiJ3ZWIiLCJzY29wZSI6WyJyZWFkIiwid3JpdGUiXX0.HIMH38EnRCqBFXRLpr4z2nPh1uIlIjUcb-AnsmOTfbNcX5IyV16w5LzYJCNbo7l6HUYMXDSmpICYfuCUnp74KbPS-jYfIfjwiMeDZgFOF74ORyiTTCDxbLgNlFThT4Kaa1A-8l68SasLr7h_PPZiueU1u3VYCsrtL1tempbOMak4vMCx44KNGBeT5c6zLrxfWBN2U1Ii5dn_14EFKFHYZgMeE9039wPfolA0aKpM7Zxjo6nkUTpb2OJoMU97Dp6t7hRWKb8k0Vy8C4OLPTpc6DQ6T72MX07rGYJX3c147DNVot5jGgtPEAX5HCERMaZ2yi5aXkWs-rZXb5NYfBJa4A" \
-X GET http://localhost:8080/mobile/permission/all;
``
### Contoh Response
```
{
  "status" : "success",
  "message" : "Successfully updated your request.",
  "data" : [ {
    "id" : "1",
    "id_role" : {
      "id" : "1",
      "nama" : "ADMIN",
      "label" : "Admin"
    },
    "nama" : "SUPER_USER",
    "label" : "Khusus untuk admin"
  }, {
    "id" : "2",
    "id_role" : {
      "id" : "2",
      "nama" : "BASIC_USER",
      "label" : "Basic User"
    },
    "nama" : "BASIC_USER",
    "label" : "User biasa"
  }, {
    "id" : "3",
    "id_role" : {
      "id" : "2",
      "nama" : "BASIC_USER",
      "label" : "Basic User"
    },
    "nama" : "CREDENTIAL_USER",
    "label" : "User gak biasa"
  }, {
    "id" : "4",
    "id_role" : {
      "id" : "3",
      "nama" : "CREDENTIAL_USER",
      "label" : "Credential User"
    },
    "nama" : "CREDENTIAL_USER",
    "label" : "User gak biasa"
  }, {
    "id" : "876adb81-d0e8-45e5-ac28-2cfb9ea02634",
    "id_role" : {
      "id" : "2",
      "nama" : "BASIC_USER",
      "label" : "Basic User"
    },
    "nama" : "baru aja",
    "label" : "baru pake banget"
  }, {
    "id" : "348e405a-d822-45ca-8b83-75d9147c41a9",
    "id_role" : {
      "id" : "2",
      "nama" : "BASIC_USER",
      "label" : "Basic User"
    },
    "nama" : "baru",
    "label" : "baru banget"
  } ]
}
```

## Find One Permission
### Contoh Request
```
curl -H "Authorization: Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1MDQ4MjY2MzMsInVzZXJfbmFtZSI6ImFkbWluIiwiYXV0aG9yaXRpZXMiOlsiU1VQRVJfVVNFUiJdLCJqdGkiOiI5MGEzMzNhMy03ZDczLTRiMWMtOTUzZi1kZTdkMTQ1NWJiMGYiLCJjbGllbnRfaWQiOiJ3ZWIiLCJzY29wZSI6WyJyZWFkIiwid3JpdGUiXX0.HIMH38EnRCqBFXRLpr4z2nPh1uIlIjUcb-AnsmOTfbNcX5IyV16w5LzYJCNbo7l6HUYMXDSmpICYfuCUnp74KbPS-jYfIfjwiMeDZgFOF74ORyiTTCDxbLgNlFThT4Kaa1A-8l68SasLr7h_PPZiueU1u3VYCsrtL1tempbOMak4vMCx44KNGBeT5c6zLrxfWBN2U1Ii5dn_14EFKFHYZgMeE9039wPfolA0aKpM7Zxjo6nkUTpb2OJoMU97Dp6t7hRWKb8k0Vy8C4OLPTpc6DQ6T72MX07rGYJX3c147DNVot5jGgtPEAX5HCERMaZ2yi5aXkWs-rZXb5NYfBJa4A" \
-X GET http://localhost:8080/mobile/permission/one?id=1;
```
### Contoh Response
```
{
  "status" : "success",
  "message" : "Successfully updated your request.",
  "data" : {
    "id" : "1",
    "id_role" : {
      "id" : "1",
      "nama" : "ADMIN",
      "label" : "Admin"
    },
    "nama" : "SUPER_USER",
    "label" : "Khusus untuk admin"
  }
}
```

## Delete Permission
### Contoh Request
```
curl -H "Authorization: Bearer token" \
-X POST http://localhost:8080/mobile/permission/delete?id=876adb81-d0e8-45e5-ac28-2cfb9ea02634;

```
### Contoh Response
```
{
  "status" : "success",
  "message" : "Successfully updated your request.",
  "data" : {
    "id" : "876adb81-d0e8-45e5-ac28-2cfb9ea02634",
    "id_role" : {
      "id" : "2",
      "nama" : "BASIC_USER",
      "label" : "Basic User"
    },
    "nama" : "baru aja",
    "label" : "baru pake banget"
  }
}
```

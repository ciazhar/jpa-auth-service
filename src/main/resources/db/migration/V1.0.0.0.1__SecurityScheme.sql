CREATE TABLE IF NOT EXISTS role(
  id_role VARCHAR(50),
  nama_role VARCHAR(30) UNIQUE NOT NULL ,
  label_role VARCHAR(30) NOT NULL
);

CREATE TABLE IF NOT EXISTS user(
  id_user VARCHAR(50),
  email VARCHAR(30) UNIQUE NOT NULL ,
  password VARCHAR(100) NOT NULL ,
  enabled BOOLEAN DEFAULT 0,
  first_name VARCHAR(20),
  last_name VARCHAR(20),
  username VARCHAR(30) UNIQUE ,
  date_of_birth DATE,
  phone_number VARCHAR(12),
  avatar VARCHAR(255),
  date_created DATE,
  android_device_id VARCHAR(15),
  id_role int(6) DEFAULT 2
);

CREATE TABLE IF NOT EXISTS permission(
  id_permission VARCHAR(50),
  nama_permission VARCHAR(30) NOT NULL ,
  label_permission VARCHAR(30) NOT NULL,
  id_role INT(6)
);
CREATE TABLE IF NOT EXISTS role(
  id_role VARCHAR(50),
  nama_role VARCHAR(30) UNIQUE NOT NULL,
  label_role VARCHAR(30) NOT NULL
);

CREATE TABLE IF NOT EXISTS user(
  id_user VARCHAR(50),
  enabled BOOLEAN DEFAULT 0,
  date_created DATE,
  id_role VARCHAR(50) DEFAULT '2',

  username VARCHAR(30) UNIQUE,
  email VARCHAR(30) UNIQUE NOT NULL,
  password VARCHAR(100) NOT NULL,

  first_name VARCHAR(20),
  last_name VARCHAR(20),
  date_of_birth DATE,
  phone_number VARCHAR(12),
  avatar VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS permission(
  id_permission VARCHAR(50),
  nama_permission VARCHAR(30) NOT NULL,
  label_permission VARCHAR(30) NOT NULL,
  id_role INT(6)
);
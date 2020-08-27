DROP TABLE IF EXISTS tokens;
DROP TABLE IF EXISTS users;

-- Users
CREATE TABLE users (
  id VARCHAR(40) PRIMARY KEY,
  name VARCHAR(60) NOT NULL,
  email VARCHAR(60) NOT NULL,
  password VARCHAR(255) NOT NULL,
  status CHAR(1) DEFAULT 'N' NOT NULL
);

-- Registration confirmation tokens
CREATE TABLE tokens (
  id VARCHAR(255) PRIMARY KEY,
  user_id VARCHAR(40) NOT NULL,
  token_type CHAR(1) NOT NULL,
  expiration TIMESTAMP,
  CONSTRAINT FK_TOKENS_USERS FOREIGN KEY (user_id) REFERENCES users (id)
);

INSERT INTO users (id, name, email, password, status) VALUES
  ('admin', 'Administrador', 'admin@gmail.com', '$2a$10$7CUnSXjZAAmB/gtRQwpY.OELhrIS/uBSFqnPBJubQGR98Wxni.4hy', 'A');

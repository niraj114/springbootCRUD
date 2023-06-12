--USE LearningDB;

DROP TABLE IF EXISTS authorities;
DROP TABLE IF EXISTS users;

--
-- Table structure for table `users`
--

CREATE TABLE users (
  username varchar(50) NOT NULL,
  password varchar(50) NOT NULL,
  enabled smallint NOT NULL,
  PRIMARY KEY (username)
);

--
-- Inserting data for table `users`
--

INSERT INTO users 
VALUES 
('john','{noop}test123',1),
('mary','{noop}test123',1),
('susan','{noop}test123',1);


--
-- Table structure for table `authorities`
--

CREATE TABLE authorities (
  username varchar(50) NOT NULL,
  authority varchar(50) NOT NULL,
  UNIQUE (username,authority),
  CONSTRAINT FK_authorities_users FOREIGN KEY (username) REFERENCES users (username)
);

--
-- Inserting data for table `authorities`
--

INSERT INTO authorities 
VALUES 
('john','ROLE_EMPLOYEE'),
('mary','ROLE_EMPLOYEE'),
('mary','ROLE_MANAGER'),
('susan','ROLE_EMPLOYEE'),
('susan','ROLE_MANAGER'),
('susan','ROLE_ADMIN');



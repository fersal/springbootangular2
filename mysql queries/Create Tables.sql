CREATE DATABASE clearlinkjournal;

USE clearlinkjournal;

CREATE table users
(
user_id LONG PRIMARY KEY AUTO_INCREMENT,
first_name varchar(255) NOT NULL,
last_name varchar(255),
username varchar(60) NOT NULL UNIQUE,
password varchar(60) NOT NULL,
enabled BOOL NOT NULL DEFAULT true,
created_date DATE NOT NULL DEFAULT current_timestamp,
modified_date DATETIME NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp
);

CREATE Table user_roles
(
  user_role_id LONG PRIMARY KEY AUTO_INCREMENT,
  username varchar(60) NOT NULL,
  role VARCHAR(60) NOT NULL,
  UNIQUE KEY uni_username_role (role, username),
  KEY fk_username_idx (username),
  CONSTRAINT fk_username FOREIGN KEY (username) REFERENCES users (username) ON DELETE CASCADE
);


CREATE table journals
(
journal_id LONG PRIMARY KEY AUTO_INCREMENT,
journal_name varchar(255) NOT NULL,
user_id LONG NOT NULL,
created_date DATE NOT NULL DEFAULT current_timestamp,
modified_date DATETIME NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp,
FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE CASCADE
);

CREATE Table journal_entries
(
journal_entry_id LONG PRIMARY KEY AUTO_INCREMENT,
journal_id LONG NOT NULL,
positive_review varchar(1000),
goal varchar(1000),
momentum varchar(1000),
mental_toughness int NOT NULL,
willingness int NOT NULL,
determination int NOT NULL,
motivation int NOT NULL,
attitude int NOT NULL,
personal_impact int NOT NULL,
created_date DATE NOT NULL DEFAULT current_timestamp,
modified_date DATETIME NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp,
FOREIGN KEY (journal_id) REFERENCES journals (journal_id) ON DELETE CASCADE
);



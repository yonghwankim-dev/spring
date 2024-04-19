
drop table IF EXISTS member;

create TABLE member(
    id INT AUTO_INCREMENT PRIMARY KEY,
    oauthId VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    name VARCHAR(255) NOT NULL,
    imageUrl VARCHAR(255),
    role VARCHAR(255)
);

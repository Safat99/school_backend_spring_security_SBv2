CREATE TABLE IF NOT EXISTS personal_info (
    uid INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    username VARCHAR(255),
    designation ENUM('student', 'teacher', 'guardian', 'headmaster'),
    extra_info TEXT,
);



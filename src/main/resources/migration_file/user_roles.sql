CREATE TABLE user_roles (
    id INT AUTO_INCREMENT PRIMARY KEY,
    roles ENUM('admin', 'user', 'guest')
);

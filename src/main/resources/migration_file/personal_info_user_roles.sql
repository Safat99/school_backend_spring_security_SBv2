CREATE TABLE personal_info_user_roles (
    personal_info_id INT,
    user_roles_id INT,
    PRIMARY KEY (personal_info_id, user_roles_id),
    FOREIGN KEY (personal_info_id) REFERENCES personal_info (uid) ON DELETE CASCADE,
    FOREIGN KEY (user_roles_id) REFERENCES user_roles (id) ON DELETE CASCADE
);

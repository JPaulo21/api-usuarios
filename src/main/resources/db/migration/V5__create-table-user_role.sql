CREATE TABLE user_role (
   role VARCHAR(255) NOT NULL,
   user_id INT NOT NULL
);

ALTER TABLE user_role ADD CONSTRAINT fk_user_role_on_role FOREIGN KEY (`role`) REFERENCES roles (`role`);
ALTER TABLE user_role ADD CONSTRAINT fk_user_role_on_user FOREIGN KEY (user_id) REFERENCES users (id);
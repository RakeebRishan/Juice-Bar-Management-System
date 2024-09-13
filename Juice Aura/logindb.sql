create database user_juice;
 use user_juice;
CREATE TABLE user (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    email VARCHAR(255) UNIQUE,
    type VARCHAR(255),
    password VARCHAR(255)
);

-- Insert sample data into the user table
INSERT INTO user (name, email, type, password) VALUES
('Alice Smith', 'alice.smith@example.com', 'admin', 'password123'),
('Bob Johnson', 'bob.johnson@example.com', 'user', 'password456'),
('Carol Davis', 'carol.davis@example.com', 'user', 'password789');

SELECT * FROM user;
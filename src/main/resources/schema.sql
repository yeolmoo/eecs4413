

CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(10) NOT NULL
);

CREATE TABLE IF NOT EXISTS vehicle (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    vehicle_condition VARCHAR(50),
    price DECIMAL(10,2),
    stock INT NOT NULL,
    mileage DECIMAL(10,2),
    brand VARCHAR(50),
    shape VARCHAR(50),
    model VARCHAR(50),
    model_year INT,
    hot_deal BOOLEAN,
    discount VARCHAR(50),
    having_history BOOLEAN,
    vehicle_img VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS vehicle_history (
    id INT AUTO_INCREMENT PRIMARY KEY,
    description TEXT NOT NULL,
    record_date DATE NOT NULL,
    vehicle_id INT NOT NULL,
    FOREIGN KEY (vehicle_id) REFERENCES vehicle(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS customer_review (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    rating INT CHECK (rating BETWEEN 1 AND 5),
    review_text TEXT,
    review_date DATE NOT NULL,
    vehicle_id INT NOT NULL,
    FOREIGN KEY (vehicle_id) REFERENCES vehicle(id) ON DELETE CASCADE
);
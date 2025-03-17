-- Create animals table
CREATE TABLE IF NOT EXISTS animals (
    id BIGINT PRIMARY KEY,
    aname VARCHAR(255) NOT NULL,
    species VARCHAR(255) NOT NULL
);

-- Create novels table
CREATE TABLE IF NOT EXISTS novels (
    id BIGINT PRIMARY KEY,
    pyear INT NOT NULL,
    title VARCHAR(255) NOT NULL,
    publisher VARCHAR(255) NOT NULL
);

-- Create connecting table for many-to-many relationship
CREATE TABLE IF NOT EXISTS connecting (
    animalid BIGINT,
    novelid BIGINT,
    PRIMARY KEY (animalid, novelid),
    FOREIGN KEY (animalid) REFERENCES animals(id),
    FOREIGN KEY (novelid) REFERENCES novels(id)
);

-- Create users table
CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(120) NOT NULL,
    email VARCHAR(50) NOT NULL,
    role VARCHAR(20) NOT NULL
);

-- Create messages table
CREATE TABLE IF NOT EXISTS messages (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    subject VARCHAR(200) NOT NULL,
    content VARCHAR(1000) NOT NULL,
    sent_at DATETIME NOT NULL,
    user_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

-- Insert sample animals data
INSERT INTO animals (id, aname, species) VALUES
(1, 'Lajos', 'donkey'),
(2, 'Csuri', 'sparrow'),
(3, 'Böske', 'horse'),
(4, 'Bark', 'fox');

-- Insert sample novels data
INSERT INTO novels (id, pyear, title, publisher) VALUES
(1, 1937, 'A koppányi aga testamentuma', 'Dante Könyvkiadó'),
(2, 1939, 'Zsellérek', 'Királyi Magyar Egyetemi Nyomda'),
(3, 1940, 'Csi. Történet állatokról és emberekről', 'Singer és Wolfner Irodalmi Intézet Rt'),
(4, 1941, 'Öreg utakon', 'Singer és Wolfner Irodalmi Intézet Rt.'),
(5, 1942, 'Hajnal Badányban', 'Singer és Wolfner Irodalmi Intézet Rt.'),
(7, 1944, 'Egy szem kukorica', 'Új Idők Irodalmi Intézet Rt. (Singer és Wolfner)');

-- Insert sample connecting data
INSERT INTO connecting (animalid, novelid) VALUES
(1, 3),
(2, 1),
(3, 4),
(4, 2);

-- Insert admin user (password: admin123)
INSERT INTO users (username, password, email, role) VALUES
('admin', '$2a$10$rDkpBZBLo3PZ6c2lrKeppu0A1DHW0wYf0SFtVQVtebJRRbPKEYpXu', 'admin@example.com', 'ADMIN');
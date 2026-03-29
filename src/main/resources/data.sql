-- ========================
-- TABLE CREATION
-- ========================

CREATE TABLE agencies (
                          agency_id INT AUTO_INCREMENT PRIMARY KEY,
                          name VARCHAR(255),
                          contact_person_name VARCHAR(30),
                          email VARCHAR(255),
                          phone VARCHAR(15)
);

CREATE TABLE addresses (
                           address_id INT AUTO_INCREMENT PRIMARY KEY,
                           address VARCHAR(255),
                           city VARCHAR(255),
                           state VARCHAR(255),
                           zip_code VARCHAR(10)
);

CREATE TABLE agency_offices (
                                office_id INT AUTO_INCREMENT PRIMARY KEY,
                                agency_id INT,
                                office_mail VARCHAR(100),
                                office_contact_person_name VARCHAR(50),
                                office_contact_number VARCHAR(10),
                                office_address_id INT,
                                FOREIGN KEY (agency_id) REFERENCES agencies(agency_id),
                                FOREIGN KEY (office_address_id) REFERENCES addresses(address_id)
);

CREATE TABLE customers (
                           customer_id INT AUTO_INCREMENT PRIMARY KEY,
                           name VARCHAR(255),
                           email VARCHAR(255),
                           phone VARCHAR(15),
                           address_id INT,
                           FOREIGN KEY (address_id) REFERENCES addresses(address_id)
);

CREATE TABLE buses (
                       bus_id INT AUTO_INCREMENT PRIMARY KEY,
                       office_id INT,
                       registration_number VARCHAR(20),
                       capacity INT,
                       type VARCHAR(30),
                       FOREIGN KEY (office_id) REFERENCES agency_offices(office_id)
);

CREATE TABLE drivers (
                         driver_id INT AUTO_INCREMENT PRIMARY KEY,
                         license_number VARCHAR(20),
                         name VARCHAR(255),
                         phone VARCHAR(15),
                         office_id INT,
                         address_id INT,
                         FOREIGN KEY (office_id) REFERENCES agency_offices(office_id),
                         FOREIGN KEY (address_id) REFERENCES addresses(address_id)
);

CREATE TABLE routes (
                        route_id INT AUTO_INCREMENT PRIMARY KEY,
                        from_city VARCHAR(255),
                        to_city VARCHAR(255),
                        break_points INT,
                        duration INT
);

CREATE TABLE trips (
                       trip_id INT AUTO_INCREMENT PRIMARY KEY,
                       route_id INT,
                       bus_id INT,
                       boarding_address_id INT,
                       dropping_address_id INT,
                       departure_time TIMESTAMP,
                       arrival_time TIMESTAMP,
                       driver1_driver_id INT,
                       driver2_driver_id INT,
                       available_seats INT,
                       fare DECIMAL(10,2),
                       trip_date DATE,
                       FOREIGN KEY (bus_id) REFERENCES buses(bus_id),
                       FOREIGN KEY (route_id) REFERENCES routes(route_id)
);

CREATE TABLE bookings (
                          booking_id INT AUTO_INCREMENT PRIMARY KEY,
                          trip_id INT,
                          seat_number INT,
                          status VARCHAR(20),
                          FOREIGN KEY (trip_id) REFERENCES trips(trip_id)
);

CREATE TABLE payments (
                          payment_id INT AUTO_INCREMENT PRIMARY KEY,
                          booking_id INT,
                          customer_id INT,
                          amount DECIMAL(10,2),
                          payment_date TIMESTAMP,
                          payment_status VARCHAR(20),
                          FOREIGN KEY (booking_id) REFERENCES bookings(booking_id),
                          FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
);

CREATE TABLE reviews (
                         review_id INT PRIMARY KEY,
                         customer_id INT,
                         trip_id INT,
                         rating INT,
                         comment VARCHAR(500),
                         review_date TIMESTAMP,
                         FOREIGN KEY (customer_id) REFERENCES customers(customer_id),
                         FOREIGN KEY (trip_id) REFERENCES trips(trip_id)
);

-- ========================
-- SAMPLE DATA (MINIMAL)
-- ========================

INSERT INTO addresses (address, city, state, zip_code) VALUES
                                                           ('Test Address', 'Mumbai', 'MH', '400001'),
                                                           ('Test Address 2', 'Pune', 'MH', '411001');

INSERT INTO agencies (name, contact_person_name, email, phone) VALUES
    ('Test Travels', 'Amit', 'test@test.com', '9999999999');

INSERT INTO agency_offices (agency_id, office_mail, office_contact_person_name, office_contact_number, office_address_id) VALUES
    (1, 'office@test.com', 'Manager', '8888888888', 1);

INSERT INTO buses (office_id, registration_number, capacity, type) VALUES
    (1, 'MH01AB1234', 50, 'AC');

INSERT INTO routes (from_city, to_city, break_points, duration) VALUES
    ('Mumbai', 'Pune', 1, 3);

INSERT INTO trips (route_id, bus_id, boarding_address_id, dropping_address_id, departure_time, arrival_time, driver1_driver_id, driver2_driver_id, available_seats, fare, trip_date)
VALUES (1,1,1,2,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,1,1,50,500.00,CURRENT_DATE);

INSERT INTO bookings (trip_id, seat_number, status) VALUES
    (1,1,'Booked');
INSERT INTO agency_offices
(agency_id, office_mail, office_contact_person_name, office_contact_number, office_address_id)
VALUES
    (1, 'nobus@test.com', 'NoBus Manager', '9999999999', 1);

INSERT INTO buses (office_id, registration_number, capacity, type) VALUES
                                                                       (1, 'DL99TEST001', 50, 'AC'),
                                                                       (1, 'DL99TEST002', 45, 'NON-AC');


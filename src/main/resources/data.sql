-- -- ========================
-- -- CLEAN TEST DATA (WORKING)
-- -- ========================
--
-- -- Addresses (must come first)
-- INSERT INTO addresses (address, city, state, zip_code) VALUES
--                                                            ('Address 1', 'Delhi', 'Delhi', '110001'),
--                                                            ('Address 2', 'Delhi', 'Delhi', '110002'),
--                                                            ('Address 3', 'Delhi', 'Delhi', '110003'),
--                                                            ('Address 4', 'Delhi', 'Delhi', '110004'),
--                                                            ('Address 5', 'Delhi', 'Delhi', '110005');
--
-- -- Agency
-- INSERT INTO agencies (name, contact_person_name, email, phone) VALUES
--     ('Test Travels', 'Manager', 'test@test.com', '9999999999');
--
-- -- Offices
-- INSERT INTO agency_offices
-- (agency_id, office_mail, office_contact_person_name, office_contact_number, office_address_id)
-- VALUES
--     (1, 'office1@test.com', 'Manager1', '8888888881', 1),
--     (1, 'office2@test.com', 'Manager2', '8888888882', 2),
--     (1, 'office2@test.com', 'Manager2', '8888888882', 3);
--
-- -- Buses
-- INSERT INTO buses (office_id, registration_number, capacity, type) VALUES
--                                                                        (1, 'DL01BUS001', 50, 'AC'),
--                                                                        (2, 'DL01BUS002', 45, 'NON-AC');
--
-- -- =========================
-- -- DRIVERS (MAIN TEST DATA)
-- -- =========================
--
-- -- Drivers for Office 1
-- INSERT INTO drivers (license_number, name, phone, office_id, address_id) VALUES
--                                                                              ('DLTEST001', 'Driver One', '9999999991', 1, 1),
--                                                                              ('DLTEST002', 'Driver Two', '9999999992', 1, 2),
--                                                                              ('DLTEST003', 'Driver Three', '9999999993', 1, 3);
--
-- -- Drivers for Office 2
-- INSERT INTO drivers (license_number, name, phone, office_id, address_id) VALUES
--                                                                              ('DLTEST004', 'Driver Four', '9999999994', 2, 4),
---------------------------------------------------------------------------------------------------------------------------------------------------
-- ('DLTEST005', 'Driver Five', '9999999995', 2, 5);

-- ========================
-- CLEAN TEST DATA (FINAL)
-- ========================

-- Addresses
INSERT INTO addresses (address, city, state, zip_code) VALUES
                                                           ('Address 1', 'Delhi', 'Delhi', '110001'),
                                                           ('Address 2', 'Delhi', 'Delhi', '110002'),
                                                           ('Address 3', 'Delhi', 'Delhi', '110003'),
                                                           ('Address 4', 'Delhi', 'Delhi', '110004'),
                                                           ('Address 5', 'Delhi', 'Delhi', '110005');

-- Agency
INSERT INTO agencies (name, contact_person_name, email, phone) VALUES
    ('Test Travels', 'Manager', 'test@test.com', '9999999999');

-- Offices (3 offices for better testing)
INSERT INTO agency_offices
(agency_id, office_mail, office_contact_person_name, office_contact_number, office_address_id)
VALUES
    (1, 'office1@test.com', 'Manager1', '8888888881', 1),
    (1, 'office2@test.com', 'Manager2', '8888888882', 2),
    (1, 'office3@test.com', 'Manager3', '8888888883', 3);

-- =========================
-- BUSES (IMPORTANT)
-- =========================

-- Office 1 → 2 buses
INSERT INTO buses (office_id, registration_number, capacity, type) VALUES
                                                                       (1, 'DL01BUS001', 50, 'AC'),
                                                                       (1, 'DL01BUS002', 45, 'NON-AC');

-- Office 2 → 2 buses
INSERT INTO buses (office_id, registration_number, capacity, type) VALUES
                                                                       (2, 'DL02BUS001', 40, 'AC'),
                                                                       (2, 'DL02BUS002', 35, 'NON-AC');

-- Office 3 → NO buses (edge case testing)

-- =========================
-- DRIVERS
-- =========================

-- Office 1 → 3 drivers
INSERT INTO drivers (license_number, name, phone, office_id, address_id) VALUES
                                                                             ('DLTEST001', 'Driver One', '9999999991', 1, 1),
                                                                             ('DLTEST002', 'Driver Two', '9999999992', 1, 2),
                                                                             ('DLTEST003', 'Driver Three', '9999999993', 1, 3);

-- Office 2 → 2 drivers
INSERT INTO drivers (license_number, name, phone, office_id, address_id) VALUES
                                                                             ('DLTEST004', 'Driver Four', '9999999994', 2, 4),
                                                                             ('DLTEST005', 'Driver Five', '9999999995', 2, 5);

-- Office 3 → NO drivers (edge case)
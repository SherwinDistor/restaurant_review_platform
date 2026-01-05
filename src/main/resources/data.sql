-- 1. Insert 10 Addresses
INSERT INTO address (id, street_name, unit, city, state, zip_code, country) VALUES
(random_uuid(), '123 Pasta Way', 'A1', 'New York', 'NY', '10001', 'USA'),
(random_uuid(), '456 Taco Blvd', null, 'Austin', 'TX', '73301', 'USA'),
(random_uuid(), '789 Sushi St', 'Suite 200', 'San Francisco', 'CA', '94101', 'USA'),
(random_uuid(), '101 Burger Ln', null, 'Chicago', 'IL', '60601', 'USA'),
(random_uuid(), '202 Curry Rd', 'B', 'Seattle', 'WA', '98101', 'USA'),
(random_uuid(), '303 Steakhouse Dr', null, 'Dallas', 'TX', '75201', 'USA'),
(random_uuid(), '404 Dim Sum Ct', 'Level 1', 'Los Angeles', 'CA', '90001', 'USA'),
(random_uuid(), '505 Pizza Pl', null, 'Boston', 'MA', '02101', 'USA'),
(random_uuid(), '606 Vegan Ave', 'Shop 4', 'Portland', 'OR', '97201', 'USA'),
(random_uuid(), '707 BBQ Cir', null, 'Nashville', 'TN', '37201', 'USA');

-- 2. Insert 10 Operating Hours Headers
-- We'll use a temporary variable approach or subqueries to link them
INSERT INTO operating_hours (id) SELECT random_uuid() FROM system_range(1, 10);

-- 3. Insert 10 Restaurants 
-- (This mapping assumes the IDs generated above are accessible. 
-- For a static script, we link by joining the tables we just populated)
INSERT INTO restaurant (id, name, cuisine_type, phone_number, address_id, operating_hours_id)
SELECT 
    random_uuid(), 
    name, 
    cuisine, 
    phone, 
    addr.id, 
    oh.id
FROM (
    SELECT 'La Trattoria' as name, 'ITALIAN' as cuisine, '555-0101' as phone, 0 as row_idx UNION ALL
    SELECT 'Taco Heaven', 'MEXICAN', '555-0102', 1 UNION ALL
    SELECT 'Sakura Zen', 'JAPANESE', '555-0103', 2 UNION ALL
    SELECT 'The Big Bun', 'AMERICAN', '555-0104', 3 UNION ALL
    SELECT 'Spice Route', 'INDIAN', '555-0105', 4 UNION ALL
    SELECT 'Prime Cut', 'STEAKHOUSE', '555-0106', 5 UNION ALL
    SELECT 'Jade Palace', 'CHINESE', '555-0107', 6 UNION ALL
    SELECT 'Dough Boys', 'PIZZA', '555-0108', 7 UNION ALL
    SELECT 'Green Leaf', 'VEGAN', '555-0109', 8 UNION ALL
    SELECT 'Smoked Out', 'BBQ', '555-0110', 9
) data
JOIN (SELECT id, row_number() OVER () - 1 as row_idx FROM address) addr ON data.row_idx = addr.row_idx
JOIN (SELECT id, row_number() OVER () - 1 as row_idx FROM operating_hours) oh ON data.row_idx = oh.row_idx;

-- 4. Insert Time Ranges (Example: Setting 9am-9pm for all 10 restaurants for MONDAY)
INSERT INTO time_range (id, day_of_week, opening_time, closing_time, operating_hours_id)
SELECT 
    random_uuid(), 
    'MONDAY', 
    '09:00', 
    '21:00', 
    id 
FROM operating_hours;

-- Add TUESDAY for all 10
INSERT INTO time_range (id, day_of_week, opening_time, closing_time, operating_hours_id)
SELECT random_uuid(), 'TUESDAY', '09:00', '21:00', id FROM operating_hours;
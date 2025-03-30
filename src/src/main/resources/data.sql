INSERT INTO vehicle (name, description, vehicle_condition, price, stock, mileage, brand, shape, model, model_year, hot_deal, discount, having_history, vehicle_img)
VALUES ('Tesla Model S', 'Electric luxury sedan with advanced features', 'NEW', 79999.99, 5, 150.50, 'TESLA', 'SEDAN', 'Model S', 2023, TRUE, 0.1, FALSE, 'https://thedriven.io/wp-content/uploads/2021/05/model-s-splaid.jpg');

INSERT INTO vehicle (name, description, vehicle_condition, price, stock, mileage, brand, shape, model, model_year, hot_deal, discount, having_history, vehicle_img)
VALUES ('Chevrolet Bolt', 'Compact electric car with excellent range', 'USED', 34999.99, 8, 230.3, 'CHEVROLET', 'HATCHBACK', 'Bolt', 2022, FALSE, 0.0, TRUE, 'https://di-uploads-pod1.dealerinspire.com/dalebenetchevy/uploads/2023/05/2023-Chevy-Bolt-EV-1LT-Model-Left.jpg');

INSERT INTO vehicle (name, description, vehicle_condition, price, stock, mileage, brand, shape, model, model_year, hot_deal, discount, having_history, vehicle_img)
VALUES ('Tesla Model 3', 'Affordable electric sedan with advanced technology and performance', 'NEW', 42999.99, 5, 250.0, 'TESLA', 'SEDAN', 'Model 3', 2023, TRUE, 0.1, FALSE, 'https://www.tesla.com/ownersmanual/images/GUID-B5641257-9E85-404B-9667-4DA5FDF6D2E7-online-en-US.png');

INSERT INTO vehicle (name, description, vehicle_condition, price, stock, mileage, brand, shape, model, model_year, hot_deal, discount, having_history, vehicle_img)
VALUES ('Rivian R1T', 'All-electric pickup truck with off-road capabilities', 'NEW', 74999.99, 4, 150.0, 'RIVIAN', 'PICKUP', 'R1T', 2023, TRUE, 0.15, FALSE, 'https://media.rivian.com/rivian-main/image/upload/ar_32:15,c_crop/rivian-com/r1t/compare/r1t-dual_agrztl.webp');

INSERT INTO vehicle (name, description, vehicle_condition, price, stock, mileage, brand, shape, model, model_year, hot_deal, discount, having_history, vehicle_img)
VALUES ('Lucid Air', 'Luxury electric sedan with exceptional range and performance', 'USED', 89999.99, 3, 200.0, 'LUCID', 'SEDAN', 'Air', 2023, TRUE, 0.12, TRUE, 'https://file.kelleybluebookimages.com/kbb/base/evox/CP/54327/2024-Lucid-Air-front_54327_032_1799x701_L806_cropped.png');

INSERT INTO vehicle (name, description, vehicle_condition, price, stock, mileage, brand, shape, model, model_year, hot_deal, discount, having_history, vehicle_img)
VALUES ('Nissan Leaf', 'Affordable electric car with great range for city driving', 'NEW', 35999.99, 7, 50.0, 'NISSAN', 'HATCHBACK', 'Leaf', 2023, FALSE, 0.0, FALSE, 'https://images.hgmsites.net/lrg/2017-nissan-leaf-sl-hatchback-angular-front-exterior-view_100610032_l.jpg');

INSERT INTO vehicle (name, description, vehicle_condition, price, stock, mileage, brand, shape, model, model_year, hot_deal, discount, having_history, vehicle_img)
VALUES ('Hyundai Tucson', 'Compact SUV with modern features', 'USED', 28000.00, 10, 150.50, 'HYUNDAI', 'SUV', 'Tucson', 2022, FALSE, 0.0, TRUE, 'https://crdms.images.consumerreports.org/c_lfill,w_470,q_auto,f_auto/prod/cars/chrome/white/2024HYS020032_1280_01');

INSERT INTO vehicle (name, description, vehicle_condition, price, stock, mileage, brand, shape, model, model_year, hot_deal, discount, having_history, vehicle_img)
VALUES ('Volkswagen ID.4', 'All-electric SUV with advanced technology and a spacious interior.', 'NEW', 42000.00, 15, 140.00, 'VOLKSWAGEN', 'SUV', 'ID.4', 2024, TRUE, 0.25, FALSE, 'https://images.dealer.com/autodata/us/color/2025/USD50VWS091A0/0Q0Q.jpg?impolicy=downsize_bkpt&imdensity=1&w=520');

INSERT INTO vehicle (name, description, vehicle_condition, price, stock, mileage, brand, shape, model, model_year, hot_deal, discount, having_history, vehicle_img)
VALUES ('Ford Mustang Mach-E', 'A stylish and powerful all-electric SUV with cutting-edge technology.', 'NEW', 48500.00, 12, 0.00, 'FORD', 'SUV', 'Mustang Mach-E', 2024, TRUE, 0.10, FALSE, 'https://magnetis-plateforme.s3.ca-central-1.amazonaws.com/vehicle_models/images/7379c6cd423a5c9f5cb9de62a64a2d5e/631/trim_3805/color_VA/0fa2b58b210174af59d3b3b902b480d3-cc_2025FOS391942376_01_1280_VA-730x548.png?v=1741716958');

INSERT INTO vehicle_history (description, record_date, vehicle_id)
VALUES
    ('Accident reported, front bumper replaced.', '2021-03-10', 2),
    ('Repainted after previous owner had scratches on the left door.', '2020-11-25', 2),
    ('Battery replaced after original was deemed faulty.', '2022-05-05', 5),
    ('Minor accident, rear bumper repaired.', '2021-07-15', 5),
    ('Previous owner had to replace the clutch at 60,000 miles.', '2019-12-15', 5),
    ('Repaired air vehicle_conditioning system.', '2020-05-20', 2),
    ('Small scratch on front left door handle.', '2023-01-10', 7),
    ('Rear-end collision reported, trunk and taillight replaced.', '2023-06-10', 7),
    ('Hairline crack in windshield.', '2023-01-10', 7);

INSERT INTO customer_review (username, rating, review_text, review_date, vehicle_id)
VALUES
    ('john_doe', 5, 'Absolutely love the Ford Mustang! Great performance and design.', '2022-06-10', 3),
    ('jane_smith', 4, 'Nice car, but the fuel efficiency could be better.', '2022-06-15', 3),
    ('karen_123', 4, 'Great electric car with good range, but lacks some advanced features.', '2022-05-30', 2),
    ('tom_baker', 5, 'Excellent vehicle for the price! Very efficient and eco-friendly.', '2022-06-20', 1),
    ('sarah_k', 3, 'Good car but a bit underpowered for highway driving.', '2021-12-15', 5),
    ('mike_wilson', 4, 'Great value for the money. Comfortable ride and reliable.', '2022-01-20', 4),
    ('tom_baker', 4, 'Smooth ride, but could use better fuel efficiency.', '2024-02-20', 7),
    ('michael_johnson', 5, 'Great SUV for family trips!', '2024-03-05', 7),
    ('sarah_ev_fan', 5, 'Love the smooth acceleration and the quiet ride. Charging is easy and efficient!', '2024-03-10', 8),
    ('jason_miles', 4, 'Great electric SUV! Interior tech is excellent, but I wish it had faster charging speeds.', '2024-02-28', 8),
    ('phil_baker', 5, 'Compared to other electric cars, the Leaf offers great value for money.', '2023-01-22', 6),
    ('tech_savvy', 4, 'Love the tech features, but the charging network could be better.', '2024-02-28', 9),
    ('ev_enthusiast', 5, 'One of the best electric SUVs out there! Smooth acceleration and great range.', '2024-03-10', 9),
    ('phil_baker', 4, 'Great bang for your buck.', '2023-09-10', 9),
    ('anonymous_driver', 4, NULL, '2023-11-11', 6);
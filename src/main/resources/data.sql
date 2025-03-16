INSERT INTO vehicle (name, description, vehicle_condition, price, stock, mileage, brand, shape, model, model_year, hot_deal, discount, having_history, vehicle_img)
VALUES ('Tesla Model S', 'Electric luxury sedan with advanced features', 'NEW', 79999.99, 5, 150.50, 'TESLA', 'SEDAN', 'Model S', 2023, TRUE, '10% OFF', FALSE, 'https://thedriven.io/wp-content/uploads/2021/05/model-s-splaid.jpg');

INSERT INTO vehicle (name, description, vehicle_condition, price, stock, mileage, brand, shape, model, model_year, hot_deal, having_history, vehicle_img)
VALUES ('Chevrolet Bolt', 'Compact electric car with excellent range', 'USED', 34999.99, 8, 230.3, 'CHEVROLET', 'HATCHBACK', 'Bolt', 2022, FALSE, TRUE, 'https://di-uploads-pod1.dealerinspire.com/dalebenetchevy/uploads/2023/05/2023-Chevy-Bolt-EV-1LT-Model-Left.jpg');

INSERT INTO vehicle (name, description, vehicle_condition, price, stock, mileage, brand, shape, model, model_year, hot_deal, discount, having_history, vehicle_img)
VALUES ('Tesla Model 3', 'Affordable electric sedan with advanced technology and performance', 'NEW', 42999.99, 5, 250.0, 'TESLA', 'SEDAN', 'Model 3', 2023, TRUE, '10% OFF', FALSE, 'https://www.tesla.com/ownersmanual/images/GUID-B5641257-9E85-404B-9667-4DA5FDF6D2E7-online-en-US.png');

INSERT INTO vehicle (name, description, vehicle_condition, price, stock, mileage, brand, shape, model, model_year, hot_deal, discount, having_history, vehicle_img)
VALUES ('Rivian R1T', 'All-electric pickup truck with off-road capabilities', 'NEW', 74999.99, 4, 150.0, 'RIVIAN', 'PICKUP', 'R1T', 2023, TRUE, '15% OFF', FALSE, 'https://media.rivian.com/rivian-main/image/upload/ar_32:15,c_crop/rivian-com/r1t/compare/r1t-dual_agrztl.webp');

INSERT INTO vehicle (name, description, vehicle_condition, price, stock, mileage, brand, shape, model, model_year, hot_deal, discount, having_history, vehicle_img)
VALUES ('Lucid Air', 'Luxury electric sedan with exceptional range and performance', 'USED', 89999.99, 3, 200.0, 'LUCID', 'SEDAN', 'Air', 2023, TRUE, '12% OFF', TRUE, 'https://file.kelleybluebookimages.com/kbb/base/evox/CP/54327/2024-Lucid-Air-front_54327_032_1799x701_L806_cropped.png');

INSERT INTO vehicle (name, description, vehicle_condition, price, stock, mileage, brand, shape, model, model_year, hot_deal, having_history, vehicle_img)
VALUES ('Nissan Leaf', 'Affordable electric car with great range for city driving', 'NEW', 35999.99, 7, 50.0, 'NISSAN', 'HATCHBACK', 'Leaf', 2023, FALSE, FALSE, 'https://images.hgmsites.net/lrg/2017-nissan-leaf-sl-hatchback-angular-front-exterior-view_100610032_l.jpg');

INSERT INTO vehicle_history (description, record_date, vehicle_id)
VALUES
    ('Accident reported, front bumper replaced.', '2021-03-10', 2),
    ('Repainted after previous owner had scratches on the left door.', '2020-11-25', 2),
    ('Battery replaced after original was deemed faulty.', '2022-05-05', 5),
    ('Minor accident, rear bumper repaired.', '2021-07-15', 5),
    ('Previous owner had to replace the clutch at 60,000 miles.', '2019-12-15', 5),
    ('Repaired air vehicle_conditioning system.', '2020-05-20', 2);

INSERT INTO customer_review (username, rating, review_text, review_date, vehicle_id)
VALUES
    ('john_doe', 5, 'Absolutely love the Ford Mustang! Great performance and design.', '2022-06-10', 3),
    ('jane_smith', 4, 'Nice car, but the fuel efficiency could be better.', '2022-06-15', 3),
    ('karen_123', 4, 'Great electric car with good range, but lacks some advanced features.', '2022-05-30', 2),
    ('tom_baker', 5, 'Excellent vehicle for the price! Very efficient and eco-friendly.', '2022-06-20', 1),
    ('sarah_k', 3, 'Good car but a bit underpowered for highway driving.', '2021-12-15', 5),
    ('mike_wilson', 4, 'Great value for the money. Comfortable ride and reliable.', '2022-01-20', 4);








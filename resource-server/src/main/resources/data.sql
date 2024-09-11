INSERT INTO `geumsapa-resource`.products
    (id, name, code, created_at)
VALUES (1, 'Gold 99.99%', 'GOLD_99_99', '2024-09-09 12:00:00'),
       (2, 'Gold 99.9%', 'GOLD_99_9', '2024-09-09 12:00:00');

INSERT INTO `geumsapa-resource`.product_prices
(id, product_id, order_type, price_per_centigramme, comment, created_at, created_by)
VALUES (1, 1, 'SELL', 106500, '09.10 고시 가격 업데이트', '2024-09-10 10:00:00', 1),
       (2, 2, 'BUY', 124000, '09.10 고시 가격 업데이트', '2024-09-10 10:00:00', 1),
       (3, 1, 'SELL', 108500, '09.11 고시 가격 업데이트', '2024-09-11 10:00:00', 1),
       (4, 2, 'BUY', 123400, '09.11 고시 가격 업데이트', '2024-09-11 10:00:00', 1);

INSERT INTO `geumsapa-resource`.orders
(id, order_user_id, product_id, quantity, total_price, created_at, deleted_at, updated_at, delivery_address,
 order_number, order_type, status)
VALUES (1, 1, 1, 10.00, 1000000, '2023-09-15 10:00:00', NULL, NULL, '서울시 강남구 테헤란로 123', 'ORDER-BY-000001', 'BUY',
        'ORDER_COMPLETED'),
       (2, 2, 2, 5.50, 550000, '2023-09-15 11:30:00', NULL, '2023-09-15 12:30:00', '서울시 서초구 서초대로 456',
        'ORDER-SL-000002', 'SELL',
        'PAYMENT_COMPLETED'),
       (3, 1, 1, 15.75, 1575000, '2023-09-16 09:15:00', NULL, '2023-09-16 14:15:00', '경기도 성남시 분당구 판교로 789',
        'ORDER-BY-000003',
        'BUY', 'SHIPMENT_COMPLETED'),
       (4, 3, 2, 8.25, 825000, '2023-09-16 14:45:00', NULL, '2023-09-16 16:45:00', '인천시 연수구 컨벤시아대로 101',
        'ORDER-SL-000004', 'SELL',
        'REMITTANCE_COMPLETED'),
       (5, 2, 1, 20.00, 2000000, '2023-09-17 08:30:00', NULL, NULL, '대전시 유성구 대학로 234', 'ORDER-BY-000005', 'BUY',
        'ORDER_COMPLETED'),
       (6, 4, 2, 12.50, 1250000, '2023-09-17 16:00:00', NULL, '2023-09-18 10:00:00', '부산시 해운대구 해운대해변로 567',
        'ORDER-SL-000006',
        'SELL', 'RECEIPT_COMPLETED'),
       (7, 3, 1, 7.75, 775000, '2023-09-18 11:20:00', NULL, '2023-09-18 13:20:00', '광주시 서구 상무중앙로 890',
        'ORDER-BY-000007', 'BUY',
        'PAYMENT_COMPLETED'),
       (8, 1, 2, 18.30, 1830000, '2023-09-18 13:40:00', NULL, NULL, '대구시 동구 동대구로 1234', 'ORDER-SL-000008', 'SELL',
        'ORDER_COMPLETED'),
       (9, 4, 1, 9.90, 990000, '2023-09-19 10:10:00', NULL, '2023-09-19 15:10:00', '울산시 남구 삼산로 5678', 'ORDER-BY-000009',
        'BUY',
        'SHIPMENT_COMPLETED'),
       (10, 2, 2, 14.60, 1460000, '2023-09-19 15:30:00', NULL, '2023-09-19 17:30:00', '세종시 한누리대로 9101',
        'ORDER-SL-000010', 'SELL',
        'REMITTANCE_COMPLETED');

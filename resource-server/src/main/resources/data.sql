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

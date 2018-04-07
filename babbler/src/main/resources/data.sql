INSERT INTO stock_item (id, name, count) VALUES
  (0, 'зелёнка', 100501);

INSERT INTO stock_item_audit (id, item_id, date, user_id, item_name, count) VALUES
  (0, 0, '2017-12-22 10:00:00', 0, 'зелёнка', 100500);
INSERT INTO stock_item_audit (id, item_id, date, user_id, item_name, count) VALUES
  (1, 0, '2017-12-23 10:00:00', 0, 'зелёнка', 100501)
-- Categories
INSERT INTO categories (id, name, created_at, updated_at) VALUES
  (1, 'Books', NOW(), NOW()),
  (2, 'Electronics', NOW(), NOW())
ON CONFLICT (id) DO NOTHING;

-- Products
INSERT INTO products (id, name, description, price, sku, image_url, category_id, stock, created_at, updated_at) VALUES
  (1, 'Clean Code', 'A Handbook of Agile Software Craftsmanship', 34.99, 'BOOK-001', NULL, 1, 100, NOW(), NOW()),
  (2, 'Mechanical Keyboard', 'Tactile keys, RGB', 89.00, 'ELEC-101', NULL, 2, 50, NOW(), NOW())
ON CONFLICT (id) DO NOTHING;

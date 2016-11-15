-- noinspection SqlNoDataSourceInspectionForFile
-- noinspection SqlResolveForFile

INSERT INTO
  product (id, code, name, price_per_unit, unit_code, pdp_image, list_image, footnote)
VALUES
  (1, 'chicken', 'Pile', 8, 'kg', 'https://res.cloudinary.com/taze/image/upload/v1478718806/product/pdpImage/chicken.jpg',
   'https://res.cloudinary.com/taze/image/upload/v1478718277/product/listImage/chicken.jpg', '* Kilaža i cijena se podešavaju da bude cio broj piladi.'),
  (2, 'honey', 'Med', 10, 'l', 'https://res.cloudinary.com/taze/image/upload/v1478718813/product/pdpImage/honey.jpg',
   'https://res.cloudinary.com/taze/image/upload/v1478718281/product/listImage/honey.jpg', NULL),
  (3, 'potato', 'Krompir', 2, 'kg', 'https://res.cloudinary.com/taze/image/upload/v1478718817/product/pdpImage/potato.jpg',
   'https://res.cloudinary.com/taze/image/upload/v1478718782/product/listImage/potato.jpg', NULL);

INSERT INTO
  product_card (id, title, paragraph, small, image, product_id)
VALUES
  (1, 'Pilad', 'Ništa nema bolju kombinaciju ukusa i jednostavnosti kao domaće pile sa ražnja.', 'Ovo je samo privremeni tekst.',
   'https://res.cloudinary.com/taze/image/upload/v1478718837/product/cardImage/chicken.jpg', 1),
  (2, 'Med', 'Činjenica da med nema rok trajanja dovoljno govori o kvaliteti ovog proizvoda.', NULL,
   'https://res.cloudinary.com/taze/image/upload/v1478718838/product/cardImage/honey.jpg', 2),
  (3, 'Krompir', 'Pecen, kuhan, przen, svejedno kako, domaci krompir je uzitak u svakom obliku.', 'Ovo je samo privremeni tekst.',
   'https://res.cloudinary.com/taze/image/upload/v1478718838/product/cardImage/potato.jpg', 3);

INSERT INTO
  order_status (id, description)
VALUES
  ('CART', 'Narudzba je u korpi'),
  ('ORDERED', 'Narudzba je zatrazena'),
  ('CONFIRMED', 'Narudzba je potvrdjena'),
  ('PROCESSING', 'Narudzba je u obradi'),
  ('COMPLETED', 'Narudzba je uspjesno obradjena'),
  ('FAILED', 'Narudzba je bila neuspjesna'),
  ('CANCELLED', 'Narudzba je otkazana');

INSERT INTO
  page (id, path, parent_page_id)
VALUES
  (1, '/', NULL),
  (2, '/about-us', 1),
  (3, '/gallery', 1),
  (4, '/products', 1),
  (41, '/products/chicken', 4),
  (42, '/products/honey', 4),
  (43, '/products/potato', 4),
  (5, '/contact', 1),
  (6, '/cart', 1),
  (7, '/confirmed-order', 1),
  (101, '/admin', NULL);

INSERT INTO
  stage (id, header, image, page_id)
VALUES
  (1, 'Pocetna', 'https://res.cloudinary.com/taze/image/upload/v1478817153/stage/root.jpg', 1),
  (2, 'O nama', 'https://res.cloudinary.com/taze/image/upload/v1478817152/stage/about-us.jpg', 2),
  (3, 'Galerija', 'https://res.cloudinary.com/taze/image/upload/v1478817153/stage/gallery.jpg', 3),
  (4, 'Proizvodi', 'https://res.cloudinary.com/taze/image/upload/v1478817153/stage/products.jpg', 4),
  (41, 'Pile', 'https://res.cloudinary.com/taze/image/upload/v1478817189/stage/product/chicken.jpg', 41),
  (42, 'Med', 'https://res.cloudinary.com/taze/image/upload/v1478817207/stage/product/honey.jpg', 42),
  (43, 'Krompir', 'https://res.cloudinary.com/taze/image/upload/v1478817238/stage/product/potato.jpg', 43),
  (5, 'Kontakt', 'https://res.cloudinary.com/taze/image/upload/v1478817152/stage/contact.jpg', 5),
  (6, 'Korpa', 'https://res.cloudinary.com/taze/image/upload/v1478817151/stage/cart.jpg', 6),
  (7, 'Potvrdjena narudzba', 'https://res.cloudinary.com/taze/image/upload/v1478817152/stage/confirmed-order.jpg', 7),
  (101, 'Admin', 'https://res.cloudinary.com/taze/image/upload/v1479216812/admin.jpg', 101);

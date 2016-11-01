insert into
  product (id, code, name, price_per_unit, unit_code, pdp_image, list_image, footnote)
values
  (1, 'chicken', 'Pile', 8, 'kg', '/img/products/chicken-pdp.jpg', '/img/products/chicken-list.jpg', '* Kilaža i cijena se podešavaju da bude cio broj piladi.'),
  (2, 'honey', 'Med', 10, 'l', '/img/products/honey-pdp.jpg', '/img/products/honey-list.jpg', null),
  (3, 'horse', 'Konj', 12, 'kg', '/img/products/horse-pdp.jpg', '/img/products/horse-list.jpg', null);

insert into
  product_card (id, title, paragraph, small, product_id)
values
  (1, 'Pilad', 'Ništa nema bolju kombinaciju ukusa i jednostavnosti kao domaće pile sa ražnja.', 'Ovo je samo privremeni tekst.', 1),
  (2, 'Med', 'Činjenica da med nema rok trajanja dovoljno govori o kvaliteti ovog proizvoda.', null, 2),
  (3, 'Konji', 'Kažu da konjsko meso daje snagu i energiju za cijeli dan.', 'Ovo je samo privremeni tekst.', 3);

insert into
  order_status (value, description)
values
  ('CART', 'Narudzba je u korpi'),
  ('ORDERED', 'Narudzba je zatrazena'),
  ('CONFIRMED', 'Narudzba je potvrdjena'),
  ('PROCESSING', 'Narudzba je u obradi'),
  ('COMPLETED', 'Narudzba je uspjesno obradjena'),
  ('FAILED', 'Narudzba je bila neuspjesna'),
  ('CANCELLED', 'Narudzba je otkazana');

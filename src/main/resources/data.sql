-- noinspection SqlNoDataSourceInspectionForFile
-- noinspection SqlResolveForFile

insert into
  product (id, code, name, price_per_unit, unit_code, pdp_image, list_image, footnote)
values
  (1, 'chicken', 'Pile', 8, 'kg', 'https://res.cloudinary.com/taze/image/upload/v1478718806/product/pdpImage/chicken.jpg', 'https://res.cloudinary.com/taze/image/upload/v1478718277/product/listImage/chicken.jpg', '* Kilaža i cijena se podešavaju da bude cio broj piladi.'),
  (2, 'honey', 'Med', 10, 'l', 'https://res.cloudinary.com/taze/image/upload/v1478718813/product/pdpImage/honey.jpg', 'https://res.cloudinary.com/taze/image/upload/v1478718281/product/listImage/honey.jpg', null),
  (3, 'potato', 'Krompir', 2, 'kg', 'https://res.cloudinary.com/taze/image/upload/v1478718817/product/pdpImage/potato.jpg', 'https://res.cloudinary.com/taze/image/upload/v1478718782/product/listImage/potato.jpg', null);

insert into
  product_card (id, title, paragraph, small, image, product_id)
values
  (1, 'Pilad', 'Ništa nema bolju kombinaciju ukusa i jednostavnosti kao domaće pile sa ražnja.', 'Ovo je samo privremeni tekst.', 'https://res.cloudinary.com/taze/image/upload/v1478718837/product/cardImage/chicken.jpg', 1),
  (2, 'Med', 'Činjenica da med nema rok trajanja dovoljno govori o kvaliteti ovog proizvoda.', null, 'https://res.cloudinary.com/taze/image/upload/v1478718838/product/cardImage/honey.jpg', 2),
  (3, 'Krompir', 'Pecen, kuhan, przen, svejedno kako, domaci krompir je uzitak u svakom obliku.', 'Ovo je samo privremeni tekst.', 'https://res.cloudinary.com/taze/image/upload/v1478718838/product/cardImage/potato.jpg', 3);

insert into
  order_status (id, description)
values
  ('CART', 'Narudzba je u korpi'),
  ('ORDERED', 'Narudzba je zatrazena'),
  ('CONFIRMED', 'Narudzba je potvrdjena'),
  ('PROCESSING', 'Narudzba je u obradi'),
  ('COMPLETED', 'Narudzba je uspjesno obradjena'),
  ('FAILED', 'Narudzba je bila neuspjesna'),
  ('CANCELLED', 'Narudzba je otkazana');

insert into
  page (id, code, path, parent_page_id)
values
  (1, 'root', '/', null),
  (2, 'about-us', '/about-us', 1),
  (3, 'gallery', '/gallery', 1),
  (4, 'products', '/products', 1),
  (5, 'contact', '/contact', 1),
  (6, 'cart', '/cart', 1),
  (7, 'confirmed-order', '/confirmed-order', 1);

insert into
  stage (id, header, image_url, page_id)
values
  (1, 'Pocetna', '/img/root.jpg', 1),
  (2, 'O nama', '/img/about-us.jpg', 2),
  (3, 'Galerija', '/img/gallery.jpg', 3),
  (4, 'Proizvodi', '/img/products.jpg', 4),
  (5, 'Kontakt', '/img/contact.jpg', 5),
  (6, 'Korpa', '/img/cart.jpg', 6),
  (7, 'Potvrdjena narudzba', '/img/confirmed-order.jpg', 7);

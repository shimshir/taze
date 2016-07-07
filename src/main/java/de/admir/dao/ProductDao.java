package de.admir.dao;

import de.admir.model.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ProductDao {
    private static final List<Product> products = new ArrayList<>();

    static {
        Product chicken = new Product();
        chicken.setUuid(UUID.randomUUID().toString());
        chicken.setCode("chicken");
        chicken.setName("Pile");
        chicken.setPricePerUnit(new BigDecimal("8"));
        chicken.setUnitCode("kg");
        chicken.setPdpImage("/img/products/chicken-pdp.jpg");
        chicken.setListImage("/img/products/chicken-list.jpg");
        Product honey = new Product();
        honey.setUuid(UUID.randomUUID().toString());
        honey.setCode("honey");
        honey.setName("Med");
        honey.setPricePerUnit(new BigDecimal("10"));
        honey.setUnitCode("l");
        honey.setPdpImage("/img/products/honey-pdp.jpg");
        honey.setListImage("/img/products/honey-list.jpg");
        Product horse = new Product();
        horse.setUuid(UUID.randomUUID().toString());
        horse.setCode("horse");
        horse.setName("Konj");
        horse.setPricePerUnit(new BigDecimal("12"));
        horse.setUnitCode("kg");
        horse.setPdpImage("/img/products/horse-pdp.jpg");
        horse.setListImage("/img/products/horse-list.jpg");
        products.add(chicken);
        products.add(honey);
        products.add(horse);
    }


    public static Optional<Product> findProductByCode(String code) {
        return products.stream().filter(product -> code.equals(product.getCode())).findFirst();
    }

    public static Optional<Product> findProductByUuid(String uuid) {
        return products.stream().filter(product -> uuid.equals(product.getUuid())).findFirst();
    }

    public static List<Product> findAllProducts() {
        return products;
    }
}

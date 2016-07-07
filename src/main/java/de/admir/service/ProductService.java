package de.admir.service;

import de.admir.dao.ProductDao;
import de.admir.model.Product;

public class ProductService {
    public static Product getProductByCode(String code) {
        return ProductDao.findProductByCode(code).orElse(null);
    }
}

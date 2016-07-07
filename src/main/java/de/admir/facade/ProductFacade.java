package de.admir.facade;

import de.admir.model.ProductDto;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class ProductFacade {
    private static final Map<String, ProductDto> productMap = new HashMap<>();

    static {
        ProductDto chicken = new ProductDto();
        chicken.setCode("chicken");
        chicken.setName("Pile");
        chicken.setPricePerUnit(new BigDecimal("8"));
        chicken.setUnitCode("kg");
        chicken.setPdpImage("/img/products/chicken-pdp.jpg");
        chicken.setListImage("/img/products/chicken-list.jpg");
        ProductDto honey = new ProductDto();
        honey.setCode("honey");
        honey.setName("Med");
        honey.setPricePerUnit(new BigDecimal("10"));
        honey.setUnitCode("l");
        honey.setPdpImage("/img/products/honey-pdp.jpg");
        honey.setListImage("/img/products/honey-list.jpg");
        ProductDto horse = new ProductDto();
        horse.setCode("horse");
        horse.setName("Konj");
        horse.setPricePerUnit(new BigDecimal("12"));
        horse.setUnitCode("kg");
        horse.setPdpImage("/img/products/horse-pdp.jpg");
        horse.setListImage("/img/products/horse-list.jpg");
        productMap.put("chicken", chicken);
        productMap.put("honey", honey);
        productMap.put("horse", horse);
    }

    public static ProductDto getProductByCode(String code) {
        return productMap.get(code);
    }
}

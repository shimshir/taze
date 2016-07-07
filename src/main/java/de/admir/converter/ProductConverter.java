package de.admir.converter;

import de.admir.model.Product;
import de.admir.model.ProductDto;

import java.util.UUID;

public class ProductConverter {

    public static ProductDto convertToDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(UUID.fromString(product.getUuid()));
        productDto.setCode(product.getCode());
        productDto.setName(product.getName());
        productDto.setListImage(product.getListImage());
        productDto.setPdpImage(product.getPdpImage());
        productDto.setPricePerUnit(product.getPricePerUnit());
        productDto.setUnitCode(product.getUnitCode());
        return productDto;
    }
}
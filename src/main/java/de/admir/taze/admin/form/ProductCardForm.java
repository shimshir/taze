package de.admir.taze.admin.form;

import com.fasterxml.jackson.annotation.JsonIgnore;

import de.admir.taze.model.product.ProductCard;

public class ProductCardForm {
    @JsonIgnore
    private ProductCard productCard = new ProductCard();

    private String productCode;
    private String imageData;

    @JsonIgnore
    public ProductCard getProductCard() {
        return productCard;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getTitle() {
        return productCard.getTitle();
    }

    public void setTitle(String title) {
        productCard.setTitle(title);
    }

    public String getParagraph() {
        return productCard.getParagraph();
    }

    public void setParagraph(String paragraph) {
        productCard.setParagraph(paragraph);
    }

    public String getSmall() {
        return productCard.getSmall();
    }

    public void setSmall(String small) {
        productCard.setSmall(small);
    }

    public String getImage() {
        return productCard.getImage();
    }

    public void setImage(String image) {
        productCard.setImage(image);
    }

    public String getImageData() {
        return imageData;
    }

    public void setImageData(String imageData) {
        this.imageData = imageData;
    }
}

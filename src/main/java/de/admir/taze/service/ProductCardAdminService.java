package de.admir.taze.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import de.admir.taze.admin.form.ProductCardForm;
import de.admir.taze.model.product.ProductCard;
import de.admir.taze.repository.ProductCardRepository;
import de.admir.taze.repository.ProductRepository;
import de.admir.taze.util.Error;
import de.admir.taze.util.Xor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NestedRuntimeException;
import org.springframework.stereotype.Service;

@Service
public class ProductCardAdminService {
    @Autowired
    private Cloudinary cloudinary;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductCardRepository productCardRepository;

    // TODO: Write unit test
    public Xor<Error, ProductCard> createProductCard(ProductCardForm requestProductCardForm) {
        return Xor.fromOptional(
                productRepository.findByCode(requestProductCardForm.getProductCode()),
                new Error("Product not found for productCode: " + requestProductCardForm.getProductCode()))
                .mapRight(product -> {
                    requestProductCardForm.getProductCard().setProduct(product);
                    return requestProductCardForm;
                })
                .flatMapRight(productCardForm -> Xor.catchNonFatal(
                        () -> cloudinary
                                .uploader()
                                .upload(productCardForm.getImageData(), ObjectUtils.asMap("public_id", productCardForm.getProductCode()))
                        ).mapLeft(Error::new)
                ).mapRight(imageUploadResult -> {
                    requestProductCardForm.setImage((String) imageUploadResult.get("secure_url"));
                    return requestProductCardForm;
                })
                .flatMapRight(productCardForm -> Xor.catchNonFatal(() ->
                        productCardRepository.save(productCardForm.getProductCard()))
                        .mapLeft(e ->
                                NestedRuntimeException.class.isAssignableFrom(e.getClass()) ?
                                        new Error(((NestedRuntimeException) e).getMostSpecificCause().getMessage()) :
                                        new Error(e)
                        )
                );
    }
}

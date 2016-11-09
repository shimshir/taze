package de.admir.taze.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import de.admir.taze.admin.form.ProductCardForm;
import de.admir.taze.model.product.ProductCard;
import de.admir.taze.repository.ProductCardRepository;
import de.admir.taze.repository.ProductRepository;
import de.admir.taze.util.Error;
import de.admir.taze.util.Xor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.NestedRuntimeException;
import org.springframework.stereotype.Service;

@Service
public class ProductCardAdminService {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private Cloudinary cloudinary;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductCardRepository productCardRepository;

    @Value("${taze.cloudinary.folder.product.cardImage}")
    private String imageFolder;

    // TODO: Write unit test
    public Xor<Error, ProductCard> createProductCard(ProductCardForm requestProductCardForm) {
        final String productCode = requestProductCardForm.getProductCode();
        return Xor.fromOptional(
                productRepository.findByCode(productCode),
                () -> {
                    LOG.warn(String.format("Product: %s not found!", productCode));
                    return new Error("Product not found for productCode: " + productCode);
                })
                .mapRight(product -> {
                    requestProductCardForm.getProductCard().setProduct(product);
                    return requestProductCardForm;
                })
                .flatMapRight(productCardForm -> Xor.catchNonFatal(() -> {
                            LOG.info("Uploading cardImage for product: " + productCode);
                            return cloudinary
                                    .uploader()
                                    .upload(productCardForm.getImageData(), ObjectUtils.asMap("public_id",
                                            imageFolder + '/' + productCode));
                        }).mapLeft(e -> {
                            LOG.warn(String.format("cardImage upload for product: %s failed", productCode), e);
                            return new Error(e);
                        })
                ).mapRight(imageUploadResult -> {
                    String imageUrl = (String) imageUploadResult.get("secure_url");
                    LOG.info(String.format("cardImage uploaded to %s", imageUrl));
                    requestProductCardForm.setImage((String) imageUploadResult.get("secure_url"));
                    return requestProductCardForm;
                })
                .flatMapRight(productCardForm -> Xor.catchNonFatal(() ->
                        productCardRepository.save(productCardForm.getProductCard()))
                        .map(e -> {
                                    LOG.warn(String.format("Failed to save productCard for product: %s to DB", productCode), e);
                                    return NestedRuntimeException.class.isAssignableFrom(e.getClass()) ?
                                            new Error(((NestedRuntimeException) e).getMostSpecificCause().getMessage()) :
                                            new Error(e);
                                },
                                productCard -> {
                                    LOG.info(String.format("Saved productCard for product: %s to DB", productCode));
                                    return productCard;
                                }
                        )
                );
    }
}

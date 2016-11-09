package de.admir.taze.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import de.admir.taze.admin.form.ProductForm;
import de.admir.taze.model.product.Product;
import de.admir.taze.repository.ProductRepository;
import de.admir.taze.util.Error;
import de.admir.taze.util.Xor;

import static de.admir.taze.util.TazeUtils.assertNotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.NestedRuntimeException;
import org.springframework.stereotype.Service;

@Service
public class ProductAdminService {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private Cloudinary cloudinary;
    @Autowired
    private ProductRepository productRepository;

    @Value("${taze.cloudinary.folder.product.pdpImage}")
    private String pdpImageFolder;

    @Value("${taze.cloudinary.folder.product.listImage}")
    private String listImageFolder;

    // TODO: Write unit test
    public Xor<Error, Product> createProduct(ProductForm requestProductForm) {
        final String productCode = requestProductForm.getCode();

        return Xor.catchNonFatal(() -> {
            assertNotNull(productCode, "ProductCode must not be null");
            LOG.info("Uploading pdpImage for product: " + productCode);
            return cloudinary
                    .uploader()
                    .upload(requestProductForm.getPdpImageData(), ObjectUtils.asMap("public_id",
                            pdpImageFolder + '/' + productCode));
        }).map(
                e -> {
                    LOG.warn(String.format("pdpImage upload for product: %s failed", productCode), e);
                    return new Error(e);
                },
                pdpImageUploadResult -> {
                    String pdpImageUrl = (String) pdpImageUploadResult.get("secure_url");
                    LOG.info(String.format("pdpImage uploaded to %s", pdpImageUrl));
                    requestProductForm.setPdpImage(pdpImageUrl);
                    return requestProductForm;
                }
        ).flatMapRight(productForm -> Xor.catchNonFatal(() -> {
            LOG.info("Uploading listImage for product: " + productCode);
            return cloudinary
                    .uploader()
                    .upload(requestProductForm.getListImageData(), ObjectUtils.asMap("public_id",
                            listImageFolder + '/' + productCode));
        }).map(
                e -> {
                    LOG.warn(String.format("listImage upload for product: %s failed", productCode), e);
                    return new Error(e);
                },
                listImageUploadResult -> {
                    String listImageUrl = (String) listImageUploadResult.get("secure_url");
                    LOG.info(String.format("listImage uploaded to %s", listImageUrl));
                    requestProductForm.setListImage(listImageUrl);
                    return requestProductForm;
                }
        )).flatMapRight(productForm -> Xor.catchNonFatal(() ->
                productRepository.save(productForm.getProduct()))
                .map(e -> {
                            LOG.warn(String.format("Failed to save product: %s to DB", productCode), e);
                            return NestedRuntimeException.class.isAssignableFrom(e.getClass()) ?
                                    new Error(((NestedRuntimeException) e).getMostSpecificCause().getMessage()) :
                                    new Error(e);
                        },
                        product -> {
                            LOG.info(String.format("Saved product: %s to DB", productCode));
                            return product;
                        }
                )
        );
    }
}

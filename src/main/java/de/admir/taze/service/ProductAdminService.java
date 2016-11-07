package de.admir.taze.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import de.admir.taze.admin.form.ProductForm;
import de.admir.taze.model.product.Product;
import de.admir.taze.repository.ProductRepository;
import de.admir.taze.util.Error;
import de.admir.taze.util.Xor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NestedRuntimeException;
import org.springframework.stereotype.Service;

@Service
public class ProductAdminService {
    @Autowired
    private Cloudinary cloudinary;
    @Autowired
    private ProductRepository productRepository;

    public Xor<Error, Product> createProduct(ProductForm requestProductForm) {

        return Xor.catchNonFatal(() ->
                cloudinary
                        .uploader()
                        .upload(requestProductForm.getPdpImageData(), ObjectUtils.asMap("public_id",
                                "productPdpImages/" + requestProductForm.getCode())))
                .map(
                        Error::new,
                        pdpImageUploadResult -> {
                            requestProductForm.setPdpImage((String) pdpImageUploadResult.get("secure_url"));
                            return requestProductForm;
                        }
                ).flatMapRight(productForm -> Xor.catchNonFatal(() ->
                        cloudinary
                                .uploader()
                                .upload(requestProductForm.getListImageData(), ObjectUtils.asMap("public_id",
                                        "productListImages/" + requestProductForm.getCode())))
                        .map(
                                Error::new,
                                listImageUploadResult -> {
                                    requestProductForm.setListImage((String) listImageUploadResult.get("secure_url"));
                                    return requestProductForm;
                                }
                        ))
                .flatMapRight(productForm -> Xor.catchNonFatal(() ->
                        productRepository.save(requestProductForm.getProduct()))
                        .mapLeft(e ->
                                NestedRuntimeException.class.isAssignableFrom(e.getClass()) ?
                                        new Error(((NestedRuntimeException) e).getMostSpecificCause().getMessage()) :
                                        new Error(e)
                        )
                );
    }
}

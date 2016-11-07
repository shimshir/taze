package de.admir.taze.controller.admin;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import de.admir.taze.admin.form.ProductForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.*;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

import static de.admir.taze.Constants.API_ADMIN_CONTEXT_PATH;

@RestController
@RequestMapping(path = API_ADMIN_CONTEXT_PATH + "/products")
public class ProductAdminController {
    @Autowired
    private Cloudinary cloudinary;

    @RequestMapping(method = POST)
    public ResponseEntity<?> postProductForm(@RequestBody ProductForm productForm) throws IOException {
        Map pdpImageUploadResult = cloudinary.uploader().upload(productForm.getPdpImageData(), ObjectUtils.asMap("public_id", "productPdpImages/" + productForm.getCode()));
        Map listImageUploadResult = cloudinary.uploader().upload(productForm.getListImageData(), ObjectUtils.asMap("public_id", "productListImages/" + productForm.getCode()));

        return ResponseEntity.ok(ObjectUtils.asMap(
                "pdpImageUploadResult", pdpImageUploadResult,
                "listImageUploadResult", listImageUploadResult
        ));
    }
}

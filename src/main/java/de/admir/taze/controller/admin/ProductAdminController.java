package de.admir.taze.controller.admin;

import de.admir.taze.admin.form.ProductForm;
import de.admir.taze.model.product.Product;
import de.admir.taze.service.ProductAdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

import static de.admir.taze.Constants.API_ADMIN_CONTEXT_PATH;

@RestController
@RequestMapping(path = API_ADMIN_CONTEXT_PATH + "/products")
public class ProductAdminController {
    @Autowired
    private ProductAdminService productAdminService;
    @Autowired
    private RepositoryEntityLinks entityLinks;

    @RequestMapping(method = POST)
    public ResponseEntity<?> postProductForm(@RequestBody ProductForm productForm) {
        return productAdminService.createProduct(productForm).fold(
                error -> ResponseEntity.badRequest().body(error),
                product -> {
                    Resource<Product> responseProductResource = new Resource<>(product);
                    Link productEntityLink = entityLinks.linkToSingleResource(product);
                    responseProductResource.add(productEntityLink.withSelfRel());
                    responseProductResource.add(productEntityLink);
                    return ResponseEntity.created(URI.create(productEntityLink.getHref())).body(responseProductResource);
                });
    }
}

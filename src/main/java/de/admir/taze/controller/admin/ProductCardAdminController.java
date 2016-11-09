package de.admir.taze.controller.admin;

import de.admir.taze.admin.form.ProductCardForm;
import de.admir.taze.model.product.ProductCard;
import de.admir.taze.service.ProductCardAdminService;
import de.admir.taze.util.TazeUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

import static de.admir.taze.Constants.API_ADMIN_CONTEXT_PATH;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(path = API_ADMIN_CONTEXT_PATH + "/productCards")
public class ProductCardAdminController {
    @Autowired
    private ProductCardAdminService productCardAdminService;
    @Autowired
    private RepositoryEntityLinks entityLinks;

    @RequestMapping(method = POST)
    public ResponseEntity<?> postProductCardForm(@RequestBody ProductCardForm productCardForm) {
        return productCardAdminService.createProductCard(productCardForm).fold(
                error -> ResponseEntity.badRequest().body(error),
                productCard -> {
                    Resource<ProductCard> responseProductCardResource = new Resource<>(productCard);
                    Link productCardEntityLink = entityLinks.linkToSingleResource(productCard);
                    responseProductCardResource.add(productCardEntityLink.withSelfRel());
                    responseProductCardResource.add(productCardEntityLink);
                    responseProductCardResource.add(entityLinks.linkToSingleResource(productCard.getProduct()));
                    return ResponseEntity.created(
                            URI.create(
                                    TazeUtils.removeTemplateVariables(productCardEntityLink.getHref())
                            )
                    ).body(responseProductCardResource);
                });
    }
}

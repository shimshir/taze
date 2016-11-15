package de.admir.taze.controller.admin;

import de.admir.taze.admin.form.PageForm;
import de.admir.taze.model.cms.Page;
import de.admir.taze.service.PageAdminService;
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
@RequestMapping(path = API_ADMIN_CONTEXT_PATH + "/pages")
public class PageAdminController {

    @Autowired
    private PageAdminService pageAdminService;
    @Autowired
    private RepositoryEntityLinks entityLinks;

    @RequestMapping(method = POST)
    public ResponseEntity<?> postPageForm(@RequestBody PageForm pageForm) {
        return pageAdminService.createPage(pageForm).fold(
                error -> ResponseEntity.badRequest().body(error),
                page -> {
                    Resource<Page> responsePageResource = new Resource<>(page);
                    Link pageEntityLink = entityLinks.linkToSingleResource(page);
                    responsePageResource.add(pageEntityLink.withSelfRel());
                    responsePageResource.add(pageEntityLink);
                    if (page.getParentPage() != null)
                        responsePageResource.add(entityLinks.linkToSingleResource(page.getParentPage()).withRel("parentPage"));
                    return ResponseEntity.created(
                            URI.create(
                                    TazeUtils.removeTemplateVariables(pageEntityLink.getHref())
                            )
                    ).body(responsePageResource);
                });
    }
}

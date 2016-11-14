package de.admir.taze.service;

import de.admir.taze.admin.form.PageForm;
import de.admir.taze.model.cms.Page;
import de.admir.taze.model.cms.Stage;
import de.admir.taze.repository.cms.PageRepository;
import de.admir.taze.util.Error;
import de.admir.taze.util.Xor;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NestedRuntimeException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class PageAdminService {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private PageRepository pageRepository;
    @Autowired
    private StageAdminService stageAdminService;

    // TODO: Write unit test
    public Xor<Error, Page> createPage(PageForm requestPageForm) {

        List<Xor<Error, Stage>> stagesCreateResults = requestPageForm.getStages().stream().map(stageForm -> stageAdminService.createStage(stageForm)).collect(Collectors.toList());

        List<Error> errors = stagesCreateResults.stream().filter(Xor::isLeft).map(Xor::getLeft).collect(Collectors.toList());

        Xor<Error, List<Stage>> stagesCreateResultsXor = (CollectionUtils.isNotEmpty(errors) ?
                Xor.left(errors.stream().reduce(new Error("Error while creating stages for page"), Error::addNestedError)) :
                Xor.right(stagesCreateResults.stream().filter(Xor::isRight).map(Xor::getRight).collect(Collectors.toList())));

        return stagesCreateResultsXor.mapRight(createdStages ->
                {
                    requestPageForm.getPage().setStages(createdStages);
                    return requestPageForm;
                }
        ).flatMapRight(pageForm -> Xor.catchNonFatal(() ->
                {
                    return pageRepository.save(pageForm.getPage());
                }
        ).map(
                e -> {
                    LOG.warn(String.format("Failed to save page: %s to DB", pageForm.getPath()), e);
                    return NestedRuntimeException.class.isAssignableFrom(e.getClass()) ?
                            new Error(((NestedRuntimeException) e).getMostSpecificCause().getMessage()) :
                            new Error(e);
                },
                page -> {
                    LOG.info(String.format("Saved page: %s to DB", page.getPath()));
                    return page;
                }
        ));
    }
}

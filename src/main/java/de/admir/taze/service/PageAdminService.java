package de.admir.taze.service;

import de.admir.taze.admin.form.PageForm;
import de.admir.taze.model.cms.Page;
import de.admir.taze.model.cms.Stage;
import de.admir.taze.repository.cms.PageRepository;
import de.admir.taze.util.Error;
import de.admir.taze.util.Xor;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NestedRuntimeException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
        return Xor.<Error, PageForm>right(requestPageForm).flatMapRight(pageForm -> {
                    if (pageForm.getPath() == null)
                        return Xor.left(new Error("Page path must not be null!"));
                    else if (pageRepository.findByPath(pageForm.getPath()).isPresent())
                        return Xor.left(new Error(String.format("Page: %s already exists!", pageForm.getPath())));
                    else
                        return Xor.right(pageForm);
                }
        ).mapLeft(error -> {
                    LOG.warn(error.toString());
                    return error;
                }
        ).flatMapRight(pageForm -> {
                    if (StringUtils.isNotEmpty(pageForm.getParentPagePath())) {
                        Optional<Page> parentPageOpt = pageRepository.findByPath(pageForm.getParentPagePath());
                        if (parentPageOpt.isPresent()) {
                            pageForm.setParentPage(parentPageOpt.get());
                            return Xor.right(pageForm);
                        } else
                            return Xor.left(new Error(String.format("Parent page: %s does not exist!", pageForm.getParentPagePath())));
                    } else
                        return Xor.right(pageForm);
                }
        ).flatMapRight(pageForm -> {
                    List<Xor<Error, Stage>> stagesCreateResults = pageForm.getStages()
                            .stream().map(stageForm -> stageAdminService.createStage(stageForm)).collect(Collectors.toList());

                    List<Error> errors = stagesCreateResults.stream().filter(Xor::isLeft).map(Xor::getLeft).collect(Collectors.toList());
                    List<Stage> stages = stagesCreateResults.stream().filter(Xor::isRight).map(Xor::getRight).collect(Collectors.toList());

                    return (CollectionUtils.isNotEmpty(errors) ?
                            Xor.left(errors.stream().reduce(new Error("Error while creating stages for page"), Error::addNestedError)) :
                            Xor.right(stages));
                }
        ).mapLeft(error -> {
                    LOG.warn(error.toString());
                    return error;
                }
        ).mapRight(createdStages -> {
                    if (CollectionUtils.isNotEmpty(createdStages)) {
                        LOG.info("Successfully created stages for page.");
                        requestPageForm.getPage().setStages(createdStages);
                    }
                    return requestPageForm;
                }
        ).flatMapRight(pageForm ->
                Xor.catchNonFatal(() -> pageRepository.save(pageForm.getPage())).map(
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
                )
        );
    }
}

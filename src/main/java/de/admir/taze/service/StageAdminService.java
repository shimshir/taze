package de.admir.taze.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import de.admir.taze.admin.form.StageForm;
import de.admir.taze.model.cms.Stage;
import de.admir.taze.repository.cms.StageRepository;
import de.admir.taze.util.Error;
import de.admir.taze.util.Xor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.NestedRuntimeException;
import org.springframework.stereotype.Service;

@Service
public class StageAdminService {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private Cloudinary cloudinary;
    @Autowired
    private StageRepository stageRepository;


    @Value("${taze.cloudinary.folder.stage}")
    private String stageImageFolder;

    // TODO: Write unit test
    public Xor<Error, Stage> createStage(StageForm requestStageForm) {
        return Xor.catchNonFatal(() -> {
                    LOG.info("Uploading stage image");
                    return cloudinary
                            .uploader()
                            .upload(requestStageForm.getImageData(), ObjectUtils.asMap("folder", stageImageFolder));
                }
        ).map(e -> {
                    LOG.warn("Image upload for stage failed", e);
                    return new Error(e);
                },
                imageUploadResult -> {
                    String imageUrl = (String) imageUploadResult.get("secure_url");
                    requestStageForm.setImage(imageUrl);
                    return requestStageForm;
                }
        ).flatMapRight(stageForm -> Xor.catchNonFatal(() ->
                stageRepository.save(stageForm.getStage()))
                .map(e -> {
                            LOG.warn("Failed to save stage to DB", e);
                            return NestedRuntimeException.class.isAssignableFrom(e.getClass()) ?
                                    new Error(((NestedRuntimeException) e).getMostSpecificCause().getMessage()) :
                                    new Error(e);
                        },
                        stage -> {
                            LOG.info("Saved stage to DB");
                            return stage;
                        }
                )
        );
    }
}

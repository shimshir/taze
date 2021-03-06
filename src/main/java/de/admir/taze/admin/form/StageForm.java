package de.admir.taze.admin.form;

import com.fasterxml.jackson.annotation.JsonIgnore;

import de.admir.taze.model.cms.Stage;

public class StageForm {
    private String imageData;
    @JsonIgnore
    private Stage stage = new Stage();

    @JsonIgnore
    public Stage getStage() {
        return stage;
    }

    public String getHeadline() {
        return stage.getHeadline();
    }

    public void setHeadline(String header) {
        stage.setHeadline(header);
    }

    public String getImage() {
        return stage.getImage();
    }

    public void setImage(String image) {
        stage.setImage(image);
    }

    public String getImageData() {
        return imageData;
    }

    public void setImageData(String imageData) {
        this.imageData = imageData;
    }
}

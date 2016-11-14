package de.admir.taze.admin.form;

import com.fasterxml.jackson.annotation.JsonIgnore;

import de.admir.taze.model.cms.Page;

import java.util.ArrayList;
import java.util.List;

public class PageForm {
    private String parentPagePath;
    private List<StageForm> stages = new ArrayList<>();

    @JsonIgnore
    private Page page = new Page();

    @JsonIgnore
    public Page getPage() {
        return page;
    }

    public String getPath() {
        return page.getPath();
    }

    public void setPath(String path) {
        page.setPath(path);
    }

    public String getParentPagePath() {
        return parentPagePath;
    }

    public void setParentPagePath(String parentPagePath) {
        this.parentPagePath = parentPagePath;
    }

    public List<StageForm> getStages() {
        return stages;
    }

    public void setStages(List<StageForm> stages) {
        this.stages = stages;
    }

    @JsonIgnore
    public Page getParentPage() {
        return page.getParentPage();
    }

    @JsonIgnore
    public void setParentPage(Page parentPage) {
        page.setParentPage(parentPage);
    }
}

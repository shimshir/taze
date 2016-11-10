package de.admir.taze.model.cms;

import de.admir.taze.model.IdentifiableModel;

import org.apache.commons.collections.CollectionUtils;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Page extends IdentifiableModel {
    @Column(unique = true)
    private String code;
    @ManyToOne
    @JoinColumn(name = "parent_page_id")
    private Page parentPage;
    private String path;
    @OneToMany
    @JoinColumn(name = "page_id", referencedColumnName = "id")
    private List<Stage> stages;

    @Transient
    public List<Stage> getClosestStages() {
        return CollectionUtils.isNotEmpty(stages) ? stages : (parentPage != null ? parentPage.getClosestStages() : null);
    }
}

package de.admir.taze.repository.cms;

import de.admir.taze.model.cms.Stage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface StageRepository extends JpaRepository<Stage, Long> {
}

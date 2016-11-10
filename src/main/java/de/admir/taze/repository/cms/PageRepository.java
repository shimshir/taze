package de.admir.taze.repository.cms;

import de.admir.taze.model.cms.Page;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface PageRepository extends JpaRepository<Page, Long> {
}

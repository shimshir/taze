package de.admir.taze.repository.cms;

import de.admir.taze.model.cms.Page;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface PageRepository extends JpaRepository<Page, Long> {
    Optional<Page> findByCode(@Param("code") String code);
}

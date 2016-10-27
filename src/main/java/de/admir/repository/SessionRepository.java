package de.admir.repository;

import de.admir.model.Session;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface SessionRepository extends JpaRepository<Session, String> {
    Session findByUuid(@Param("uuid") String uuid);
}

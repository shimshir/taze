package de.admir.taze.repository;

import de.admir.taze.model.Session;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface SessionRepository extends JpaRepository<Session, Long> {
    Optional<Session> findByUuidId(@Param("uuid") String uuid);
}

package de.admir.repository.rest;

import de.admir.model.Session;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface SessionRepository extends JpaRepository<Session, String> {
    Optional<Session> findByUuidValue(@Param("uuid") String uuid);
}

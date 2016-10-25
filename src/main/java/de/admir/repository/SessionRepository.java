package de.admir.repository;

import de.admir.model.Session;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface SessionRepository extends JpaRepository<Session, Long> {
    Optional<Session> findByTazeUuidValue(@Param("uuid") String uuid);
}

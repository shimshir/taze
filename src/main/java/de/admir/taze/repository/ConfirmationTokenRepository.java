package de.admir.taze.repository;

import de.admir.taze.model.order.ConfirmationToken;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(exported = false)
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, String> {
    Optional<ConfirmationToken> findByValueId(@Param("valueId") String valueId);
}

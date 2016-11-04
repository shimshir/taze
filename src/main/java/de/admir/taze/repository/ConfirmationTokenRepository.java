package de.admir.taze.repository;

import de.admir.taze.model.order.ConfirmationToken;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, String> {
}

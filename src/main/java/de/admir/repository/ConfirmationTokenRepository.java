package de.admir.repository;

import de.admir.model.order.ConfirmationToken;
import de.admir.model.order.Order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, String> {
}

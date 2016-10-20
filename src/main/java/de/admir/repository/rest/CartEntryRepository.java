package de.admir.repository.rest;

import de.admir.model.cart.CartEntry;
import de.admir.model.cart.projection.CartEntryWithProductProjection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CartEntryRepository extends JpaRepository<CartEntry, Long> {
}

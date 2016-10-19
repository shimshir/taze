package de.admir.repository.rest;

import de.admir.model.cart.Cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findBySessionUuidValue(@Param("sessionUuid") String sessionUuid);
}

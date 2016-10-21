package de.admir.repository.rest;

import de.admir.model.product.ProductCard;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface ProductCardRepository extends JpaRepository<ProductCard, Long> {
    Optional<ProductCard> findByProductCode(@Param("code") String code);
}

package de.admir.repository;

import de.admir.model.product.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByCode(@Param("code") String code);
}

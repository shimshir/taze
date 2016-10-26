package de.admir.repository;

import de.admir.model.product.ProductCard;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource
public interface ProductCardRepository extends JpaRepository<ProductCard, Long> {
    ProductCard findByProductCode(@Param("code") String code);
}

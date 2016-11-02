package de.admir.repository;

import de.admir.model.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findBySessionUuidAndStatusCode(@Param("sessionUuid") String sessionUuid, @Param("status") String statusCode);
    Order findByTokenValue(@Param("tokenValue") String tokenValue);
}

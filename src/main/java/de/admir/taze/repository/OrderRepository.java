package de.admir.taze.repository;

import de.admir.taze.model.order.Order;
import de.admir.taze.model.order.OrderStatusEnum;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findBySessionUuidIdAndStatus(@Param("sessionUuid") String sessionUuid, @Param("status") OrderStatusEnum status);
    Optional<Order> findByTokenValue(@Param("tokenValue") String tokenValue);
}

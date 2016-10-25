package de.admir.repository.rest;

import de.admir.model.OrderStatusEnum;
import de.admir.model.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findBySessionUuidValueAndStatus(@Param("sessionUuid") String sessionUuid, @Param("status") OrderStatusEnum status);
}

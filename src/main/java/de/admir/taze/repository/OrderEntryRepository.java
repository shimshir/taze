package de.admir.taze.repository;

import de.admir.taze.model.order.OrderEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface OrderEntryRepository extends JpaRepository<OrderEntry, Long> {
}

package de.admir.repository.rest;

import de.admir.model.order.OrderEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface OrderEntryRepository extends JpaRepository<OrderEntry, Long> {
}

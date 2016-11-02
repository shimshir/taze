package de.admir.administration;

import de.admir.model.order.OrderStatus;

import org.lightadmin.api.config.AdministrationConfiguration;
import org.lightadmin.api.config.builder.EntityMetadataConfigurationUnitBuilder;
import org.lightadmin.api.config.unit.EntityMetadataConfigurationUnit;

public class OrderStatusAdministration extends AdministrationConfiguration<OrderStatus> {

    @Override
    public EntityMetadataConfigurationUnit configuration(EntityMetadataConfigurationUnitBuilder configurationBuilder) {
        return configurationBuilder
                .nameField("code")
                .singularName("Status")
                .pluralName("Statusi")
                .build();
    }
}
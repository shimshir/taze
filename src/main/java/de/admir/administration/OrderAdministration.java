package de.admir.administration;

import de.admir.model.order.Order;

import org.lightadmin.api.config.AdministrationConfiguration;
import org.lightadmin.api.config.builder.EntityMetadataConfigurationUnitBuilder;
import org.lightadmin.api.config.unit.EntityMetadataConfigurationUnit;

public class OrderAdministration extends AdministrationConfiguration<Order> {

    @Override
    public EntityMetadataConfigurationUnit configuration(EntityMetadataConfigurationUnitBuilder configurationBuilder) {
        return configurationBuilder.nameField("id").singularName("Narudzba").pluralName("Narudzbe").build();
    }
}
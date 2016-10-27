package de.admir.administration;

import de.admir.model.order.OrderEntry;

import org.lightadmin.api.config.AdministrationConfiguration;
import org.lightadmin.api.config.builder.EntityMetadataConfigurationUnitBuilder;
import org.lightadmin.api.config.unit.EntityMetadataConfigurationUnit;

public class OrderEntryAdministration extends AdministrationConfiguration<OrderEntry> {

    @Override
    public EntityMetadataConfigurationUnit configuration(EntityMetadataConfigurationUnitBuilder configurationBuilder) {
        return configurationBuilder.nameField("id").singularName("Unos narudzbe").pluralName("Unosi narudzbi").build();
    }
}
package de.admir.administration;

import de.admir.model.Customer;

import org.lightadmin.api.config.AdministrationConfiguration;
import org.lightadmin.api.config.builder.EntityMetadataConfigurationUnitBuilder;
import org.lightadmin.api.config.unit.EntityMetadataConfigurationUnit;

public class CustomerAdministration extends AdministrationConfiguration<Customer> {

    @Override
    public EntityMetadataConfigurationUnit configuration(EntityMetadataConfigurationUnitBuilder configurationBuilder) {
        return configurationBuilder.nameField("email").singularName("Musterija").pluralName("Musterije").build();
    }
}
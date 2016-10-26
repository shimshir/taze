package de.admir.administration;

import de.admir.model.Session;
import de.admir.model.product.Product;

import org.lightadmin.api.config.AdministrationConfiguration;
import org.lightadmin.api.config.builder.EntityMetadataConfigurationUnitBuilder;
import org.lightadmin.api.config.unit.EntityMetadataConfigurationUnit;

public class SessionAdministration extends AdministrationConfiguration<Session> {

    @Override
    public EntityMetadataConfigurationUnit configuration(EntityMetadataConfigurationUnitBuilder configurationBuilder) {
        return configurationBuilder.nameField("uuid").singularName("Sesija").pluralName("Sesije").build();
    }
}
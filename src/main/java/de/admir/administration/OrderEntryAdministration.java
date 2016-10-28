package de.admir.administration;

import de.admir.model.order.OrderEntry;

import org.lightadmin.api.config.AdministrationConfiguration;
import org.lightadmin.api.config.builder.EntityMetadataConfigurationUnitBuilder;
import org.lightadmin.api.config.builder.FieldSetConfigurationUnitBuilder;
import org.lightadmin.api.config.builder.FiltersConfigurationUnitBuilder;
import org.lightadmin.api.config.unit.EntityMetadataConfigurationUnit;
import org.lightadmin.api.config.unit.FieldSetConfigurationUnit;
import org.lightadmin.api.config.unit.FiltersConfigurationUnit;
import org.lightadmin.api.config.utils.EntityNameExtractor;
import org.lightadmin.api.config.utils.FieldValueRenderer;
import org.lightadmin.core.config.domain.field.CustomFieldMetadata;
import org.lightadmin.core.config.domain.field.PersistentFieldMetadata;

public class OrderEntryAdministration extends AdministrationConfiguration<OrderEntry> implements CustomFieldViews {

    @Override
    public EntityMetadataConfigurationUnit configuration(EntityMetadataConfigurationUnitBuilder configurationBuilder) {
        return configurationBuilder
                .nameExtractor((EntityNameExtractor<OrderEntry>) orderEntry ->
                        String.format("%s (%d %s)", orderEntry.getProduct().getName(), orderEntry.getAmount(), orderEntry.getProduct().getUnitCode()))
                .singularName("Narudzba, unos")
                .pluralName("Narudzbe, unosi")
                .build();
    }

    @Override
    public FieldSetConfigurationUnit listView(FieldSetConfigurationUnitBuilder fragmentBuilder) {
        return addCustomFields(fragmentBuilder);
    }

    @Override
    public FieldSetConfigurationUnit showView(FieldSetConfigurationUnitBuilder fragmentBuilder) {
        return addCustomFields(fragmentBuilder);
    }

    @Override
    public FieldSetConfigurationUnit quickView(FieldSetConfigurationUnitBuilder fragmentBuilder) {
        return addCustomFields(fragmentBuilder);
    }

    @Override
    public FieldSetConfigurationUnit addCustomFields(FieldSetConfigurationUnitBuilder fragmentBuilder) {
        FieldSetConfigurationUnit fieldSetConfigurationUnit = fragmentBuilder.build();
        fieldSetConfigurationUnit.addField(new PersistentFieldMetadata("ID", "id"));
        fieldSetConfigurationUnit.addField(new PersistentFieldMetadata("Narudzba", "order"));
        fieldSetConfigurationUnit.addField(new PersistentFieldMetadata("Proizvod", "product"));
        fieldSetConfigurationUnit.addField(new CustomFieldMetadata("Kolicina", input -> {
            OrderEntry orderEntry = (OrderEntry) input;
            return String.format("%d %s", orderEntry.getAmount(), orderEntry.getProduct().getUnitCode());
        }));
        fieldSetConfigurationUnit.addField(new CustomFieldMetadata("Ukupna cijena", input -> {
            OrderEntry orderEntry = (OrderEntry) input;
            return orderEntry.getTotalPrice() == null ? null : String.format("%.2f %s", orderEntry.getTotalPrice(), "KM");
        }));

        return fieldSetConfigurationUnit;
    }

    @Override
    public FiltersConfigurationUnit filters(FiltersConfigurationUnitBuilder filterBuilder) {
        filterBuilder
                .filter("ID", "id")
                .filter("Narudzba", "order")
                .filter("Proizvod", "product");
        return filterBuilder.build();
    }
}

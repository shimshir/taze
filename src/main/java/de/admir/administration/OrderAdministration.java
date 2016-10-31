package de.admir.administration;

import de.admir.model.order.Order;

import org.lightadmin.api.config.AdministrationConfiguration;
import org.lightadmin.api.config.builder.EntityMetadataConfigurationUnitBuilder;
import org.lightadmin.api.config.builder.FieldSetConfigurationUnitBuilder;
import org.lightadmin.api.config.builder.FiltersConfigurationUnitBuilder;
import org.lightadmin.api.config.unit.EntityMetadataConfigurationUnit;
import org.lightadmin.api.config.unit.FieldSetConfigurationUnit;
import org.lightadmin.api.config.unit.FiltersConfigurationUnit;
import org.lightadmin.core.config.domain.field.CustomFieldMetadata;
import org.lightadmin.core.config.domain.field.PersistentFieldMetadata;

public class OrderAdministration extends AdministrationConfiguration<Order> implements CustomFieldViews {

    @Override
    public EntityMetadataConfigurationUnit configuration(EntityMetadataConfigurationUnitBuilder configurationBuilder) {
        return configurationBuilder
                .nameField("id")
                .singularName("Narudzba")
                .pluralName("Narudzbe")
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

    public FieldSetConfigurationUnit addCustomFields(FieldSetConfigurationUnitBuilder fragmentBuilder) {
        FieldSetConfigurationUnit fieldSetConfigurationUnit = fragmentBuilder.build();
        fieldSetConfigurationUnit.addField(new PersistentFieldMetadata("ID", "id"));
        fieldSetConfigurationUnit.addField(new PersistentFieldMetadata("Musterija", "customer"));
        fieldSetConfigurationUnit.addField(new PersistentFieldMetadata("Unosi", "entries"));
        fieldSetConfigurationUnit.addField(new PersistentFieldMetadata("Sesija", "session"));
        fieldSetConfigurationUnit.addField(new PersistentFieldMetadata("Status", "status"));
        fieldSetConfigurationUnit.addField(new PersistentFieldMetadata("Token", "token"));
        fieldSetConfigurationUnit.addField(new PersistentFieldMetadata("Kreirano", "created"));
        fieldSetConfigurationUnit.addField(new PersistentFieldMetadata("Azurirano", "updated"));
        fieldSetConfigurationUnit.addField(new CustomFieldMetadata("Ukupna cijena", input ->
                ((Order) input).getTotalPrice() == null ? null : String.format("%.2f KM", ((Order) input).getTotalPrice())));

        return fieldSetConfigurationUnit;
    }

    @Override
    public FiltersConfigurationUnit filters(FiltersConfigurationUnitBuilder filterBuilder) {
        filterBuilder
                .filter("ID", "id")
                .filter("Musterija", "customer")
                .filter("Unosi", "entries")
                .filter("Status", "status");
        return filterBuilder.build();
    }
}
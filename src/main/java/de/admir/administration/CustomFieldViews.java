package de.admir.administration;

import org.lightadmin.api.config.builder.FieldSetConfigurationUnitBuilder;
import org.lightadmin.api.config.unit.FieldSetConfigurationUnit;

public interface CustomFieldViews {
    FieldSetConfigurationUnit addCustomFields(FieldSetConfigurationUnitBuilder fragmentBuilder);
}

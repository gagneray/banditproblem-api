package org.gagneray.api.banditproblemapi.validation;

import org.gagneray.rl.banditproblem.dto.TestBedConfigurationDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.MapBindingResult;

import java.util.HashMap;

@Component
public class ConfigurationValidatorAdapter {

    public static final String OBJECT_NAME = "TestBedConfigurationDTO";
    private final RestrictedValidator restrictedValidator;
    private final StandardValidator standardValidator;

    public ConfigurationValidatorAdapter(RestrictedValidator restrictedValidator, StandardValidator standardValidator) {
        this.restrictedValidator = restrictedValidator;
        this.standardValidator = standardValidator;
    }

    public MapBindingResult validate(TestBedConfigurationDTO testBedConfigurationDTO, boolean restricted) {
        MapBindingResult errors = new MapBindingResult(new HashMap<>(), ConfigurationValidatorAdapter.OBJECT_NAME);
        TestBedConfigurationValidator validator = restricted ? restrictedValidator : standardValidator;
        validator.validate(testBedConfigurationDTO, errors);
        return errors;
    }
}

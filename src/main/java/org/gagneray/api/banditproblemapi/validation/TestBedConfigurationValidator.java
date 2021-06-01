package org.gagneray.api.banditproblemapi.validation;

import org.gagneray.rl.banditproblem.dto.TestBedConfigurationDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public abstract class TestBedConfigurationValidator implements Validator {


    @Override
    public boolean supports(Class<?> clazz) {
        return TestBedConfigurationDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        throw new UnsupportedOperationException("unsupported operation");
    }


}

package org.gagneray.api.banditproblemapi.validation;

import org.gagneray.rl.banditproblem.dto.TestBedConfigurationDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.MapBindingResult;

import java.util.HashMap;

import static org.gagneray.api.banditproblemapi.validation.ConfigurationValidatorAdapter.OBJECT_NAME;

@Component
class StandardValidator extends TestBedConfigurationValidator {

    private final static Logger LOGGER = LoggerFactory.getLogger(StandardValidator.class);

    @Override
    public void validate(Object o, Errors errors) {
        TestBedConfigurationDTO testBedConfigurationDTO = (TestBedConfigurationDTO) o;
        LOGGER.info("Standard validation");

        MapBindingResult fieldErrors = new MapBindingResult(new HashMap<>(), OBJECT_NAME);

        if (testBedConfigurationDTO.getPolicies().isEmpty()) {
            String defaultMessage = "Number of action policies must be at least 1";
            fieldErrors.addError(new FieldError(OBJECT_NAME, "policies", testBedConfigurationDTO.getPolicies(), false, null, null, defaultMessage));
        }

        if (testBedConfigurationDTO.getBanditProblemCount() <= 0) {
            String defaultMessage = "Number of bandit problem must be at least 1";
            fieldErrors.addError(new FieldError(OBJECT_NAME, "banditProblemCount", testBedConfigurationDTO.getBanditProblemCount(), false, null, null, defaultMessage));
        }

        if (testBedConfigurationDTO.getTotalSteps() <= 0) {
            String defaultMessage = "Number of total steps must be at least 1";
            fieldErrors.addError(new FieldError(OBJECT_NAME, "totalSteps", testBedConfigurationDTO.getTotalSteps(), false, null, null, defaultMessage));
        }

        if (testBedConfigurationDTO.getK() <= 0) {
            String defaultMessage = "Number of bandits per problem must be at least 1";
            fieldErrors.addError(new FieldError(OBJECT_NAME, "k", testBedConfigurationDTO.getK(), false, null, null, defaultMessage));
        }

        errors.addAllErrors(fieldErrors);
    }

}
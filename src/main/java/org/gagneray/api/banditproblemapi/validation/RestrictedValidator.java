package org.gagneray.api.banditproblemapi.validation;

import org.gagneray.api.banditproblemapi.configuration.TestBedProperties;
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
class RestrictedValidator extends TestBedConfigurationValidator {

    private final static Logger LOGGER = LoggerFactory.getLogger(RestrictedValidator.class);

    private final TestBedProperties testBedProperties;

    RestrictedValidator(TestBedProperties testBedProperties) {
        this.testBedProperties = testBedProperties;
    }

    @Override
    public void validate(Object o, Errors errors) {
        TestBedConfigurationDTO testBedConfigurationDTO = (TestBedConfigurationDTO) o;
        LOGGER.info("Validate object, restricted access");

        MapBindingResult fieldErrors = new MapBindingResult(new HashMap<>(), OBJECT_NAME);

        if (testBedConfigurationDTO.getPolicies().isEmpty() || testBedConfigurationDTO.getPolicies().size() > testBedProperties.getMaxPoliciesCount()) {
            String defaultMessage = String.format("Number of bandit problem must be in range [1, %d]", testBedProperties.getMaxBanditProblemCount());
            fieldErrors.addError(new FieldError(OBJECT_NAME, "policies", testBedConfigurationDTO.getPolicies(), false, null, null, defaultMessage));
        }

        if (testBedConfigurationDTO.getBanditProblemCount() <= 0 || testBedConfigurationDTO.getBanditProblemCount() > testBedProperties.getMaxBanditProblemCount()) {
            String defaultMessage = String.format("Number of bandit problem must be in range [1, %d]", testBedProperties.getMaxBanditProblemCount());
            fieldErrors.addError(new FieldError(OBJECT_NAME, "banditProblemCount", testBedConfigurationDTO.getBanditProblemCount(), false, null, null, defaultMessage));
        }

        if (testBedConfigurationDTO.getTotalSteps() <= 0 || testBedConfigurationDTO.getTotalSteps() > testBedProperties.getMaxTotalSteps()) {
            String defaultMessage = String.format("Number of total steps must be in range [1, %d]", testBedProperties.getMaxTotalSteps());
            fieldErrors.addError(new FieldError(OBJECT_NAME, "totalSteps", testBedConfigurationDTO.getTotalSteps(), false, null, null, defaultMessage));
        }

        if (testBedConfigurationDTO.getK() <= 0 || testBedConfigurationDTO.getK() > testBedProperties.getMaxBanditPerBanditProblem()) {
            String defaultMessage = String.format("Number of bandits must be in range [1, %d]", testBedProperties.getMaxBanditPerBanditProblem());
            fieldErrors.addError(new FieldError(OBJECT_NAME, "k", testBedConfigurationDTO.getK(), false, null, null, defaultMessage));
        }

        errors.addAllErrors(fieldErrors);
    }

}
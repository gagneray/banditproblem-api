package org.gagneray.api.banditproblemapi.validation;

import org.gagneray.api.banditproblemapi.configuration.TestBedProperties;
import org.gagneray.rl.banditproblem.dto.TestBedConfigurationDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.MapBindingResult;
import org.springframework.validation.Validator;

import java.util.HashMap;

@Component
public class TestBedConfigurationValidator implements Validator {

    public static String OBJECT_NAME = "TestBedConfigurationDTO";

    private final TestBedProperties testBedProperties;
    private boolean restricted = true;

    public TestBedConfigurationValidator(TestBedProperties testBedProperties) {
        this.testBedProperties = testBedProperties;
    }

    public void restricted(boolean restricted) {
        this.restricted = restricted;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return TestBedConfigurationDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        TestBedConfigurationDTO testBedConfigurationDTO = (TestBedConfigurationDTO) o;

        if (restricted) {
            onPropertiesValidation(testBedConfigurationDTO, errors);
        } else {
            basicValidation(testBedConfigurationDTO, errors);
        }

        restricted = true;
    }

    private void onPropertiesValidation(TestBedConfigurationDTO testBedConfigurationDTO, Errors errors) {

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

    private void basicValidation(TestBedConfigurationDTO testBedConfigurationDTO, Errors errors) {

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
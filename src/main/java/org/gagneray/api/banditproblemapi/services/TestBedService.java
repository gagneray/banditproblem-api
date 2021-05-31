package org.gagneray.api.banditproblemapi.services;

import org.gagneray.api.banditproblemapi.validation.TestBedConfigurationValidator;
import org.gagneray.rl.banditproblem.configurations.TestBedConfiguration;
import org.gagneray.rl.banditproblem.dto.TestBedConfigurationDTO;
import org.gagneray.rl.banditproblem.services.BanditProblemTestBed;
import org.gagneray.rl.banditproblem.services.BanditProblemTestBed.TestBedResultDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.validation.MapBindingResult;

import java.util.HashMap;

@Service
public class TestBedService {

    private final static Logger LOGGER = LoggerFactory.getLogger(TestBedService.class);

    private final TestBedConfigurationValidator validator;

    public TestBedService(TestBedConfigurationValidator validator) {
        this.validator = validator;
    }

    public TestBedResultDTO processTestBed(TestBedConfigurationDTO testBedConfigurationDTO) {

        TestBedConfiguration testBedConfiguration = new TestBedConfiguration(testBedConfigurationDTO);
        BanditProblemTestBed testBed = new BanditProblemTestBed(testBedConfiguration);
        testBed.process();
        return testBed.getResults();
    }

    public MapBindingResult validateTestBedConfiguration(TestBedConfigurationDTO testBedConfigurationDTO) {

        LOGGER.info("Validating testbed configuration");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        validator.restricted(authentication instanceof AnonymousAuthenticationToken);

        MapBindingResult errors = new MapBindingResult(new HashMap<>(), TestBedConfigurationValidator.OBJECT_NAME);
        validator.validate(testBedConfigurationDTO, errors);
        return errors;
    }
}

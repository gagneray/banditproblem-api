package org.gagneray.api.banditproblemapi.services;

import org.gagneray.api.banditproblemapi.validation.ConfigurationValidatorAdapter;
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

@Service
public class TestBedService {

    private final static Logger LOGGER = LoggerFactory.getLogger(TestBedService.class);

    private final ConfigurationValidatorAdapter validator;

    public TestBedService(ConfigurationValidatorAdapter validatorAdapter) {
        this.validator = validatorAdapter;
    }

    public TestBedResultDTO processTestBed(TestBedConfigurationDTO testBedConfigurationDTO) {
        LOGGER.info("Processing testbed configuration");
        TestBedConfiguration testBedConfiguration = new TestBedConfiguration(testBedConfigurationDTO);
        BanditProblemTestBed testBed = new BanditProblemTestBed(testBedConfiguration);
        testBed.process();
        return testBed.getResults();
    }

    public MapBindingResult validateTestBedConfiguration(TestBedConfigurationDTO testBedConfigurationDTO) {
        LOGGER.info("Validating testbed configuration");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return validator.validate(testBedConfigurationDTO, authentication instanceof AnonymousAuthenticationToken);
    }
}

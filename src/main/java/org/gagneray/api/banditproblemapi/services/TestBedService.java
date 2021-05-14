package org.gagneray.api.banditproblemapi.services;

import org.gagneray.rl.banditproblem.configurations.TestBedConfiguration;
import org.gagneray.rl.banditproblem.dto.TestBedConfigurationDTO;
import org.gagneray.rl.banditproblem.services.BanditProblemTestBed;
import org.gagneray.rl.banditproblem.services.BanditProblemTestBed.TestBedResultDTO;
import org.springframework.stereotype.Service;

@Service
public class TestBedService {

    public TestBedResultDTO processTestBed(TestBedConfigurationDTO testBedConfigurationDTO) {
        TestBedConfiguration testBedConfiguration = new TestBedConfiguration(testBedConfigurationDTO);
        BanditProblemTestBed testBed = new BanditProblemTestBed(testBedConfiguration);
        testBed.process();
        return testBed.getResults();
    }
}

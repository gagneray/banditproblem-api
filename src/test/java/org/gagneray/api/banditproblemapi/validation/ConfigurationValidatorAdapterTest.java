package org.gagneray.api.banditproblemapi.validation;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.gagneray.api.banditproblemapi.configuration.TestBedProperties;
import org.gagneray.rl.banditproblem.dto.TestBedConfigurationDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.MapBindingResult;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ConfigurationValidatorAdapterTest {

    @Autowired
    private ConfigurationValidatorAdapter configurationValidatorAdapter;

    @Autowired
    private TestBedProperties testBedProperties;

    private static final String JSON_CONFIG_PATH = "src/test/java/resources/testbed_config_1.json";


    @Test
    void ValidateReturnsRightClassObject() throws IOException {
        TestBedConfigurationDTO testBedConfigurationDTO = createTestBedConfigurationDTO(JSON_CONFIG_PATH);
        MapBindingResult result = configurationValidatorAdapter.validate(testBedConfigurationDTO, false);
        assertEquals("TestBedConfigurationDTO", result.getObjectName());
    }

    @Test
    void ValidateIsOk() throws IOException {
        TestBedConfigurationDTO testBedConfigurationDTO = createTestBedConfigurationDTO(JSON_CONFIG_PATH);
        MapBindingResult result = configurationValidatorAdapter.validate(testBedConfigurationDTO, false);
        assertFalse(result.hasErrors());
    }

    @Test
    void ValidationTestAgaintsProperties() throws IOException {
        TestBedConfigurationDTO testBedConfigurationDTO = createTestBedConfigurationDTO(JSON_CONFIG_PATH);

        testBedConfigurationDTO.setBanditProblemCount(testBedProperties.getMaxBanditProblemCount() + 1);
        testBedConfigurationDTO.setTotalSteps(testBedProperties.getMaxTotalSteps() + 1);
        testBedConfigurationDTO.setK(testBedProperties.getMaxBanditPerBanditProblem() + 1);

        for (int i = 0; i < testBedProperties.getMaxPoliciesCount() + 1; i++) {
            testBedConfigurationDTO.getPolicies().add(testBedConfigurationDTO.getPolicies().get(0));
        }

        // Standard validation
        MapBindingResult result = configurationValidatorAdapter.validate(testBedConfigurationDTO, false);
        assertFalse(result.hasErrors());

        // Restricted Validation
        result = configurationValidatorAdapter.validate(testBedConfigurationDTO, true);
        assertTrue(result.hasErrors());
        assertEquals(4, result.getErrorCount());
    }

    private TestBedConfigurationDTO createTestBedConfigurationDTO(String jsonConfigPath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        TestBedConfigurationDTO testBedConfigurationDTO = mapper.readValue(new File(jsonConfigPath), TestBedConfigurationDTO.class);
        return testBedConfigurationDTO;
    }
}
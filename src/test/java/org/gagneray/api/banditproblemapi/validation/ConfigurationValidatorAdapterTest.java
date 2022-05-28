package org.gagneray.api.banditproblemapi.validation;

import com.fasterxml.jackson.databind.ObjectMapper;
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

    private static final String JSON_CONFIG_PATH = "src/test/java/org/gagneray/api/banditproblemapi/resources/testBedConfigurations/testbed_config_1.json";


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

    private TestBedConfigurationDTO createTestBedConfigurationDTO(String jsonConfigPath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        TestBedConfigurationDTO testBedConfigurationDTO = mapper.readValue(new File(jsonConfigPath), TestBedConfigurationDTO.class);
        return testBedConfigurationDTO;
    }
}
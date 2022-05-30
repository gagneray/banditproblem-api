package org.gagneray.api.banditproblemapi.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.gagneray.rl.banditproblem.dto.TestBedConfigurationDTO;

import java.io.File;
import java.io.IOException;

public class TestUtils {

    public static final String JSON_CONFIG_PATH = "src/test/java/resources/testbed_config_1.json";

    public static TestBedConfigurationDTO createTestBedConfigurationDTO(String jsonConfigPath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        TestBedConfigurationDTO testBedConfigurationDTO = mapper.readValue(new File(jsonConfigPath), TestBedConfigurationDTO.class);
        return testBedConfigurationDTO;
    }
}

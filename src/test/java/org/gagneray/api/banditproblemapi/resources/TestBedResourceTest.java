package org.gagneray.api.banditproblemapi.resources;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class TestBedResourceTest {

    @Autowired
    private TestBedResource testBedResource;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(testBedResource).build();
    }


    @Test
    void processTestBedTest_isOK() throws Exception {

        //TODO deserialize policies to use dto as content in request. bug : empty policies array after mapping
        /*TestBedConfigurationDTO conf = createTestBedConfigurationDTO(JSON_CONFIG_PATH);
        objectMapper = new ObjectMapper();
        System.out.println(objectMapper.writeValueAsString(conf));*/

        mockMvc.perform(
                        post("/api/banditproblem/testbed")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\n" +
                                        "  \"policies\" : [ {\n" +
                                        "    \"name\" : \"E_GREEDY\",\n" +
                                        "    \"epsilon\" : 0.1\n" +
                                        "  }, {\n" +
                                        "    \"name\" : \"UCB\",\n" +
                                        "    \"c\" : 2\n" +
                                        "  } ],\n" +
                                        "  \"banditProblemCount\" : 2000,\n" +
                                        "  \"k\" : 10,\n" +
                                        "  \"totalSteps\" : 1000\n" +
                                        "}")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
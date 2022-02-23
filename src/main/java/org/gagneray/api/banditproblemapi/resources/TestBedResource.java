package org.gagneray.api.banditproblemapi.resources;


import org.gagneray.api.banditproblemapi.services.TestBedService;
import org.gagneray.rl.banditproblem.dto.TestBedConfigurationDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/banditproblem")
public class TestBedResource {

    private final static Logger LOGGER = LoggerFactory.getLogger(TestBedResource.class);

    private final TestBedService testBedService;

    public TestBedResource(TestBedService testBedService) {
        this.testBedService = testBedService;
    }

    @GetMapping("/testbed")
    public ResponseEntity<Object> processTestBed(@RequestBody @NonNull TestBedConfigurationDTO testBedConfigurationDTO) {
        LOGGER.info("Received call for testbed processing with configuration {}", testBedConfigurationDTO);
        Errors errors = testBedService.validateTestBedConfiguration(testBedConfigurationDTO);

        if (errors.hasErrors()) {
            LOGGER.info("Configuration Not Valid");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(errors.getAllErrors());
        }

        LOGGER.info("Configuration validated");
        return ResponseEntity.ok(testBedService.processTestBed(testBedConfigurationDTO));
    }
}

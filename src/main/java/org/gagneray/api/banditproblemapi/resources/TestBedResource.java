package org.gagneray.api.banditproblemapi.resources;


import org.gagneray.api.banditproblemapi.services.TestBedService;
import org.gagneray.rl.banditproblem.dto.TestBedConfigurationDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestBedResource {

    private final TestBedService testBedService;

    public TestBedResource(TestBedService testBedService) {
        this.testBedService = testBedService;
    }

    @GetMapping("/testbed")
    public ResponseEntity<Object> processTestBed(@RequestBody @NonNull TestBedConfigurationDTO testBedConfigurationDTO) {

        Errors errors = testBedService.validateTestBedConfiguration(testBedConfigurationDTO);

        if (errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(errors.getAllErrors());
        }

        return ResponseEntity.ok(testBedService.processTestBed(testBedConfigurationDTO));
    }
}

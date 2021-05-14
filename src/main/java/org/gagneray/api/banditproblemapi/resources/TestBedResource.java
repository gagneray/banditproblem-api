package org.gagneray.api.banditproblemapi.resources;


import org.gagneray.api.banditproblemapi.services.TestBedService;
import org.gagneray.rl.banditproblem.dto.TestBedConfigurationDTO;
import org.gagneray.rl.banditproblem.services.BanditProblemTestBed.TestBedResultDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
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
    public ResponseEntity<TestBedResultDTO> processTestBed(@RequestBody @NonNull TestBedConfigurationDTO testBedConfigurationDTO) {
        return ResponseEntity.ok(testBedService.processTestBed(testBedConfigurationDTO));
    }

}

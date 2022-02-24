package org.gagneray.api.banditproblemapi.dto;

import org.gagneray.rl.banditproblem.services.BanditProblemTestBed.TestBedResultDTO;
import org.springframework.validation.ObjectError;

import java.util.List;
import java.util.Objects;

public class TestBedResultExtendedDTO {


    private List<ObjectError> validationErrors;
    private TestBedResultDTO testBedResult;

    public TestBedResultExtendedDTO() {
    }

    public void setValidationErrors(List<ObjectError> validationErrors) {
        this.validationErrors = validationErrors;
    }

    public List<ObjectError> getValidationErrors() {
        return validationErrors;
    }

    public void setTestBedResult(TestBedResultDTO testBedResult) {
        this.testBedResult = testBedResult;
    }

    public TestBedResultDTO getTestBedResult() {
        return testBedResult;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestBedResultExtendedDTO that = (TestBedResultExtendedDTO) o;
        return Objects.equals(validationErrors, that.validationErrors) && Objects.equals(testBedResult, that.testBedResult);
    }

    @Override
    public int hashCode() {
        return Objects.hash(validationErrors, testBedResult);
    }

    @Override
    public String toString() {
        return "TestBedResultExtendedDTO{" +
                "validationErrors=" + validationErrors +
                ", testBedResult=" + testBedResult +
                '}';
    }
}

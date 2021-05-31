package org.gagneray.api.banditproblemapi.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import java.util.Objects;

@ConstructorBinding
@ConfigurationProperties(prefix = "testbed")
public class TestBedProperties {

    private final int maxPoliciesCount;
    private final int maxTotalSteps;
    private final int maxBanditProblemCount;
    private final int maxBanditPerBanditProblem;

    public TestBedProperties(int maxPoliciesCount, int maxTotalSteps, int maxBanditProblemCount, int maxBanditPerBanditProblem) {
        this.maxPoliciesCount = maxPoliciesCount;
        this.maxTotalSteps = maxTotalSteps;
        this.maxBanditProblemCount = maxBanditProblemCount;
        this.maxBanditPerBanditProblem = maxBanditPerBanditProblem;
    }

    public int getMaxPoliciesCount() {
        return maxPoliciesCount;
    }

    public int getMaxTotalSteps() {
        return maxTotalSteps;
    }

    public int getMaxBanditProblemCount() {
        return maxBanditProblemCount;
    }

    public int getMaxBanditPerBanditProblem() {
        return maxBanditPerBanditProblem;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestBedProperties that = (TestBedProperties) o;
        return maxPoliciesCount == that.maxPoliciesCount && maxTotalSteps == that.maxTotalSteps && maxBanditProblemCount == that.maxBanditProblemCount && maxBanditPerBanditProblem == that.maxBanditPerBanditProblem;
    }

    @Override
    public int hashCode() {
        return Objects.hash(maxPoliciesCount, maxTotalSteps, maxBanditProblemCount, maxBanditPerBanditProblem);
    }

    @Override
    public String toString() {
        return "TestBedProperties{" +
                "maxPoliciesCount=" + maxPoliciesCount +
                ", maxTotalSteps=" + maxTotalSteps +
                ", maxBanditProblemCount=" + maxBanditProblemCount +
                ", maxBanditPerBanditProblem=" + maxBanditPerBanditProblem +
                '}';
    }
}

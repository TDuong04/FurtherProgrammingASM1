package Model;

import java.util.List;

public class PolicyHolder extends Customer {
    private List<Dependent> dependents;

    public PolicyHolder(String id, String fullName, String insuranceId, List<String> claimIds, List<Dependent> dependents) {
        super(id, fullName, insuranceId, claimIds);
        this.dependents = dependents;
    }

    // Getters and setters for dependents
    public List<Dependent> getDependents() {
        return dependents;
    }

    public void setDependents(List<Dependent> dependents) {
        this.dependents = dependents;
    }
}
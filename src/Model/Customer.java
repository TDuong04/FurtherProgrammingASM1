package Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Customer {
    protected String id;
    protected String fullName;
    protected String insuranceId;
    protected List<String> claimIds;
    protected List<Claim> Claimlist;


    public Customer(String id, String fullName, String insuranceId, List<String> claimIds) {
        if (!isValidId(id)) {
            throw new IllegalArgumentException("Invalid ID format. ID must be in the format 'c-xxxxxxx' where 'x' is a digit.");
        }
        this.id = id;
        this.fullName = fullName;
        this.insuranceId = insuranceId;
        this.claimIds = claimIds;
        this.Claimlist = new ArrayList<>();
    }
    private boolean isValidId(String id) {
        return Pattern.matches("c-\\d{7}", id);
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        if (!isValidId(id)) {
            throw new IllegalArgumentException("Invalid ID format. ID must be in the format 'c-xxxxxxx' where 'x' is a digit.");
        }
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getInsuranceId() {
        return insuranceId;
    }

    public void setInsuranceId(String insuranceId) {
        this.insuranceId = insuranceId;
    }

    public List<String> getClaimIds() {
        return claimIds;
    }

    public void setClaimIds(List<String> claimIds) {
        this.claimIds = claimIds;
    }
    public List<Claim> getClaimList() {
        return Claimlist;
    }

    public void setClaimlist(List<Claim> claimlist) {
        Claimlist = claimlist;
    }
    public void addnewclaim(Claim claim) {
        this.Claimlist.add(claim);
    }
}
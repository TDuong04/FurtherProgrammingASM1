/**
 * @author <Huynh Thai Duong - s3978955>
 */
package Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public abstract class Customer {
    protected String id;
    protected String fullName;
    protected String insuranceId;
    protected InsuranceCard insuranceCard;
    protected List<String> claimIds;
    protected List<Claim> Claimlist;


    public Customer(String id, String fullName, String insuranceId, List<String> claimIds) {

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


    public String getFullName() {
        return fullName;
    }

    public String getInsuranceId() {
        return insuranceId;
    }


    public List<Claim> getClaimList() {
        return Claimlist;
    }


    public void addnewclaim(Claim claim) {
        this.Claimlist.add(claim);
    }

    public void removeClaim(Claim findclaimbyID) {
        this.Claimlist.remove(findclaimbyID);
    }

    public void setInsuranceCard(InsuranceCard matchingCard) {
        this.insuranceCard = matchingCard;

    }
}
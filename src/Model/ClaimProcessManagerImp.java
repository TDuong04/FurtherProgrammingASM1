package Model;


import java.util.*;

public class ClaimProcessManagerImp implements ClaimProcessManager {
    public static List<Claim> claimList = new ArrayList<>();
    public static List<Customer> customerList = new ArrayList<>();

    public ClaimProcessManagerImp(List<Customer> customers, List<Claim> claims) {
        this.customerList = customers;
        this.claimList = claims;
    }

    @Override
    public void add(Claim claim) {
        claimList.add(claim);

    }

    @Override
    public void updateClaim(String id, Date examDate, double claimAmount, String status) {
        Claim claim = getOne(id);
        if (claim != null) {
            claim.setExamDate(examDate);
            claim.setClaimAmount(claimAmount);
            claim.setStatus(status);

        } else {
            System.out.println("Claim with ID " + id + " not found.");
        }
    }

    @Override
    public void deleteClaim(String id, List<Customer> customers) {

    }

    @Override
    public void delete(String id) {
        Claim claim = getOne(id);
        if (claim != null) {
            claimList.remove(claim);

        }
    }

    @Override
    public Claim getOne(String id) {
        for (Claim claim : claimList) {
            if (claim.getId().equals(id)) {
                return claim;
            }
        }
        return null;
    }

    @Override
    public List<Claim> getAll() {
        return new ArrayList<>(claimList);
    }
    public List<Customer> getCustomers() {
        return this.customerList;
    }

    public List<Claim> getClaims() {
        return this.claimList;
    }

    public Customer getCustomerById(String customerId) {
        for (Customer customer : customerList){
            if (customer.getId().equals(customerId)) {
                return customer;
            }
        }
        return null;
    }



}
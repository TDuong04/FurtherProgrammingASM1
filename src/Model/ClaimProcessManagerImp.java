package Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ClaimProcessManagerImp implements ClaimProcessManager {
    public static List<Claim> claimList = new ArrayList<>();
    public static List<Customer> customerList = new ArrayList<>();

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
    public void loadClaims(List<Customer> customers) {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/claims.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String id = parts[0];
                Date claimDate = new SimpleDateFormat("yyyy-MM-dd").parse(parts[1]);
                String customerId = parts[2];
                String cardNumber = parts[3];
                Date examDate = new SimpleDateFormat("yyyy-MM-dd").parse(parts[4]);
                double claimAmount = Double.parseDouble(parts[5]);
                String status = parts[6];
                BankingInfo receiverBankingInfo = new BankingInfo(parts[7], parts[8], parts[9]);
                List<String> documents = Arrays.asList(parts[10].split(";"));

                Claim claim = new Claim(id, claimDate, customerId, cardNumber, examDate, claimAmount, status, receiverBankingInfo, documents);
                claimList.add(claim);

                Customer associatedCustomer = customers.stream()
                        .filter(customer -> customer.getId().equals(claim.getCustomerId()))
                        .findFirst()
                        .orElse(null);

                if (associatedCustomer != null) {
                    associatedCustomer.getClaimList().add(claim);
                    claim.setInsuredPerson(associatedCustomer);
                }
            }
        } catch (IOException | ParseException e) {
            System.out.println("An error occurred while reading from file: " + e.getMessage());
        }
    }
    public static List<Customer> loadCustomersFromFile(String filename) {
        List<Customer> customers = new ArrayList<>();
        List<PolicyHolder> policyHolders = new ArrayList<>();
        List<Dependent> dependents = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String id = parts[0];
                String fullName = parts[1];
                String insuranceId = parts[2];
                List<String> claimIds = Arrays.asList(parts[3].split(";"));
                String customerType = parts[4];

                if ("PolicyHolder".equals(customerType)) {
                    PolicyHolder policyHolder = new PolicyHolder(id, fullName, insuranceId, claimIds, new ArrayList<>());
                    customers.add(policyHolder);
                    policyHolders.add(policyHolder);
                } else if ("Dependent".equals(customerType)) {
                    String policyHolderId = parts[5];
                    Dependent dependent = new Dependent(id, fullName, insuranceId, claimIds, policyHolderId);
                    customers.add(dependent);
                    dependents.add(dependent);
                } else {
                    throw new IllegalArgumentException("Invalid customer type: " + customerType);
                }
            }

            for (Dependent dependent : dependents) {
                PolicyHolder policyHolder = policyHolders.stream()
                        .filter(holder -> holder.getId().equals(dependent.getPolicyHolderId()))
                        .findFirst()
                        .orElse(null);

                if (policyHolder != null) {
                    policyHolder.getDependents().add(dependent);
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading from file: " + e.getMessage());
        }

        return customers;
    }
    public void linkClaimsToCustomers(List<Customer> customers) {
        for (Claim claim : claimList) {
            Customer FindingCustomer = customers.stream()
                    .filter(customer -> customer.getId().equals(claim.getCustomerId()))
                    .findFirst()
                    .orElse(null);

            if (FindingCustomer != null && !FindingCustomer.getClaimList().contains(claim)) {
                FindingCustomer.getClaimList().add(claim);
            }
        }
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
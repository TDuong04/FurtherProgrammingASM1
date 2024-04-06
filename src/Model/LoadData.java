package Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;



public class LoadData {
    public List<Claim> loadClaims(List<Customer> customers) {
        List<Claim> claimList = new ArrayList<>();
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
        return claimList;
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
                    if (parts.length >= 5) {
                        String policyHolderId = parts[5];
                        Dependent dependent = new Dependent(id, fullName, insuranceId, claimIds, policyHolderId);
                        customers.add(dependent);
                        dependents.add(dependent);
                    } else {
                        throw new IllegalArgumentException("Missing policy holder ID for dependent: " + id);
                    }
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
    public void linkClaimsToCustomers(List<Customer> customers, List<Claim> claimList) {
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
}

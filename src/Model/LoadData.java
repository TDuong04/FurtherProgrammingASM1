/**
 * @author <Huynh Thai Duong - s3978955>
 */
package Model;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
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
        Map<String, Dependent> dependents = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < 5) {
                    System.out.println("Invalid line format: " + line);
                    continue;
                }
                String id = parts[0];
                String fullName = parts[1];
                String insuranceId = parts[2];
                List<String> claimIds = Arrays.asList(parts[3].split(";"));
                String customerType = parts[4];

                if ("PolicyHolder".equals(customerType)) {
                    List<Dependent> dependentsList = new ArrayList<>();
                    if (parts.length >= 6 && parts[5] != null && !parts[5].isEmpty()) {
                        String[] dependentsID = parts[5].split(";");
                        for (String dependentID : dependentsID) {
                            if (dependents.containsKey(dependentID)) {
                                dependentsList.add(dependents.get(dependentID));
                            }
                        }
                    }
                    PolicyHolder policyHolder = new PolicyHolder(id, fullName, insuranceId, claimIds, dependentsList);
                    customers.add(policyHolder);
                } else if ("Dependent".equals(customerType)) {
                    Dependent dependent = new Dependent(id, fullName, insuranceId, claimIds);
                    customers.add(dependent);
                    dependents.put(id, dependent);
                } else {
                    System.out.println("Invalid customer type: " + customerType);
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
    public static List<InsuranceCard> loadInsuranceCardsFromFile(String filename) {
        List<InsuranceCard> insuranceCards = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String cardNumber = parts[0];
                String cardHolder = parts[1];
                String policyOwner = parts[2];
                Date expirationDate = dateFormat.parse(parts[3]);
                InsuranceCard insuranceCard = new InsuranceCard(cardNumber, cardHolder, policyOwner, expirationDate);
                insuranceCards.add(insuranceCard);
            }
        } catch (IOException | ParseException e) {
            System.out.println("An error occurred while reading from file: " + e.getMessage());
        }
        return insuranceCards;
    }
    public void linkCustomersToInsuranceCards(List<Customer> customers, List<InsuranceCard> insuranceCards) {
        for (Customer customer : customers) {
            InsuranceCard matchingCard = insuranceCards.stream()
                    .filter(card -> card.getCardNumber().equals(customer.getInsuranceId()))
                    .findFirst()
                    .orElse(null);

            if (matchingCard != null) {
                customer.setInsuranceCard(matchingCard);
            }
        }
    }
    public static void saveCustomersToFile(String filename, List<Customer> customers) {
        customers.sort(Comparator.comparing(Customer::getId));
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Customer customer : customers) {
                StringBuilder lineBuilder = new StringBuilder();
                lineBuilder.append(String.join(",",
                        customer.getId(),
                        customer.getFullName(),
                        customer.getInsuranceId(),
                        String.join(";", customer.getClaimList().stream().map(Claim::getId).collect(Collectors.toList())),
                        customer instanceof PolicyHolder ? "PolicyHolder" : "Dependent"
                ));

                if (customer instanceof PolicyHolder) {
                    PolicyHolder policyHolder = (PolicyHolder) customer;
                    StringBuilder dependentIds = new StringBuilder();
                    for (Dependent dependent : policyHolder.getDependents()) {
                        dependentIds.append(dependent.getId()).append(";");
                    }
                    lineBuilder.append(",").append(dependentIds.toString());
                } else {
                    lineBuilder.append(",");
                }

                writer.write(lineBuilder.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("An error occurred while writing to file: " + e.getMessage());
        }
    }

    public static void saveClaimsToFile(String filename, List<Claim> claims) {
        claims.sort(Comparator.comparing(Claim::getId));
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Claim claim : claims) {
                String line = String.join(",",
                        claim.getId(),
                        dateFormat.format(claim.getClaimDate()),
                        claim.getInsuredPerson().getId(),
                        claim.getCardNumber(),
                        dateFormat.format(claim.getExamDate()),
                        Double.toString(claim.getClaimAmount()),
                        claim.getStatus(),
                        claim.getReceiverBankingInfo().getBank(),
                        claim.getReceiverBankingInfo().getName(),
                        claim.getReceiverBankingInfo().getNumber(),
                        String.join(";", claim.getDocuments())
                );
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("An error occurred while writing to file: " + e.getMessage());
        }
    }

    public static void saveInsuranceCardsToFile(String filename, List<InsuranceCard> insuranceCards) {
        insuranceCards.sort(Comparator.comparing(InsuranceCard::getCardNumber));
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (InsuranceCard card : insuranceCards) {
                String line = String.join(",",
                        card.getCardNumber(),
                        card.getCardHolder(),
                        card.getPolicyOwner(),
                        dateFormat.format(card.getExpirationDate())
                );
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("An error occurred while writing to file: " + e.getMessage());
        }
    }
}

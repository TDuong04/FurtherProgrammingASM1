import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Collectors;

public class Customer {
    protected String id;
    protected String fullName;
    protected String insuranceId;
    protected List<String> claimIds;


    public Customer(String id, String fullName, String insuranceId, List<String> claimIds) {
        if (!isValidId(id)) {
            throw new IllegalArgumentException("Invalid ID format. ID must be in the format 'c-xxxxxxx' where 'x' is a digit.");
        }
        this.id = id;
        this.fullName = fullName;
        this.insuranceId = insuranceId;
        this.claimIds = claimIds;
    }

    // Validates the customer ID format
    private boolean isValidId(String id) {
        return Pattern.matches("c-\\d{7}", id);
    }

    // Getters and setters
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

    public static List<Customer> loadCustomersFromFile(String filename) {
        List<Customer> customers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String id = parts[0];
                String fullName = parts[1];
                String insuranceId = parts[2];
                List<String> claimIds = Arrays.asList(parts[3].split(";"));
                boolean isPolicyHolder = Boolean.parseBoolean(parts[4]);
                if (isPolicyHolder) {
                    List<String> dependentIds = new ArrayList<>();
                    List<Dependent> dependents = new ArrayList<>();
                    if (parts.length > 5) {
                        dependentIds = Arrays.asList(parts[5].split(";"));
                        for (String dependentId : dependentIds) {
                            dependents.add(new Dependent(dependentId, fullName, insuranceId, claimIds));
                        }
                    }
                    customers.add(new PolicyHolder(id, fullName, insuranceId, claimIds, dependents));
                } else {
                    customers.add(new Customer(id, fullName, insuranceId, claimIds));
                }
            }
        } catch (IOException e) {
            System.out.println("ooby dooo i wanna be like uuu " + e.getMessage());
        }
        return customers;
    }


}
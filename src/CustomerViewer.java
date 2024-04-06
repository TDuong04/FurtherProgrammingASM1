import java.util.List;
import java.util.Map;

public class CustomerViewer {

    public void displayCustomer(Customer customer, Map<String, Claim> claimsMap) {
        System.out.println("Customer ID: " + customer.getId());
        System.out.println("Full Name: " + customer.getFullName());
        System.out.println("Insurance ID: " + customer.getInsuranceId());
        System.out.println("Claims:");
        for (String claimId : customer.getClaimIds()) {
            System.out.println("- " + claimId);
        }
        if (customer instanceof PolicyHolder) {
            System.out.println("Dependents:");
            List<Dependent> dependents = ((PolicyHolder) customer).getDependents();
            if (dependents.isEmpty()) {
                System.out.println("None");
            } else {
                for (Dependent dependent : dependents) {
                    System.out.println("- Dependent ID: " + dependent.getId());
                    System.out.println("  Full Name: " + dependent.getFullName());
                }
            }
        }
        for (String claimId : customer.getClaimIds()) {
            Claim claim = claimsMap.get(claimId);
            if (claim != null) {
                System.out.println("Claim ID: " + claim.getId());
                System.out.println("Exam Date: " + claim.getExamDate());
                System.out.println("Claim Amount: " + claim.getClaimAmount());
                System.out.println("Status: " + claim.getStatus());
            }
        }
    }
}
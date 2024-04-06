import java.text.SimpleDateFormat;
import java.util.List;

public class ClaimView {
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public void displayClaim(Claim claim, List<Customer> customers) {
        System.out.println("Claim ID: " + claim.getId());
        System.out.println("Claim Date: " + sdf.format(claim.getClaimDate()));
        // Find the Customer object with the matching customerId
        Customer customer = customers.stream()
                .filter(c -> c.getId().equals(claim.getCustomerId()))
                .findFirst()
                .orElse(null);

        if (customer != null) {
            System.out.println("Customer ID: " + customer.getId());
            System.out.println("Customer Name: " + customer.getFullName());
            // Print other customer details...
        } else {
            System.out.println("Customer with ID " + claim.getCustomerId() + " not found.");
        }
        System.out.println("Card Number: " + claim.getCardNumber());
        System.out.println("Exam Date: " + sdf.format(claim.getExamDate()));
        System.out.println("Claim Amount: " + claim.getClaimAmount());
        System.out.println("Status: " + claim.getStatus());
        System.out.println("Receiver Banking Info: " + claim.getReceiverBankingInfo().getBank() + " - " + claim.getReceiverBankingInfo().getName() + " - " + claim.getReceiverBankingInfo().getNumber());
        System.out.println("Documents: ");
        for (String document : claim.getDocuments()) {
            System.out.println("- " + document);
        }
    }
}

import java.text.SimpleDateFormat;

public class ClaimView {
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public void displayClaim(Claim claim) {
        System.out.println("Claim ID: " + claim.getId());
        System.out.println("Claim Date: " + sdf.format(claim.getClaimDate()));
        System.out.println("Insured Person: "  + claim.getInsuredPerson().getFullName());
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

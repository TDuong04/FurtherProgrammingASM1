import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InsuranceCard {
    private String cardNumber; // 10 digits
    private String cardHolder;
    private String policyOwner;
    private Date expirationDate;

    // Constructor
    public InsuranceCard(String cardNumber, String cardHolder, String policyOwner, Date expirationDate) {
        setCardNumber(cardNumber); // Validates and sets the card number
        this.cardHolder = cardHolder;
        this.policyOwner = policyOwner;
        this.expirationDate = expirationDate;
    }

    // Setters
    public void setCardNumber(String cardNumber) {
        if (cardNumber != null && cardNumber.matches("\\d{10}")) {
            this.cardNumber = cardNumber;
        } else {
            throw new IllegalArgumentException("Card number must be 10 digits.");
        }
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    public void setPolicyOwner(String policyOwner) {
        this.policyOwner = policyOwner;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    // Getters
    public String getCardNumber() {
        return cardNumber;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public String getPolicyOwner() {
        return policyOwner;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public static List<InsuranceCard> loadInsuranceCardsFromFile(String filename) {
        List<InsuranceCard> insuranceCards = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                // Assuming the file has lines in the format: cardNumber,cardHolder,policyOwner,expirationDate
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
}
 class InsuranceCardViewer {
    public void displayInsuranceCard(InsuranceCard insuranceCard) {
        System.out.println("Card Number: " + insuranceCard.getCardNumber());
        System.out.println("Card Holder: " + insuranceCard.getCardHolder());
        System.out.println("Policy Owner: " + insuranceCard.getPolicyOwner());
        System.out.println("Expiration Date: " + insuranceCard.getExpirationDate());
    }
}
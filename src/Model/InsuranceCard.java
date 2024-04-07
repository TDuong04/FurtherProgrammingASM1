package Model;
import java.util.Date;


public class InsuranceCard {
    private String cardNumber;
    private String cardHolder;
    private String policyOwner;
    private Date expirationDate;

    public InsuranceCard(String cardNumber, String cardHolder, String policyOwner, Date expirationDate) {
        setCardNumber(cardNumber);
        this.cardHolder = cardHolder;
        this.policyOwner = policyOwner;
        this.expirationDate = expirationDate;
    }

    public void setCardNumber(String cardNumber) {
        if (cardNumber != null && cardNumber.matches("\\d{10}")) {
            this.cardNumber = cardNumber;
        } else {
            throw new IllegalArgumentException("Card number must be 10 digits.");
        }
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
}

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;


public class Claim {
    private String id; // Format: f-numbers; 10 numbers
    private Date claimDate;
    private Customer insuredPerson;
    private String cardNumber;
    private Date examDate;
    private List<String> documents; // Format: ClaimId_CardNumber_DocumentName.pdf
    private double claimAmount;
    private String status; // New, Processing, Done
    private BankingInfo receiverBankingInfo;


    // Validates the claim ID format
    private boolean isValidId(String id) {
        return Pattern.matches("f-\\d{10}", id);
    }

    // Getters
    public String getId() {
        return id;
    }
    public Claim(String line) {
        String[] parts = line.split(",");
        if (parts.length != 10) {
            throw new IllegalArgumentException("Line should contain 10 parts separated by commas");
        }
        this.id = parts[0];
        try {
            this.claimDate = new SimpleDateFormat("yyyy-MM-dd").parse(parts[1]);
            // Assuming parts[2] is the insuredPersonId, which is not directly used in Claim
            this.cardNumber = parts[3];
            this.examDate = new SimpleDateFormat("yyyy-MM-dd").parse(parts[4]);
            this.claimAmount = Double.parseDouble(parts[5]);
            this.status = parts[6];
            // Assuming parts[7], parts[8], and parts[9] are bank, name, and number for BankingInfo
            this.receiverBankingInfo = new BankingInfo(parts[7], parts[8], parts[9]);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Date should be in the format yyyy-MM-dd", e);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Claim amount should be a valid number", e);
        }
    }

    public Date getClaimDate() {
        return claimDate;
    }

    public Customer getInsuredPerson() {
        return insuredPerson;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public Date getExamDate() {
        return examDate;
    }

    public List<String> getDocuments() {
        return documents;
    }

    public double getClaimAmount() {
        return claimAmount;
    }

    public String getStatus() {
        return status;
    }

    public BankingInfo getReceiverBankingInfo() {
        return receiverBankingInfo;
    }

    // Setters
    public void setId(String id) {
        if (!isValidId(id)) {
            throw new IllegalArgumentException("Invalid ID format. ID must be in the format 'f-xxxxxxxxxx' where 'x' is a digit.");
        }
        this.id = id;
    }
    public boolean areDocumentFormatsValid() {
        String documentPattern = String.format("%s_%s_[^\\s]+\\.pdf", this.id, this.cardNumber);
        Pattern pattern = Pattern.compile(documentPattern);
        for (String document : this.documents) {
            if (!pattern.matcher(document).matches()) {
                return false;
            }
        }
        return true;
    }

    public void setClaimDate(Date claimDate) {
        this.claimDate = claimDate;
    }

    public void setInsuredPerson(Customer insuredPerson) {
        this.insuredPerson = insuredPerson;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setExamDate(Date examDate) {
        this.examDate = examDate;
    }

    public void setDocuments(List<String> documents) {
        this.documents = documents;
    }

    public void setClaimAmount(double claimAmount) {
        this.claimAmount = claimAmount;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setReceiverBankingInfo(BankingInfo receiverBankingInfo) {
        this.receiverBankingInfo = receiverBankingInfo;
    }
}



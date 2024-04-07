package Model;

import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;


public class Claim {
    private String id;
    private Date claimDate;
    private Customer insuredPerson;
    private String customerId;

    private String cardNumber;
    private Date examDate;
    private List<String> documents;
    private double claimAmount;
    private String status;
    private BankingInfo receiverBankingInfo;

    public Claim(String id, Date claimDate, String customerId, String cardNumber, Date examDate, double claimAmount, String status, BankingInfo receiverBankingInfo, List<String> documents) {
        this.id = id;
        this.claimDate = claimDate;
        this.customerId = customerId;
        this.cardNumber = cardNumber;
        this.examDate = examDate;
        this.documents = documents;
        this.claimAmount = claimAmount;
        this.status = status;
        this.receiverBankingInfo = receiverBankingInfo;
    }
    public Claim(String id, Date claimDate, Customer insuredPerson, String cardNumber, Date examDate, List<String> documents, double claimAmount, String status, BankingInfo receiverBankingInfo) {
        this.id = id;
        this.claimDate = claimDate;
        this.insuredPerson = insuredPerson;
        this.cardNumber = cardNumber;
        this.examDate = examDate;
        this.documents = documents;
        this.claimAmount = claimAmount;
        this.status = status;
        this.receiverBankingInfo = receiverBankingInfo;
    }
    // Getters
    public String getId() {
        return id;
    }
    // Constructor
    public Claim(String id, Date claimDate, String customerId, Customer insuredPerson, String cardNumber, Date examDate, List<String> documents, double claimAmount, String status, BankingInfo receiverBankingInfo) {
        this.id = id;
        this.claimDate = claimDate;
        this.customerId = customerId;
        this.insuredPerson = insuredPerson;
        this.cardNumber = cardNumber;
        this.examDate = examDate;
        this.documents = documents;
        this.claimAmount = claimAmount;

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
    public String getCustomerId() {
        return customerId;
    }

    public void setInsuredPerson(Customer insuredPerson) {
        this.insuredPerson = insuredPerson;
    }

    public void setExamDate(Date examDate) {
        this.examDate = examDate;
    }

    public void setClaimAmount(double claimAmount) {
        this.claimAmount = claimAmount;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}



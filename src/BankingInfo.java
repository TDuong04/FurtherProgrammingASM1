
public class BankingInfo {
    private String bank;
    private String name;
    private String number;

    // Constructor
    public BankingInfo(String bank, String name, String number) {
        this.bank = bank;
        this.name = name;
        this.number = number;
    }

    // Getter for bank
    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
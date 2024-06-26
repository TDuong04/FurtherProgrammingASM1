/**
 * @author <Huynh Thai Duong - s3978955>
 */
package Model;

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


    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }


}
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        Map<String, InsuranceCard> insuranceCards = new HashMap<>();
        Map<String, Claim> claims = new HashMap<>();

        List<Customer> customers = Customer.loadCustomersFromFile("src/customers.txt");

        CustomerViewer viewer = new CustomerViewer();
        for (Customer customer : customers) {
            viewer.displayCustomer(customer, claims);
        }
    }
}
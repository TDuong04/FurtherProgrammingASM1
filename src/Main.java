import Controller.ClaimController;
import Viewer.ClaimView;
import Model.Claim;
import Model.ClaimProcessManagerImp;
import Model.Customer;
import Model.InsuranceCard;
import Viewer.CustomerViewer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        Map<String, InsuranceCard> insuranceCards = new HashMap<>();
        Map<String, Claim> claims = new HashMap<>();

        List<Customer> customers = Customer.loadCustomersFromFile("src/customers.txt");

        ClaimProcessManagerImp claimProcessManager = new ClaimProcessManagerImp();
        ClaimView claimView = new ClaimView();
        ClaimController claimController = new ClaimController(claimProcessManager, claimView);

        claimProcessManager.loadClaims();


        CustomerViewer viewer = new CustomerViewer();
        for (Customer customer : customers) {
            viewer.displayCustomer(customer, claims);
        }

        for (Claim claim : claimProcessManager.getAll()) {
            claimView.displayClaim(claim, customers);
        }
        claimProcessManager.deleteClaim("f-1234567893", customers);
        System.out.println("After deleting claim:");
        for (Claim claim : claimProcessManager.getAll()) {
            claimView.displayClaim(claim, customers);
        }

    }

}
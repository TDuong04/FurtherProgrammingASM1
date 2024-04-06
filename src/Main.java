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
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {


        Map<String, InsuranceCard> insuranceCards = new HashMap<>();

        ClaimProcessManagerImp claimProcessManager = new ClaimProcessManagerImp();

        List<Customer> customers = ClaimProcessManagerImp.loadCustomersFromFile("src/customers.txt");

        ClaimView claimView = new ClaimView();
        ClaimController claimController = new ClaimController(claimProcessManager, claimView);

        claimProcessManager.loadClaims(customers);
        claimProcessManager.linkClaimsToCustomers(customers);

        CustomerViewer viewer = new CustomerViewer();
        for (Customer customer : customers) {
            viewer.displayCustomer(customer);
        }


//        for (Claim claim : claimProcessManager.getAll()) {
//            claimView.displayClaim(claim, customers);
//        }
//        claimProcessManager.deleteClaim("f-1234567893", customers);
//        System.out.println("After deleting claim:");
//        for (Claim claim : claimProcessManager.getAll()) {
//            claimView.displayClaim(claim, customers);
//        }

    }

}
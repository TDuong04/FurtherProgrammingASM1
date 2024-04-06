import Controller.ClaimController;
import Model.*;
import Viewer.ClaimView;
import Viewer.CustomerViewer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        LoadData loader = new LoadData();

        // Load the data
        List<Customer> customers = loader.loadCustomersFromFile("src/customers.txt");
        List<Claim> claims = loader.loadClaims(customers);
        loader.linkClaimsToCustomers(customers, claims);

        ClaimProcessManagerImp manager = new ClaimProcessManagerImp(customers, claims);

        ClaimController controller = new ClaimController(manager, null);
        ClaimView view = new ClaimView(controller);
        controller.setView(view);
        controller.App();
    }

}
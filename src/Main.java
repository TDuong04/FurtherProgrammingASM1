import Controller.ClaimController;
import Model.*;
import Viewer.ClaimView;

import java.util.List;

import static Model.LoadData.*;

public class Main {
    public static void main(String[] args) {
        LoadData loader = new LoadData();

        // Load the data
        List<Customer> customers = loader.loadCustomersFromFile("src/customers.txt");
        List<Claim> claims = loader.loadClaims(customers);
        loader.linkClaimsToCustomers(customers, claims);
        List<InsuranceCard> insuranceCards = loader. loadInsuranceCardsFromFile("src/insuranceCard.txt");
        loader.linkCustomersToInsuranceCards(customers, insuranceCards);

        ClaimProcessManagerImp manager = new ClaimProcessManagerImp(customers, claims);

        ClaimController controller = new ClaimController(manager, null);
        ClaimView view = new ClaimView(controller);
        controller.setView(view);
        controller.App();
        // Save the data when exiting
        saveCustomersToFile("src/customers.txt", manager.getCustomers());
        saveClaimsToFile("src/claims.txt", manager.getClaims());
        saveInsuranceCardsToFile("src/insuranceCard.txt", insuranceCards);
    }

}
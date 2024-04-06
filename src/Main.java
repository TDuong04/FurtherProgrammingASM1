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
import java.util.Set;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        ClaimProcessManagerImp manager = new ClaimProcessManagerImp();

        ClaimController controller = new ClaimController(manager, null);
        ClaimView view = new ClaimView(controller);
        controller.setView(view);
        controller.App();
    }

}
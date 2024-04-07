package Controller;

import Model.Claim;
import Model.ClaimProcessManagerImp;
import Model.Customer;
import Viewer.ClaimView;

import java.util.List;
import java.util.Scanner;

public class ClaimController {

    private ClaimProcessManagerImp claimProcessManager;
    private ClaimView claimView;

    public ClaimController(ClaimProcessManagerImp claimProcessManager, ClaimView claimView) {
        this.claimProcessManager = claimProcessManager;
        this.claimView = claimView;
    }
    public void setView(ClaimView view) {
        this.claimView = view;
    }

    public void App()
    {
        int choice;
        Scanner in = new Scanner(System.in);
        do
        {
            claimView.AdminScreen();
            System.out.print("Enter your choice: ");
            choice = in.nextInt();
            in.nextLine();

            switch (choice)
            {
                case 1:
                    claimView.displayNewClaimForm();
                    break;
                case 2:
                    claimView.deleteClaimForm();
                    break;
                case 3:
                    claimView.getOneClaimForm();
                    break;
                case 4:
                    claimView.getAllClaims();
                    break;
                case 5:
                    claimView.updateClaim();
                    break;
                case 0:
                    System.out.println("Exiting Program");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter according to the menu.");
            }
            System.out.println("------------------------------------------------");
        } while (choice != 0);
    }




    public void deleteClaim(String id) {
        claimProcessManager.delete(id);
    }

    public Customer getCustomerById(String customerId) {
        return claimProcessManager.getCustomerById(customerId);
    }


    public Claim getClaim(String claimId) {
        return claimProcessManager.getOne(claimId);
    }

    public List<Claim> getAllClaims() {
        return claimProcessManager.getAll();
    }

    public void addClaim(Claim newClaim) {
        claimProcessManager.add(newClaim);
    }
}
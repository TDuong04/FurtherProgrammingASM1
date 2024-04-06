package Controller;

import Model.Claim;
import Model.ClaimProcessManagerImp;
import Viewer.ClaimView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ClaimController {
    private List<Claim> claims = new ArrayList<>();
    private ClaimProcessManagerImp claimProcessManager;
    private ClaimView claimView;

    public ClaimController(ClaimProcessManagerImp claimProcessManager, ClaimView claimView) {
        this.claimProcessManager = claimProcessManager;
        this.claimView = claimView;
    }

    public void createClaim(Claim claim) {
        claimProcessManager.add(claim);
    }

    public void updateClaimScan() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter claim ID:");
        String id = scanner.nextLine();

        System.out.println("Enter exam date (yyyy-MM-dd):");
        String examDateString = scanner.nextLine();
        Date examDate = null;
        try {
            examDate = new SimpleDateFormat("yyyy-MM-dd").parse(examDateString);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please enter in yyyy-MM-dd format.");
            return;
        }

        System.out.println("Enter claim amount:");
        double claimAmount = scanner.nextDouble();

        scanner.nextLine();
        System.out.println("Enter status:");
        String status = scanner.nextLine();

        claimProcessManager.updateClaim(id, examDate, claimAmount, status);
    }


    public void deleteClaim(String id) {
        claimProcessManager.delete(id);
    }


}
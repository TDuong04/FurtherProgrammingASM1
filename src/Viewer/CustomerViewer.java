package Viewer;

import Model.Claim;
import Model.Customer;
import Model.Dependent;
import Model.PolicyHolder;

import java.util.List;
import java.util.Map;

public class CustomerViewer {


    public void displayCustomer(Customer customer) {
        System.out.println("Customer ID: " + customer.getId());
        System.out.println("Full Name: " + customer.getFullName());
        System.out.println("Insurance ID: " + customer.getInsuranceId());
        System.out.println("Claims:");
        System.out.println("Number of claims: " + customer.getClaimList().size()); // Print out the size of the ClaimList
        for (Claim claim : customer.getClaimList()) {
            System.out.println("- " + claim.getId());
        }
        if (customer instanceof PolicyHolder) {
            System.out.println("Dependents:");
            List<Dependent> dependents = ((PolicyHolder) customer).getDependents();
            if (dependents.isEmpty()) {
                System.out.println("None");
            } else {
                for (Dependent dependent : dependents) {
                    System.out.println("- Dependent ID: " + dependent.getId());
                    System.out.println("  Full Name: " + dependent.getFullName());
                }
            }
        }
    }
}
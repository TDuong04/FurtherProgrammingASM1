package Viewer;

import Model.InsuranceCard;

class InsuranceCardViewer {
    public void displayInsuranceCard(InsuranceCard insuranceCard) {
        System.out.println("Card Number: " + insuranceCard.getCardNumber());
        System.out.println("Card Holder: " + insuranceCard.getCardHolder());
        System.out.println("Policy Owner: " + insuranceCard.getPolicyOwner());
        System.out.println("Expiration Date: " + insuranceCard.getExpirationDate());
    }
}
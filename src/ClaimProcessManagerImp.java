import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ClaimProcessManagerImp implements ClaimProcessManager {
    private List<Claim> claims = new ArrayList<>();

    @Override
    public void add(Claim claim) {
        claims.add(claim);
        saveClaimsToFile();
    }

    @Override
    public void updateClaim(String id, Date examDate, double claimAmount, String status) {
        Claim claim = getOne(id);
        if (claim != null) {
            claim.setExamDate(examDate);
            claim.setClaimAmount(claimAmount);
            claim.setStatus(status);
            saveClaimsToFile();
        } else {
            System.out.println("Claim with ID " + id + " not found.");
        }
    }

    @Override
    public void delete(String id) {
        Claim claim = getOne(id);
        if (claim != null) {
            claims.remove(claim);
            saveClaimsToFile();
        }
    }

    @Override
    public Claim getOne(String id) {
        for (Claim claim : claims) {
            if (claim.getId().equals(id)) {
                return claim;
            }
        }
        return null;
    }

    @Override
    public List<Claim> getAll() {
        return new ArrayList<>(claims);
    }
    public void loadClaims () {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/claims.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String id = parts[0];
                Date claimDate = new SimpleDateFormat("yyyy-MM-dd").parse(parts[1]);
                String customerId = parts[2];
                String cardNumber = parts[3];
                Date examDate = new SimpleDateFormat("yyyy-MM-dd").parse(parts[4]);
                double claimAmount = Double.parseDouble(parts[5]);
                String status = parts[6];
                BankingInfo receiverBankingInfo = new BankingInfo(parts[7], parts[8], parts[9]);
                List<String> documents = Arrays.asList(parts[10].split(";"));
                //Claim(String id, Date claimDate, String customerId, Customer insuredPerson, String cardNumber, Date examDate, List<String> documents, double claimAmount, String status, BankingInfo receiverBankingInfo)

                Claim claim = new Claim(id, claimDate, customerId, cardNumber, examDate, claimAmount, status, receiverBankingInfo, documents);
                claims.add(claim);
            }
        } catch (IOException | ParseException e) {
            System.out.println("An error occurred while reading from file: " + e.getMessage());
        }
    }


    // Constructor

    private void saveClaimsToFile() {
        // Sort the claims by ID before saving
        Collections.sort(claims, Comparator.comparing(Claim::getId));

        try (PrintWriter writer = new PrintWriter("claims.txt")) {
            for (Claim claim : claims) {
                writer.println(claim.getId() + "," + claim.getClaimDate() + "," + claim.getInsuredPerson().getId() + "," + claim.getCardNumber() + "," + claim.getExamDate() + "," + claim.getClaimAmount() + "," + claim.getStatus() + "," + claim.getReceiverBankingInfo().getBank() + "," + claim.getReceiverBankingInfo().getName() + "," + claim.getReceiverBankingInfo().getNumber());
            }
        } catch (IOException e) {
            System.out.println("An error occurred while writing to file: " + e.getMessage());
        }
    }
}
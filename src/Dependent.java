import java.util.List;

public class Dependent extends Customer {
    public Dependent(String id, String fullName, String insuranceId, List<String> claimIds) {
        super(id, fullName, insuranceId, claimIds);
    }
}
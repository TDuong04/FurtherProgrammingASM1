
import java.util.Date;
import java.util.List;

    public interface ClaimProcessManager {
        void add(Claim claim);
        void updateClaim(String id, Date examDate, double claimAmount, String status);
        void delete(String id);
        Claim getOne(String id);
        List<Claim> getAll();
    }
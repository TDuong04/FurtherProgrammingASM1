/**
 * @author <Huynh Thai Duong - s3978955>
 */
package Model;

import java.util.Date;
import java.util.List;

    public interface ClaimProcessManager {
        void add(Claim claim);
        void updateClaim(String id, Date examDate, double claimAmount, String status);
        void deleteClaim(String id, List<Customer> customers);

        void delete(String id);

        Claim getOne(String id);
        List<Claim> getAll();
    }
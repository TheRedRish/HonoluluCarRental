import java.time.LocalDateTime;
import java.util.List;

public interface IRenterRepository {
    void addRenter(Renter renter);
    List<Renter> getAllRenters();
    Renter getRenterByName(String name);
    Renter getRenterByPhoneNumber(String phoneNumber);
    void updateRenter(Renter updatedRenter);
    void deleteRenterByName(String name);
    void deleteRenterByPhoneNumber(String phoneNumber);
}

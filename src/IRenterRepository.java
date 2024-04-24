import java.util.List;
import java.util.UUID;

public interface IRenterRepository {
    void addRenter(Renter renter);
    List<Renter> getAllRenters();
    Renter getRenterById(UUID id);
    List<Renter> getRenterByName(String name);
    List<Renter>  getRenterByPhoneNumber(String phoneNumber);
    void updateRenter(Renter updatedRenter);
    void deleteRenterByName(String name);
    void deleteRenterByPhoneNumber(String phoneNumber);
    void deleteRenterById(UUID id);
}

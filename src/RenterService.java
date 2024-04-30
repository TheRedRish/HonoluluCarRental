import java.util.List;
import java.util.UUID;

public class RenterService {
    private final IRenterRepository renterRepository;

    public RenterService(IRenterRepository renterRepository) {
        this.renterRepository = renterRepository;
    }

    //<editor-fold desc="renter">
    public void addRenter(Renter renter) {
        renterRepository.addRenter(renter);
    }

    public List<Renter> getAllRenters() {
        return renterRepository.getAllRenters();
    }

    public List<Renter> getRentersByName(String name) {
        return renterRepository.getRentersByName(name);
    }

    public List<Renter> getRentersByPhoneNumber(String phoneNumber) {
        return renterRepository.getRentersByPhoneNumber(phoneNumber);
    }

    public void updateRenter(Renter updatedRenter) {
        renterRepository.updateRenter(updatedRenter);
    }

    public void deleteRenterByName(String name) {
        renterRepository.deleteRenterByName(name);
    }

    public void deleteRenterByPhoneNumber(String phoneNumber) {
        renterRepository.deleteRenterByPhoneNumber(phoneNumber);
    }

    public void deleteRenterById(UUID id){
        renterRepository.deleteRenterById(id);
    }
    //</editor-fold>

}
